package com.kocfinans.creditapplication.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CreditApplicationResponse {
    private Boolean isSuccessful;
    private BigDecimal limit;
    private String message;
}
