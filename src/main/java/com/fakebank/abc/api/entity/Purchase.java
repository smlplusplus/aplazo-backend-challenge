package com.fakebank.abc.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @ManyToOne
    @Getter
    @Setter
    private Client client;
    @Getter
    @Setter
    private double amount;
    @Getter
    @Setter
    private LocalDate purchaseDate;
}