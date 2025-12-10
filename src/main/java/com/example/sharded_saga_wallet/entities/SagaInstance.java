package com.example.sharded_saga_wallet.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "saga_instance")
public class SagaInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SagaStatus status = SagaStatus.STARTED;

//    @Type(JsonSubTypes.class)
    @Column(name = "context", columnDefinition = "json")
    private String context;

    @Column(name = "current_step",nullable = false)
    private String currentStep;

}
