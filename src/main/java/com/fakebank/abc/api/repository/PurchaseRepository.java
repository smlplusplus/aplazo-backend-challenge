package com.fakebank.abc.api.repository;

import com.fakebank.abc.api.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}