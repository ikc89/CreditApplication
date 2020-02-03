package com.kocfinans.creditapplication.client.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsOperatorClient implements SmsClient {

    private static final String phoneNumberRegex = "^[+0-9-\\(\\)\\s]*{6,14}$";

    @Override
    public void sendSms(final String phoneNumber) {

        if(!validatePhoneNumber(phoneNumber))
            throw PhoneNumberUnAcceptableException.create(phoneNumber);


        log.info("Sms send to phoneNumber: " + phoneNumber);
    }

    @Override
    public Boolean validatePhoneNumber(final String phoneNumber) {
        if(phoneNumber == null)
        return Boolean.FALSE;

        return phoneNumber.matches(phoneNumberRegex);
    }
}
