package com.transaction.infraestructure.producer.transaction.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.transaction.infraestructure.producer.transaction.event.enums.EventSituationEnum;
import com.transaction.infraestructure.producer.transaction.event.enums.TypeTransactionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Transport object for sending financial transaction")
@SuperBuilder
@NoArgsConstructor
@Data
public class TransactionEventV1 {

    @Schema(description = "Transaction identification code")
    String codeTransaction;

    @Schema(description = "transaction amount", type = "BigDecimal", example = "1230.47")
    BigDecimal value;

    @Schema(description = "Date transaction")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime date;

    @Schema(description = "Transaction source account")
    AccountEventDto account;

    @Schema(description = "recipient")
    RecipientEventDto recipient;

    @Schema(description = "typeTransaction", type = "string", allowableValues = {"PIX", "DOC"})
    TypeTransactionEnum typeTransaction;

    @Schema(description = "transaction status", type = "string", allowableValues = {"ANALYZED", "NOT_ANALYZED"})
    EventSituationEnum situation;


}
