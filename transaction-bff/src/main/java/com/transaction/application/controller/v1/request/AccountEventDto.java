package com.transaction.application.controller.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record AccountEventDto(
    @Schema(description = "Agency Code", type = "Long", example = "1109")
    @NotNull(message = "Enter the agency code.")
    Long agencyCode,

    @Schema(description = "Account Code", type = "Long", example = "372221")
    @NotNull(message = "Enter the Account code.")
    Long accountCode
) {
}
