package com.example.sharded_saga_wallet.entities;

public enum SagaStatus {
    STARTED,
    RUNNING,
    COMPLETED,
    FAILED,
    COMPENSATING,
    COMPENSATED
}
