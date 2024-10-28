package br.com.microservices.orchestrated.orchestratorservice.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETopics {

    START_SAGA("start-saga"),
    BASE_ORCHESTRATOR("orchestrator"),
    FINISH_SUCCESS("finish-success"),
    FINISH_FAIL("finish-fail"),
    PRODUCT_VALIDATION_SUCCESS("product-validation-success"), // Corrigido de PRODUTCT para PRODUCT
    PRODUCT_VALIDATION_FAIL("product-validation-fail"),       // Corrigido de PRODUTCT para PRODUCT
    PAYMENT_SUCCESS("payment-success"),
    PAYMENT_FAIL("payment-fail"),
    INVENTOY_SUCCESS("inventory-success"),
    INVENTOY_FAIL("inventory-fail"),
    NOTIFY_ENDING("notify-ending");

    private final String topic;
}
