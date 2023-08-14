package com.transaction.infraestructure.producer.transaction.event.mapper;

import com.transaction.application.controller.v1.request.TransactionRequestV1;
import com.transaction.infraestructure.producer.transaction.event.AccountEventDto;
import com.transaction.infraestructure.producer.transaction.event.RecipientEventDto;
import com.transaction.infraestructure.producer.transaction.event.TransactionEventV1;
import com.transaction.infraestructure.producer.transaction.event.enums.EventSituationEnum;
import com.transaction.infraestructure.producer.transaction.event.enums.TypeTransactionEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransactionMapperV1 {
    public TransactionEventV1 toEvent(TransactionRequestV1 transactionRequestV1) {

        if (transactionRequestV1 == null) {
            throw new InternalError("TransactionRequestV1 not be null");
        }

        return TransactionEventV1.builder().
            value(transactionRequestV1.value())
            .codeTransaction(UUID.randomUUID().toString())
            .date(LocalDateTime.now())
            .account(new AccountEventDto(transactionRequestV1.account().agencyCode(), transactionRequestV1.account().agencyCode()))
            .recipient(RecipientEventDto.builder()
                .cpf(transactionRequestV1.recipient().getCpf())
                .codeBank(transactionRequestV1.recipient().getCodeBank())
                .agency(transactionRequestV1.recipient().getAgency())
                .account(transactionRequestV1.recipient().getAccount())
                .favoredName(transactionRequestV1.recipient().getFavoredName())
                .build())
            .typeTransaction(TypeTransactionEnum.validtedType(transactionRequestV1.typeTransaction().name()))
            .situation(EventSituationEnum.validtedType(transactionRequestV1.situation().name()))
            .build();

    }

}