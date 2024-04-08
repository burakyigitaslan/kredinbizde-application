package com.patika.kredinbizdeservice.service.interfaces;

import com.patika.kredinbizdeservice.model.Campaign;
import com.patika.kredinbizdeservice.model.CreditCard;

import java.util.List;
import java.util.Optional;

public interface ICreditCardService {
    CreditCard createCreditCard(CreditCard creditCard);
    List<CreditCard> getAllCreditCards();
    Optional<CreditCard> getCreditCardById(Long id);
    List<CreditCard> getCreditCardsByBankName(String bankName);
    CreditCard updateCreditCard(CreditCard creditCard);
    void deleteCreditCard(Long id);
}
