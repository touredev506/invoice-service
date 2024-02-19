package com.toure.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Transient;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Invoice {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Label;
    private int Quantity;
    private int Unit_Price;
    private LocalDate CreatedAt;
    private int Total_Price;
    private Long CustomerId;
    @Transient
    private Customer customer;
}
