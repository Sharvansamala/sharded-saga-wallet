package com.example.sharded_saga_wallet.repositories;

import com.example.sharded_saga_wallet.entities.SagaInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SagaInstanceRepository extends JpaRepository<SagaInstance,Long> {


}
