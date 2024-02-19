package com.toure.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerResponseDto {
    private Long Id;
    private String First_Name;
    private String Last_Name;
    private String Email;
    private String Profile;
    private LocalDate CreatedAt;
    private Boolean isEnabled;
}
