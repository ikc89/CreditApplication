package com.kocfinans.creditapplication.service;

import com.kocfinans.creditapplication.entities.Applicant;
import com.kocfinans.creditapplication.model.CreditApplicationRequest;
import com.kocfinans.creditapplication.model.CreditApplicationResponse;

public interface CreditApplicationService {

    CreditApplicationResponse checkCreditApplicationApproval(final CreditApplicationRequest request);

    Iterable<Applicant> findAll();
}
