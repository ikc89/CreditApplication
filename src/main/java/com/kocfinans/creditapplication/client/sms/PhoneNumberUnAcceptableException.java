package com.kocfinans.creditapplication.client.sms;

import com.kocfinans.creditapplication.client.creditscore.IdentityNoNotFoundException;

public class PhoneNumberUnAcceptableException extends IllegalArgumentException {

    private final String phoneNumber;

    private PhoneNumberUnAcceptableException(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static PhoneNumberUnAcceptableException create(String phoneNumber) {
        return new PhoneNumberUnAcceptableException(phoneNumber);
    }

    @Override
    public String getMessage() {
        return "Phone number cannot be  : " + phoneNumber;
    }
}
