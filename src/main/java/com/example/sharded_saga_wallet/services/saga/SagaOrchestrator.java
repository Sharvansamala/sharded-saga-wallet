package com.example.sharded_saga_wallet.services.saga;

import com.example.sharded_saga_wallet.entities.SagaInstance;

public interface SagaOrchestrator {
    Long startSaga(SagaContext context);

    boolean executeStep(Long sagaInstanceId, String stepName);

    boolean compensateSaga(Long sagaInstanceId, String stepName);

    SagaInstance getSagaInstance(Long sagaInstanceId);

    void compensateSaga(Long sagaInstanceId);

    void failSaga(Long sagaInstanceId);

    void completeSaga(Long sagaInstanceId);
}
