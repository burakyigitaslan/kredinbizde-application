package com.patika.kredinbizdeservice.model.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanEntityColumnConstants {

    public static final String AMOUNT = "amount";
    public static final String INSTALLMENT = "installment";
    public static final String INTEREST_RATE = "interest_rate";
    public static final String BANK = "bank_id";

    public static final String LOAN_TYPE = "loan_type";
}
