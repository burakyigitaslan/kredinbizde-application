package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.model.Bank;
import com.patika.kredinbizdeservice.repository.BankRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BankServiceTest {

    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private BankService bankService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBank_Success() {
        Bank bank = new Bank();
        bank.setName("Test Bank");

        when(bankRepository.save(any(Bank.class))).thenReturn(bank);

        Bank savedBank = bankService.createBank(bank);

        assertNotNull(savedBank);
        assertEquals("Test Bank", savedBank.getName());

        verify(bankRepository, times(1)).save(bank);
    }

    @Test
    void getAllBanks_Success() {
        Bank bank1 = new Bank();
        bank1.setName("Bank 1");

        Bank bank2 = new Bank();
        bank2.setName("Bank 2");

        when(bankRepository.findAll()).thenReturn(Arrays.asList(bank1, bank2));

        List<Bank> banks = bankService.getAllBanks();

        assertNotNull(banks);
        assertEquals(2, banks.size());

        verify(bankRepository, times(1)).findAll();
    }

    @Test
    void getBankById_Exists_Success() {
        Bank bank = new Bank();
        bank.setId(1L);

        when(bankRepository.findById(1L)).thenReturn(Optional.of(bank));

        Optional<Bank> optionalBank = bankService.getBankById(1L);

        assertTrue(optionalBank.isPresent());
        assertEquals(1L, optionalBank.get().getId());

        verify(bankRepository, times(1)).findById(1L);
    }

    @Test
    void getBankById_NotExists_Success() {
        when(bankRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Bank> optionalBank = bankService.getBankById(1L);

        assertFalse(optionalBank.isPresent());

        verify(bankRepository, times(1)).findById(1L);
    }

    @Test
    void updateBank_Success() {
        Bank bank = new Bank();
        bank.setId(1L);
        bank.setName("Test Bank");

        when(bankRepository.save(any(Bank.class))).thenReturn(bank);

        Bank updatedBank = bankService.updateBank(bank);

        assertNotNull(updatedBank);
        assertEquals(1L, updatedBank.getId());
        assertEquals("Test Bank", updatedBank.getName());

        verify(bankRepository, times(1)).save(bank);
    }

    @Test
    void deleteBank_Success() {
        doNothing().when(bankRepository).deleteById(1L);

        bankService.deleteBank(1L);

        verify(bankRepository, times(1)).deleteById(1L);
    }
}
