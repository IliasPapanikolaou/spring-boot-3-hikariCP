package com.ipap.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipap.dto.StudentDto;
import com.ipap.service.StudentService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.net.SocketTimeoutException;

@Component
public class KafkaMessageConsumer {

    private final StudentService studentService;

    Logger log = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    public KafkaMessageConsumer(StudentService studentService) {
        this.studentService = studentService;
    }

    // Simple Kafka Consumer with manual Acknowledgments
    @KafkaListener(topics = "student-topic", groupId = "group-1", containerFactory = "manualAckKafkaListenerContainerFactory")
    public void listenerManualAcks(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) throws SocketTimeoutException {
        try {
            String message = record.value();
            log.info("Received message [{}] in group-1", message);

            // Simulate some work
            //Thread.sleep(200);

            // Produce intended exception
            //String nullString = null;
            //nullString.length();

            // Call external API
            //Optional<Post> postByIdOpt = apiClient.getPostById(Integer.parseInt(message.substring(4)));
            //postByIdOpt.ifPresent(p -> log.info(p.toString()));

            // Convert Json String to Object
            ObjectMapper objectMapper = new ObjectMapper();
            StudentDto studentDto = objectMapper.readValue(message, StudentDto.class);

            // Store to Database
            studentService.saveStudent(studentDto);

            log.warn("--- Warning! In case of error, process won't reach to this point, thus no Acks will committed ---");

            // Acknowledge
            acknowledgment.acknowledge();
        } catch (Exception ex) {
            log.error("Error during processing the message: [{}]: {}", record.value(), ex.getMessage());
            // Handle error here - send to fallback mechanism

            // Code here

            // Intended throw exception in order to engage retry mechanism
            throw new ListenerExecutionFailedException(ex.getMessage());
        } finally {
            // Always Acknowledge
            // acknowledgment.acknowledge();
        }
    }
}
