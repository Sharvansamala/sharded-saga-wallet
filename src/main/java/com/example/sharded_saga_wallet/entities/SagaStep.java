package com.example.sharded_saga_wallet.entities;

import com.example.sharded_saga_wallet.entities.enums.StepStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "saga_step")
public class SagaStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "saga_instance_id",nullable = false)
    private Long sagaInstanceId;

    @Column(name = "stepstatus",nullable = false)
    private StepStatus status;

    @Column(name = "error_message",nullable = false)
    private String errorMessage;

    @Column(name = "step_data",nullable = false)
    private String stepData;
}
