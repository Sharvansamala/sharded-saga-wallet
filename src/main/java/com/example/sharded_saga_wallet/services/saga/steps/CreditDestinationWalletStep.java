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
public class CreditDestinationWalletStep implements SagaStep {

    private final WalletRepository walletRepository;

    @Override
    @Transactional
    public boolean execute(SagaContext context) {

        //Step 1: get wallet if from context
        Long toWalletId = context.getLong("toWalletId");
        BigDecimal amount = context.getBigDecimal("amount");
        log.info("Crediting destination wallet {} with amount {}", toWalletId, amount);

        //step 2 : fetch dest wall with lock
        Wallet wallet = walletRepository.findByIdWithLock(toWalletId).orElseThrow(() -> new RuntimeException("Wallet not found"));
        log.info("Wallet fetched with balance: {}", wallet.getBalance());
        context.putData("originalToWalletBalance", wallet.getBalance());

//        wallet.credit(amount);

        walletRepository.save(wallet);
        log.info("Wallet saved with balance: {}", wallet.getBalance());
        context.putData("toWalletBalanceAfterCredit", wallet.getBalance());

        log.info("Credit Destination Wallet Step completed successfully");
        return true;
    }

    @Override
    @Transactional
    public boolean compensate(SagaContext context) {
        Long toWalletId = context.getLong("toWalletId");
        BigDecimal amount = context.getBigDecimal("amount");
        log.info("Compensation credit ofs destination wallet {} with amount {}", toWalletId, amount);


        Wallet wallet = walletRepository.findByIdWithLock(toWalletId)
                .orElseThrow(() -> new RuntimeException("Wallet Not found"));

        log.info("Wallet fetched with balance: {}", wallet.getBalance());

        wallet.debit(amount);
        walletRepository.save(wallet);
        log.info("Wallet saved with balance: {}", wallet.getBalance());
        context.putData("toWalletBalanceAfterCreditCompensation", wallet.getBalance());

        log.info("Credit Compensation Wallet Step completed successfully");
        return true;
    }

    @Override
    public String getStepName() {
        return "CreditDestinationWalletStep";
    }
}
