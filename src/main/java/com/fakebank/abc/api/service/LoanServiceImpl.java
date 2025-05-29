package com.fakebank.abc.api.service;


import com.fakebank.abc.api.dto.InstallmentResponse;
import com.fakebank.abc.api.dto.LoanRequest;
import com.fakebank.abc.api.dto.LoanResponse;
import com.fakebank.abc.api.entity.Customer;
import com.fakebank.abc.api.entity.Loan;
import com.fakebank.abc.api.exception.BnplApiException;
import com.fakebank.abc.api.repository.CustomerRepository;
import com.fakebank.abc.api.repository.LoanRepository;
import com.fakebank.abc.api.util.InstallmentStatus;
import com.fakebank.abc.api.util.LoanStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    public LoanServiceImpl(LoanRepository loanRepository, CustomerRepository customerRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public LoanResponse createLoan(LoanRequest request) throws BnplApiException {
        Long customerId = request.getCustomerId();
        double amount = request.getAmount();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BnplApiException("Customer not found"));

        // Validar crédito disponible
        if (amount > customer.getAvailableCreditLineAmount())
            throw new BnplApiException("Insufficient credit line");

        // Calcular esquema de pago y comisión
        double commissionRate = 0.16;
        if (customer.getFirstName().startsWith("C") || customer.getFirstName().startsWith("L") || customer.getFirstName().startsWith("H")) {
            commissionRate = 0.13;
        } else if (customer.getId() > 25) {
            commissionRate = 0.16;
        }
        double commissionAmount = amount * commissionRate;
        double total = amount + commissionAmount;

        // Plan de pagos (5 quincenales)
        List<InstallmentResponse> installments = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            InstallmentResponse installment = new InstallmentResponse();
            installment.setAmount(total / 5);
            installment.setScheduledPaymentDate(LocalDate.now().plusWeeks(i * 2L));
            installment.setStatus(i == 1 ? InstallmentStatus.NEXT : InstallmentStatus.PENDING);
            installments.add(installment);
        }

        Loan loan = new Loan();
        loan.setCustomer(customer);
        loan.setAmount(amount);
        loan.setCreatedAt(LocalDateTime.now());
        loan.setStatus(LoanStatus.ACTIVE);

        loanRepository.save(loan);

        // Actualizar línea de crédito disponible
        customer.setAvailableCreditLineAmount(customer.getAvailableCreditLineAmount() - amount);
        customerRepository.save(customer);

        LoanResponse response = new LoanResponse();
        response.setId(loan.getId());
        response.setCustomerId(customer.getId());
        response.setStatus(loan.getStatus());
        response.setCreatedAt(loan.getCreatedAt());

        LoanResponse.PaymentPlan paymentPlan = new LoanResponse.PaymentPlan();
        paymentPlan.setCommissionAmount(commissionAmount);
        paymentPlan.setInstallments(installments);

        response.setPaymentPlan(paymentPlan);
        return response;
    }

    @Override
    public LoanResponse getLoanById(Long loanId) throws BnplApiException {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new BnplApiException("Loan not found"));
        LoanResponse response = new LoanResponse();
        response.setId(loan.getId());
        response.setCustomerId(loan.getCustomer().getId());
        response.setStatus(loan.getStatus());
        response.setCreatedAt(loan.getCreatedAt());
        return response;
    }
}
