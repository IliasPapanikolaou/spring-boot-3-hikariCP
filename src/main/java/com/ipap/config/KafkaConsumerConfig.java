package com.ipap.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.ExponentialBackOff;
import org.springframework.util.backoff.FixedBackOff;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${kafka.backoff.interval:3000}")
    private Long interval;

    @Value(value = "${kafka.backoff.max_failure:5}")
    private Long maxAttempts;

    // Manual Ack and Offset Reset Consumer Factory
    @Bean
    public ConsumerFactory<String, String> manualAckConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // Auto Commit configuration for Manual Ack
        configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        // The auto-offset-reset property determines the offset to start consuming messages when there is no committed
        // offset for the consumer group. In this case, it's set to earliest to consume messages from the beginning
        // configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> manualAckKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(manualAckConsumerFactory());
        // Error Handling
        factory.setCommonErrorHandler(errorHandler());
        // Ack Mode Manual
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

        return factory;
    }

    @Bean
    public DefaultErrorHandler errorHandler() {
        // BackOff fixedBackOff = new FixedBackOff(interval, maxAttempts);
        BackOff exponentialBackOff = new ExponentialBackOff(interval, 1.5);
        DefaultErrorHandler errorHandler = new DefaultErrorHandler((consumerRecord, ex) -> {
            // Logic to execute when all the retry attempts are exhausted
        }, /*fixedBackOff*/ exponentialBackOff);
        // We can specify which exceptions are retryable and which are non-retryable
        // If we don't set any retryable exceptions, the default set of retryable exceptions will be used:
        // MessagingException, RetryableException, ListenerExecutionFailedException
        errorHandler.addRetryableExceptions(SocketTimeoutException.class);
        errorHandler.addNotRetryableExceptions(NullPointerException.class);

        return errorHandler;
    }
}
