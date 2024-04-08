package com.patika.kredinbizdeservice.model;

import com.patika.kredinbizdeservice.enums.SectorType;
import com.patika.kredinbizdeservice.model.constant.CampaignEntityColumnConstants;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Campaign extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = CampaignEntityColumnConstants.TITLE, nullable = false,unique = true)
    private String title;

    @Column(name = CampaignEntityColumnConstants.CONTENT, nullable = false)
    private String content;

    @Column(name = CampaignEntityColumnConstants.DUE_DATE, nullable = false)
    private LocalDate dueDate;

    @Column(name = CampaignEntityColumnConstants.SECTOR, nullable = false)
    private SectorType sector;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = CampaignEntityColumnConstants.CREDIT_CARD, nullable = false)
    private CreditCard creditCard;

}
