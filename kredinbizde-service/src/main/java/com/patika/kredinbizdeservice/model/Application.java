package com.patika.kredinbizdeservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patika.kredinbizdeservice.enums.ApplicationStatus;
import com.patika.kredinbizdeservice.model.constant.ApplicationEntityColumnConstants;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Application extends Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = ApplicationEntityColumnConstants.LOAN,nullable = false)
    private Loan loan;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = ApplicationEntityColumnConstants.USER,nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = ApplicationEntityColumnConstants.APPLICATION_STATUS,nullable = false)
    private ApplicationStatus applicationStatus;

}
