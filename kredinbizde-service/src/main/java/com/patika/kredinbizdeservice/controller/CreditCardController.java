package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditcards")
public class CreditCardController {

    private final CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping
    public ResponseEntity<List<CreditCard>> getAllCreditCards() {
        List<CreditCard> creditCards = creditCardService.getAllCreditCards();
        return new ResponseEntity<>(creditCards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCard> getCreditCardById(@PathVariable Long id) {
        CreditCard creditCard = creditCardService.getCreditCardById(id)
                .orElse(null);
        return creditCard != null ? ResponseEntity.ok(creditCard) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CreditCard> createCreditCard(@RequestBody CreditCard creditCard) {
        CreditCard createdCreditCard = creditCardService.createCreditCard(creditCard);
        return new ResponseEntity<>(createdCreditCard, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> updateCreditCard(@PathVariable Long id, @RequestBody CreditCard creditCard) {
        creditCard.setId(id);
        CreditCard updatedCreditCard = creditCardService.updateCreditCard(creditCard);
        return new ResponseEntity<>(updatedCreditCard, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable Long id) {
        creditCardService.deleteCreditCard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byBankName/{bankName}")
    public ResponseEntity<List<CreditCard>> getCreditCardsByBankName(@PathVariable String bankName) {
        List<CreditCard> creditCards = creditCardService.getCreditCardsByBankName(bankName);
        return creditCards.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(creditCards);
    }
}