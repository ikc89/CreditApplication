package com.kocfinans.creditapplication.client.creditscore;

public class IdentityNoNotFoundException extends IllegalArgumentException {

    private final String message;

    private IdentityNoNotFoundException(String message) {
        this.message = message;
    }

    public static IdentityNoNotFoundException create(String message) {
        return new IdentityNoNotFoundException(message);
    }

    @Override
    public String getMessage() {
        return "Credit Score not found for Identity Number : " + message;
    }
}
