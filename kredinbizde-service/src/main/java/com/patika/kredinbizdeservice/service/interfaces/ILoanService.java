package com.patika.kredinbizdeservice.service.interfaces;

import com.patika.kredinbizdeservice.model.Loan;

import java.util.List;
import java.util.Optional;

public interface ILoanService {
    Loan createLoan(Loan loan);
    List<Loan> getAllLoans();
    Optional<Loan> getLoanById(Long id);
    Loan updateLoan(Loan loan);
    void deleteLoan(Long id);
}
