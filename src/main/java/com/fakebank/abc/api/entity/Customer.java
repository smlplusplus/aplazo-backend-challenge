package com.fakebank.abc.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Column(nullable = false)
    @Setter
    @Getter
    private String firstName;

    @Column(nullable = false)
    @Setter
    @Getter
    private String lastName;

    @Column(nullable = false)
    @Setter
    @Getter
    private String secondLastName;

    @Column(nullable = false)
    @Setter
    @Getter
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    @Setter
    @Getter
    private Double creditLineAmount;

    @Column(nullable = false)
    @Setter
    @Getter
    private Double availableCreditLineAmount;

    @Column(nullable = false)
    @Setter
    @Getter
    private LocalDateTime createdAt;
}