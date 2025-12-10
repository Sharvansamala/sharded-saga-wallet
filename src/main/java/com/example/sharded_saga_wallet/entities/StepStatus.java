package com.example.sharded_saga_wallet.entities;

public enum StepStatus {
    PENDING,
    RUNNING,
    COMPLETED,
    COMPENSATING,
    COMPENSATED,
    SKIPPED
}
