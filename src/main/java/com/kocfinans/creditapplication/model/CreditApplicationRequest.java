package com.kocfinans.creditapplication.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class CreditApplicationRequest {

    @NotBlank(message = "IdentityNo is mandatory!")
    private String identityNo;
    private String name;
    private String surname;
    @NotBlank(message = "Monthly Income is mandatory !")
    private BigDecimal monthlyIncome;
    private String phoneNumber;
}
