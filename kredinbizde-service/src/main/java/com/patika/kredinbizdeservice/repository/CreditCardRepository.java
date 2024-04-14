package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    List<CreditCard> findAllByBank_Name(String bankName);

}
