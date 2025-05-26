package com.fakebank.abc.api.controller;

import com.fakebank.abc.api.dto.LoanRequest;
import com.fakebank.abc.api.dto.LoanResponse;
import com.fakebank.abc.api.exception.BnplApiException;
import com.fakebank.abc.api.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(@RequestBody LoanRequest request) throws BnplApiException {
        LoanResponse response = loanService.createLoan(request);
        return ResponseEntity.created(URI.create("/v1/loans/" + response.getId()))
                .body(response);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanResponse> getLoanById(@PathVariable Long loanId) throws BnplApiException {
        LoanResponse response = loanService.getLoanById(loanId);
        return ResponseEntity.ok(response);
    }
}
