package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
