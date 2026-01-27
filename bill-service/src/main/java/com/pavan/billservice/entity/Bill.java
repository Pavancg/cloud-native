package com.pavan.billservice.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name="bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private BigDecimal amount;

    private LocalDate dueDate;

    private boolean paid = false;

    @Column(nullable = false)
    private UUID userId;   // ownership
}
