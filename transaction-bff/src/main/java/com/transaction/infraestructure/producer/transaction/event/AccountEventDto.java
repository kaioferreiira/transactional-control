package com.transaction.infraestructure.producer.transaction.event;

import io.swagger.v3.oas.annotations.media.Schema;

public record AccountEventDto(

    @Schema(description = "Agency Code", type = "Long", example = "1109")
    Long agencyCode,

    @Schema(description = "Account Code", type = "Long", example = "372221")
    Long accountCode
) {
}
