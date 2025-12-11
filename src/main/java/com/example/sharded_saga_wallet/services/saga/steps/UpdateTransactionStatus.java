package com.example.sharded_saga_wallet.services.saga.steps;

import com.example.sharded_saga_wallet.entities.Transaction;
import com.example.sharded_saga_wallet.entities.TransactionStatus;
import com.example.sharded_saga_wallet.repositories.TransactionRepository;
import com.example.sharded_saga_wallet.services.saga.SagaContext;
import com.example.sharded_saga_wallet.services.saga.SagaStep;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateTransactionStatus implements SagaStep {

    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public boolean execute(SagaContext context) {
        Long transactionId = context.getLong("transactionId");

        log.info("Updating transaction status for transaction id {}", transactionId);

        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        context.putData("originalTransactionStatus", transaction.getStatus());

        transaction.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);

        context.putData("updatedTransactionStatus", transaction.getStatus());

        log.info("Transaction status updated to SUCCESS for transaction id {}", transactionId);
        return true;
    }

    @Override
    @Transactional
    public boolean compensate(SagaContext context) {

        Long transactionId = context.getLong("transactionId");
        log.info("Compensating transaction status for transaction id {}", transactionId);

        TransactionStatus originalStatus = TransactionStatus.valueOf(context.getString("originalTransactionStatus"));
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        log.info("Reverting transaction status to {} for transaction id {}", originalStatus, transactionId);

        transaction.setStatus(originalStatus);
        transactionRepository.save(transaction);
        context.putData("compensatedTransactionStatus", transaction.getStatus());

        log.info("Transaction status reverted to {} for transaction id {}", originalStatus, transactionId);

        return true;
    }

    @Override
    public String getStepName() {
        return "UpdateTransactionStatus";
    }
}
