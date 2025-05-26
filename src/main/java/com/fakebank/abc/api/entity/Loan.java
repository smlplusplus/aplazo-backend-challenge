package com.fakebank.abc.api.entity;

import com.fakebank.abc.api.util.LoanStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @ManyToOne(optional = false)
    @Setter
    @Getter
    private Customer customer;

    @Column(nullable = false)
    @Setter
    @Getter
    private Double amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    @Getter
    private LoanStatus status;

    @Column(nullable = false)
    @Setter
    @Getter
    private LocalDateTime createdAt;
}