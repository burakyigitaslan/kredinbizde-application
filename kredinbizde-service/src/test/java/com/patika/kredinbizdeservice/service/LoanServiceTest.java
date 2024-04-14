package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.model.Loan;
import com.patika.kredinbizdeservice.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createLoan_Success() {
        Loan loan = new Loan();
        loan.setAmount(BigDecimal.valueOf(10000));

        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Loan savedLoan = loanService.createLoan(loan);

        assertNotNull(savedLoan);
        assertEquals(BigDecimal.valueOf(10000), savedLoan.getAmount());

        verify(loanRepository, times(1)).save(loan);
    }

    @Test
    void getAllLoans_Success() {
        Loan loan1 = new Loan();
        loan1.setAmount(BigDecimal.valueOf(5000));

        Loan loan2 = new Loan();
        loan2.setAmount(BigDecimal.valueOf(8000));

        when(loanRepository.findAll()).thenReturn(Arrays.asList(loan1, loan2));

        List<Loan> loans = loanService.getAllLoans();

        assertNotNull(loans);
        assertEquals(2, loans.size());

        verify(loanRepository, times(1)).findAll();
    }

    @Test
    void getLoanById_Exists_Success() {
        Loan loan = new Loan();
        loan.setId(1L);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        Optional<Loan> optionalLoan = loanService.getLoanById(1L);

        assertTrue(optionalLoan.isPresent());
        assertEquals(1L, optionalLoan.get().getId());

        verify(loanRepository, times(1)).findById(1L);
    }

    @Test
    void getLoanById_NotExists_Success() {
        when(loanRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Loan> optionalLoan = loanService.getLoanById(1L);

        assertFalse(optionalLoan.isPresent());

        verify(loanRepository, times(1)).findById(1L);
    }

    @Test
    void updateLoan_Success() {
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setAmount(BigDecimal.valueOf(10000));

        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Loan updatedLoan = loanService.updateLoan(loan);

        assertNotNull(updatedLoan);
        assertEquals(1L, updatedLoan.getId());
        assertEquals(BigDecimal.valueOf(10000), updatedLoan.getAmount());

        verify(loanRepository, times(1)).save(loan);
    }

    @Test
    void deleteLoan_Success() {
        doNothing().when(loanRepository).deleteById(1L);

        loanService.deleteLoan(1L);

        verify(loanRepository, times(1)).deleteById(1L);
    }
}
