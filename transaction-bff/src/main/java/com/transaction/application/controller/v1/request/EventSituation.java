package com.transaction.application.controller.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public enum EventSituation {

    ANALYZED,
    NOT_ANALYZED,
    IN_HUMAN_ANALYSIS,
    ON_SUSPECTED_FRAUD,
    CONFIRMED_RISK;
}
