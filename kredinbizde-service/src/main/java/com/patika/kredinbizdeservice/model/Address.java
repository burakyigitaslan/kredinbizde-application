package com.patika.kredinbizdeservice.model;

import com.patika.kredinbizdeservice.model.constant.AddressEntityColumnConstants;
import com.patika.kredinbizdeservice.model.constant.UserEntityColumnConstants;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Address extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = AddressEntityColumnConstants.ADDRESS_TITLE, nullable = false, unique = true)
    private String addressTitle;

    @Column(name = AddressEntityColumnConstants.ADDRESS_DESCRIPTION)
    private String addressDescription;

    @Column(name = AddressEntityColumnConstants.PROVINCE, nullable = false)
    private String province;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = AddressEntityColumnConstants.USER, nullable = false)
    private User user;

}
