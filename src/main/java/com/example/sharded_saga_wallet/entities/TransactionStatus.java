package com.example.sharded_saga_wallet.entities;

public enum TransactionStatus {
    PENDING,
    SUCCESS,
    FAILED,
    CANCELLED
}
