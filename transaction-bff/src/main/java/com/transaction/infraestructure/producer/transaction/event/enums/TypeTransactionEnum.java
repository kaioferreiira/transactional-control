package com.transaction.infraestructure.producer.transaction.event.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.util.stream.Stream.of;

@Schema
@Getter
@AllArgsConstructor
public enum TypeTransactionEnum {

    PIX,
    TED,
    DOC,
    INVALID_TYPE;

    public static TypeTransactionEnum validtedType(final String tipoSeguro) {

        return of(values())
            .filter(produtosSeguradorasEnum -> produtosSeguradorasEnum.name().equalsIgnoreCase(tipoSeguro))
            .findFirst().orElse(TypeTransactionEnum.INVALID_TYPE);
    }

}
