package com.ipap.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageProducer {

    Logger log = LoggerFactory.getLogger(KafkaMessageProducer.class);

    private final KafkaTemplate<String, String> template;

    public KafkaMessageProducer(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public void sendMessageToTopic(String message) {
        template.send("student-topic", message)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Sent message [{}] on topic [{}] with offset [{}]",
                                message, result.getRecordMetadata().topic(), result.getRecordMetadata().offset());
                    } else {
                        log.error("Unable to send message [{}] due to: [{}]", message, ex.getMessage());
                    }
                });
    }
}
