package com.toure.Dtos;
import com.toure.Model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class InvoiceResponseDto {
    private Long Id;
    private String Label;
    private int Quantity;
    private int Unit_Price;
    private LocalDate CreatedAt;
    private int Total_Price;
    private Long CustomerId;
    private Customer customer;
}
