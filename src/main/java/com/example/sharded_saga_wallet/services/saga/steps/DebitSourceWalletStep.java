package com.example.sharded_saga_wallet.services.saga.steps;

import com.example.sharded_saga_wallet.entities.Wallet;
import com.example.sharded_saga_wallet.repositories.WalletRepository;
import com.example.sharded_saga_wallet.services.saga.SagaContext;
import com.example.sharded_saga_wallet.services.saga.SagaStep;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class DebitSourceWalletStep implements SagaStep {
    private final WalletRepository walletRepository;

    @Override
    @Transactional
    public boolean execute(SagaContext context) {
        Long fromWalletId = context.getLong("fromWalletId");
        BigDecimal amount = context.getBigDecimal("amount");
        log.info("Debit Source wallet id {}, with amount {}", fromWalletId, amount);

        Wallet wallet = walletRepository.findByIdWithLock(fromWalletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        log.info("Wallet fetched with balance: {}", wallet.getBalance());
        context.putData("originalFromWalletBalance", wallet.getBalance());

        if(!wallet.hasSufficientBalance(amount))
            throw new RuntimeException("Insufficient Balance");

        wallet.debit(amount);

        walletRepository.save(wallet);
        log.info("Wallet saved with balance: {}", wallet.getBalance());
        context.putData("fromWalletBalanceAfterDebit", wallet.getBalance());

        log.info("Debit source wallet completed successfully");

        return true;
    }

    @Override
    public boolean compensate(SagaContext context) {
        Long fromWalletId = context.getLong("fromWalletId");
        BigDecimal amount = context.getBigDecimal("amount");
        log.info("Debit Source wallet id {}, with amount {}", fromWalletId, amount);
        Wallet wallet = walletRepository.findByIdWithLock(fromWalletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
        log.info("Wallet fetched with balance: {}", wallet.getBalance());
        context.putData("originalFromWalletBalance", wallet.getBalance());

        wallet.credit(amount);
        walletRepository.save(wallet);
        log.info("Wallet saved with balance: {}", wallet.getBalance());
        context.putData("fromWalletBalanceAfterCredit", wallet.getBalance());

        log.info("Debit source wallet completed successfully");

        return true;
    }

    @Override
    public String getStepName() {
        return "DebitSourceWalletStep";
    }
}
