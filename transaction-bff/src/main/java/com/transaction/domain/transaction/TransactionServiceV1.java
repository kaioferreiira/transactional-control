package com.transaction.domain.transaction;


import com.transaction.application.controller.v1.request.TransactionRequestV1;
import com.transaction.infraestructure.producer.transaction.TransactionProducerV1;
import com.transaction.infraestructure.producer.transaction.event.mapper.TransactionMapperV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceV1 {

    private final TransactionMapperV1 transactionMapperV1;
    private final TransactionProducerV1 transactionProducerV1;

    public void sendTransaction(TransactionRequestV1 transactionRequestV1) {

        var event = transactionMapperV1.toEvent(transactionRequestV1);

        transactionProducerV1.send(event);
    }

}
