package com.transaction.infraestructure.producer.transaction;

import com.transaction.infraestructure.producer.transaction.event.TransactionEventV1;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static org.springframework.kafka.support.KafkaHeaders.KEY;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Component
@Slf4j
@RequiredArgsConstructor
public class TransactionProducerV1 {


    /**
     * IMPORTANT.
     * To incorporate the Kafka framework into your project, it's essential to define the data transport model.
     * Kafka allows data to be transmitted in formats such as JSON, string, Avro, and even custom structures.
     * In this example, we'll use the JSON structure for message transmission.
     */
    private final KafkaTemplate<String, TransactionEventV1> kafkaTemplate;

    @Value("${topic.transaction.name}")
    public String topic;

    @SneakyThrows
    public void send(TransactionEventV1 transaction) {

        String key = transaction.getCodeTransaction();

        var mensagem = MessageBuilder.withPayload(transaction)
            .setHeader(TOPIC, topic)
            .setHeader(KEY, key)
            .build();

        CompletableFuture<SendResult<String, TransactionEventV1>> send = kafkaTemplate.send(mensagem);

        send.whenComplete((sendResult, throwable) -> {
            if (throwable != null) {
                handleFailure(key, transaction, throwable);
            } else {
                handleSuccess(key, transaction, sendResult);
            }
        });

    }

    private void handleFailure(String key, TransactionEventV1 value, Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
    }

    private void handleSuccess(String key, TransactionEventV1 value, SendResult<String, TransactionEventV1> result) {
        log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
    }
}
