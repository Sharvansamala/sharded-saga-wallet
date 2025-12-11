package com.example.sharded_saga_wallet.entities.enums;

public enum StepStatus {
    PENDING,
    RUNNING,
    COMPLETED,
    COMPENSATING,
    COMPENSATED,
    SKIPPED
}
