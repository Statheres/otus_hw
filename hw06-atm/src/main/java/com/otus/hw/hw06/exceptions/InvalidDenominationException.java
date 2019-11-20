package com.otus.hw.hw06.exceptions;

public class InvalidDenominationException extends ATMException {
    public InvalidDenominationException(int denomination) {
        super(String.format("Invalid denomination '%d'", denomination));
    }
}
