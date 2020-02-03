package com.kocfinans.creditapplication.service;

import com.kocfinans.creditapplication.client.creditscore.CreditScoreServiceClient;
import com.kocfinans.creditapplication.client.sms.SmsClient;
import com.kocfinans.creditapplication.entities.Applicant;
import com.kocfinans.creditapplication.model.Constants;
import com.kocfinans.creditapplication.model.CreditApplicationRequest;
import com.kocfinans.creditapplication.model.CreditApplicationResponse;
import com.kocfinans.creditapplication.repository.CreditApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DefaultCreditApplicationService implements CreditApplicationService {

    @Autowired
    private CreditApplicationRepository repository;

    @Autowired
    private SmsClient smsClient;

    @Override
    public CreditApplicationResponse checkCreditApplicationApproval(CreditApplicationRequest request) {
        final BigDecimal creditScore = CreditScoreServiceClient.getCreditScore(request.getIdentityNo());

        final Boolean status = checkCreditApplicationStatus(creditScore);

        if (!status)
            return CreditApplicationResponse
                    .builder()
                    .isSuccessful(false)
                    .message(Constants.CREDIT_APPLICATION_UNSUCCESSFUL_RESPONSE)
                    .build();


        final BigDecimal creditLimit = determineCreditLimit(creditScore, request.getMonthlyIncome());

        repositoryPersistHandler(request, creditLimit);
        smsClient.sendSms(request.getPhoneNumber());

        return CreditApplicationResponse.builder()
                .message(Constants.CREDIT_APPLICATION_SUCCESSFUL_RESPONSE)
                .isSuccessful(true)
                .limit(creditLimit)
                .build();
    }

    public Boolean checkCreditApplicationStatus(final BigDecimal creditScore) {
        if (creditScore.compareTo(Constants.MIN_CREDIT_SCORE) < 0)
            return Boolean.FALSE;

        return Boolean.TRUE;
    }

    public BigDecimal determineCreditLimit(final BigDecimal creditScore, final BigDecimal monthlyIncome) {
        if (creditScore.compareTo(Constants.MAX_CREDIT_SCORE) < 0)
            return monthlyIncome.multiply(Constants.creditLimitFactor);


        return monthlyIncome.multiply(Constants.creditLimitFactor);
    }

    public void repositoryPersistHandler(final CreditApplicationRequest request, final BigDecimal creditLimit) {

        final Applicant applicant = new Applicant();
        applicant.setIdentityNo(request.getIdentityNo());
        applicant.setMonthlyIncome(request.getMonthlyIncome());
        applicant.setName(request.getName());
        applicant.setSurname(request.getSurname());
        applicant.setCreditLimit(creditLimit);
        repository.save(applicant);
    }

    @Override
    public Iterable<Applicant> findAll() {
        return repository.findAll();
    }
}
