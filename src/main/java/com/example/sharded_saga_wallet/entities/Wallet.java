package com.example.sharded_saga_wallet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(name="is_active",nullable = false)
    private boolean isActive;

    @Column(name = "balance",nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;


    public boolean hasSufficientBalance(BigDecimal amount){
        return balance.compareTo(amount)>=0;
    }

    public void debit(BigDecimal amount){
        if(!hasSufficientBalance(amount)){
            throw  new IllegalArgumentException("Balance not sufficient");
        }
        balance.subtract(amount);
    }
    public void credit(BigDecimal amount){
        balance.add(amount);
    }

}
