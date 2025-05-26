package com.fakebank.abc.api.repository;

import com.fakebank.abc.api.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}