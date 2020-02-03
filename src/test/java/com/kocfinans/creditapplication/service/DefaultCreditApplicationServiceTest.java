package com.kocfinans.creditapplication.service;

import com.kocfinans.creditapplication.client.creditscore.IdentityNoNotFoundException;
import com.kocfinans.creditapplication.model.Constants;
import com.kocfinans.creditapplication.model.CreditApplicationRequest;
import com.kocfinans.creditapplication.model.CreditApplicationResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultCreditApplicationServiceTest {

    private DefaultCreditApplicationService creditApplicationService;


    @Test
    public void testCheckCreditApplicationApprovalThrowsCorrectException(){

        creditApplicationService = new DefaultCreditApplicationService();

        final CreditApplicationRequest request = new CreditApplicationRequest();
        request.setIdentityNo("131313131");
        request.setMonthlyIncome(BigDecimal.valueOf(1000));
        request.setName("Hasan");
        request.setPhoneNumber("31314131");
        request.setSurname("Ucar");

        final CreditApplicationResponse expectedResult = CreditApplicationResponse.builder()
                .isSuccessful(Boolean.FALSE)
                .message(Constants.CREDIT_APPLICATION_UNSUCCESSFUL_RESPONSE)
                .build();

        IdentityNoNotFoundException thrown =
                assertThrows(IdentityNoNotFoundException.class,
                        () -> creditApplicationService.checkCreditApplicationApproval(request));

    }

    @Test
    public void testCheckCreditApplicationApprovalReturnUnsuccessfulCreditResponse(){
        creditApplicationService = new DefaultCreditApplicationService();

        final CreditApplicationRequest request = new CreditApplicationRequest();
        request.setIdentityNo("19282190784");
        request.setMonthlyIncome(BigDecimal.valueOf(10));
        request.setName("Hasan");
        request.setPhoneNumber("213131313");
        request.setSurname("Ucar");

        final CreditApplicationResponse expectedResult = CreditApplicationResponse.builder()
                .isSuccessful(Boolean.FALSE)
                .message(Constants.CREDIT_APPLICATION_UNSUCCESSFUL_RESPONSE)
                .build();

        final CreditApplicationResponse result = creditApplicationService.checkCreditApplicationApproval(request);

        assertThat(result.getIsSuccessful(),is(equalTo(expectedResult.getIsSuccessful())));
        assertThat(result.getLimit(),is(equalTo(expectedResult.getLimit())));
        assertThat(result.getMessage(),is(equalTo(expectedResult.getMessage())));
    }



}