package com.patika.kredinbizdeservice.model;

import com.patika.kredinbizdeservice.model.constant.CreditCardEntityColumnConstants;
import com.patika.kredinbizdeservice.model.constant.UserEntityColumnConstants;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CreditCard extends Audit implements Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = CreditCardEntityColumnConstants.NAME, nullable = false)
    private String name;

    @Column(name = CreditCardEntityColumnConstants.FEE, nullable = false)
    private BigDecimal fee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = CreditCardEntityColumnConstants.BANK,nullable = false)
    private Bank bank;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "creditCard")
    private List<Campaign> campaignList;
}
