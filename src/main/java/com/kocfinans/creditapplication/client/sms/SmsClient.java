package com.kocfinans.creditapplication.client.sms;


public interface SmsClient {
     void sendSms(final String phoneNumber);
    Boolean validatePhoneNumber(final String phoneNumber);
}
