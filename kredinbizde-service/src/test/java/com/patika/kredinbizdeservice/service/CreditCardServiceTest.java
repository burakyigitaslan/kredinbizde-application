package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.model.Bank;
import com.patika.kredinbizdeservice.model.CreditCard;
import com.patika.kredinbizdeservice.repository.CreditCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreditCardServiceTest {

    @Mock
    private CreditCardRepository creditCardRepository;

    @InjectMocks
    private CreditCardService creditCardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCreditCard_Success() {
        CreditCard creditCard = new CreditCard();
        creditCard.setName("Test Card");
        creditCard.setFee(BigDecimal.valueOf(100));

        when(creditCardRepository.save(any(CreditCard.class))).thenReturn(creditCard);

        CreditCard savedCreditCard = creditCardService.createCreditCard(creditCard);

        assertNotNull(savedCreditCard);
        assertEquals("Test Card", savedCreditCard.getName());
        assertEquals(BigDecimal.valueOf(100), savedCreditCard.getFee());

        verify(creditCardRepository, times(1)).save(creditCard);
    }

    @Test
    void getAllCreditCards_Success() {
        CreditCard creditCard1 = new CreditCard();
        creditCard1.setName("Card 1");

        CreditCard creditCard2 = new CreditCard();
        creditCard2.setName("Card 2");

        when(creditCardRepository.findAll()).thenReturn(Arrays.asList(creditCard1, creditCard2));

        List<CreditCard> creditCards = creditCardService.getAllCreditCards();

        assertNotNull(creditCards);
        assertEquals(2, creditCards.size());

        verify(creditCardRepository, times(1)).findAll();
    }

    @Test
    void getCreditCardById_Exists_Success() {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(1L);

        when(creditCardRepository.findById(1L)).thenReturn(Optional.of(creditCard));

        Optional<CreditCard> optionalCreditCard = creditCardService.getCreditCardById(1L);

        assertTrue(optionalCreditCard.isPresent());
        assertEquals(1L, optionalCreditCard.get().getId());

        verify(creditCardRepository, times(1)).findById(1L);
    }

    @Test
    void getCreditCardById_NotExists_Success() {
        when(creditCardRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<CreditCard> optionalCreditCard = creditCardService.getCreditCardById(1L);

        assertFalse(optionalCreditCard.isPresent());

        verify(creditCardRepository, times(1)).findById(1L);
    }

    @Test
    void getCreditCardsByBankName_Success() {
        Bank bank = new Bank();
        bank.setName("Test Bank");

        CreditCard creditCard1 = new CreditCard();
        creditCard1.setName("Card 1");
        creditCard1.setBank(bank);

        CreditCard creditCard2 = new CreditCard();
        creditCard2.setName("Card 2");
        creditCard2.setBank(bank);

        when(creditCardRepository.findAllByBank_Name("Test Bank")).thenAnswer(new Answer<List<CreditCard>>() {
            @Override
            public List<CreditCard> answer(InvocationOnMock invocation) {
                return Arrays.asList(creditCard1, creditCard2);
            }
        });

        List<CreditCard> creditCards = creditCardService.getCreditCardsByBankName("Test Bank");

        assertNotNull(creditCards);
        assertEquals(2, creditCards.size());

        verify(creditCardRepository, times(1)).findAllByBank_Name("Test Bank");
    }

    @Test
    void updateCreditCard_Success() {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(1L);
        creditCard.setName("Test Card");
        creditCard.setFee(BigDecimal.valueOf(100));

        when(creditCardRepository.save(any(CreditCard.class))).thenReturn(creditCard);

        CreditCard updatedCreditCard = creditCardService.updateCreditCard(creditCard);

        assertNotNull(updatedCreditCard);
        assertEquals(1L, updatedCreditCard.getId());
        assertEquals("Test Card", updatedCreditCard.getName());
        assertEquals(BigDecimal.valueOf(100), updatedCreditCard.getFee());

        verify(creditCardRepository, times(1)).save(creditCard);
    }

    @Test
    void deleteCreditCard_Success() {
        doNothing().when(creditCardRepository).deleteById(1L);

        creditCardService.deleteCreditCard(1L);

        verify(creditCardRepository, times(1)).deleteById(1L);
    }
}