package com.transaction.application.controller.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Transport object for sending financial transaction")
public record TransactionRequestV1(

    @Schema(description = "transaction amount", type = "BigDecimal", example = "1230.47")
    @NotNull(message = "Inform the value of the transaction")
    BigDecimal value,

    @NotNull(message = "Inform the transaction origin account")
    @Schema(description = "Transaction source account")
    @Valid
    AccountEventDto account,
    @Schema(description = "recipient")
    @Valid
    RecipientEventDto recipient,
    @NotNull(message = "Inform the transaction type")
    @Schema(description = "typeTransaction", type = "string", allowableValues = {"PIX", "DOC"})
    TypeTransactionEnum typeTransaction,
    @Schema(description = "transaction status", type = "string", allowableValues = {"NOT_ANALYZED", "ANALYZED"})
    EventSituation situation

) {
}
