package com.transaction.infraestructure.producer.transaction.event;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
public class RecipientEventDto implements Serializable {

    private static final long serialVersionUID = 2806421543985360625L;

    @Schema(description = "Beneficiary CPF", type = "Long", example = "10298823233")
    @NotNull(message = "Inform the CPF.")
    private Long cpf;

    @NotNull(message = "Inform the destination bank code.")
    @Schema(description = "codeBank", type = "Long", example = "137")
    private Long codeBank;

    @NotNull(message = "Inform the destination agency.")
    @Schema(description = "destination agency", type = "String", example = "1108")
    private String agency;

    @NotNull(message = "Inform the target account.")
    @Schema(description = "target account", type = "String", example = "55982")
    private String account;

    @NotNull(message = "Inform the name of the Beneficiary.")
    @Schema(description = "Name of Beneficiary", type = "String", example = "Ciclano Beltrano de tal")
    private String favoredName;


}