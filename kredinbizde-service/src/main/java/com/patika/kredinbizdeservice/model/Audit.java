package com.patika.kredinbizdeservice.model;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Audit {

    @Column(name = "create_date",nullable = false)
    private LocalDate createdDate;

    @Column(name = "updated_date",nullable = false)
    private LocalDate updatedDate;
}
