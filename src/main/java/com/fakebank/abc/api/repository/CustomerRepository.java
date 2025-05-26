package com.fakebank.abc.api.repository;

import com.fakebank.abc.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}