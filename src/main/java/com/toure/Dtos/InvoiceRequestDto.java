package com.toure.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class InvoiceRequestDto {
    private String Label;
    private int Quantity;
    private int Unit_Price;
    private Long CustomerId;
}
