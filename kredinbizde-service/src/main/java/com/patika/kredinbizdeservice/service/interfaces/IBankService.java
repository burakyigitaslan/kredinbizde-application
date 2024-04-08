package com.patika.kredinbizdeservice.service.interfaces;

import com.patika.kredinbizdeservice.model.Bank;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IBankService {
    Bank createBank(Bank bank);
    List<Bank> getAllBanks();
    Optional<Bank> getBankById(Long id);
    Bank updateBank(Bank bank);
    void deleteBank(Long id);
}
