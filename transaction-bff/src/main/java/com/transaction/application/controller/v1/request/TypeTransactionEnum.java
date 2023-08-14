package com.transaction.application.controller.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public enum TypeTransactionEnum {
    PIX,
    TED,
    DOC;
}
