package com.kocfinans.creditapplication.controller;

import com.kocfinans.creditapplication.entities.Applicant;
import com.kocfinans.creditapplication.model.CreditApplicationRequest;
import com.kocfinans.creditapplication.service.CreditApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreditApplicationController {

    @Autowired
    private CreditApplicationService creditApplicationService;


    @RequestMapping(value = "/v1/creditapplication/approval", method = RequestMethod.POST)
    public ResponseEntity creditApprovalCheck(@RequestBody CreditApplicationRequest creditApplicationRequest) {
        return ResponseEntity.ok(creditApplicationService.checkCreditApplicationApproval(creditApplicationRequest));
    }

    @GetMapping("/v1/getapplicants")
    Iterable<Applicant> all() {
        return creditApplicationService.findAll();
    }
}
