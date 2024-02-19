package com.toure.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    private Long Id;
    private String First_Name;
    private String Last_Name;
    private String Email;
    private String Profile;
    private LocalDate CreatedAt;
    private Boolean isEnabled;
}
