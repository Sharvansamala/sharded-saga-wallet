package com.example.sharded_saga_wallet.repositories;

import com.example.sharded_saga_wallet.entities.Transaction;
import com.example.sharded_saga_wallet.entities.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByFromWalletId(Long fromWalletId);

    List<Transaction> findByToWalletId(Long toWalletId);  // credit transactions

    @Query("SELECT t FROM Transaction t WHERE t.fromWalletId = :id OR t.toWalletId = :id")
    List<Transaction> findByWalletId(@Param("id") Long walletId);  // all transactions

    List<Transaction> findByStatus(TransactionStatus status);

    List<Transaction> findBySagaInstanceId(Long sagaInstanceId);
}
