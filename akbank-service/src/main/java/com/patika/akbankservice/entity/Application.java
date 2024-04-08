package com.patika.akbankservice.entity;

import com.patika.akbankservice.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;


    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_status",nullable = false)
    private ApplicationStatus applicationStatus;
}

