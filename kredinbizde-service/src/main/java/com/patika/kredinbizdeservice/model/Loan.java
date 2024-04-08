package com.patika.kredinbizdeservice.model;

import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.model.constant.CreditCardEntityColumnConstants;
import com.patika.kredinbizdeservice.model.constant.LoanEntityColumnConstants;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Loan extends Audit implements Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = LoanEntityColumnConstants.LOAN_TYPE, nullable = false)
    private LoanType loanType;

    @Column(name = LoanEntityColumnConstants.AMOUNT,nullable = false)
    private BigDecimal amount;

    @Column(name = LoanEntityColumnConstants.INSTALLMENT,nullable = false)
    private Integer installment;

    @Column(name = LoanEntityColumnConstants.INTEREST_RATE, nullable = false)
    private Double interestRate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = LoanEntityColumnConstants.BANK,nullable = false)
    private Bank bank;
}
