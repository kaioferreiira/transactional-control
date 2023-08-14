package com.transaction.infraestructure.producer.transaction.event.enums;

import io.swagger.v3.oas.annotations.media.Schema;

import static java.util.stream.Stream.of;

@Schema
public enum EventSituationEnum {

    ANALYZED,
    NOT_ANALYZED,
    IN_HUMAN_ANALYSIS,
    ON_SUSPECTED_FRAUD,
    CONFIRMED_RISK,
    INVALID_TYPE;

    public static EventSituationEnum validtedType(final String tipoSeguro) {

        return of(values())
            .filter(produtosSeguradorasEnum -> produtosSeguradorasEnum.name().equalsIgnoreCase(tipoSeguro))
            .findFirst().orElse(EventSituationEnum.INVALID_TYPE);
    }

}
