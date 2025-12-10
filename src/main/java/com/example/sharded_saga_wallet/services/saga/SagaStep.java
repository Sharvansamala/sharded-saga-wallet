package com.example.sharded_saga_wallet.services.saga;

public interface SagaStep {
    boolean execute(SagaContext context);

    boolean compensate(SagaContext context);

    String getStepName();
}
