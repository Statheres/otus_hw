package com.otus.hw.hw06.exceptions;

public class NotEnoughBanknotesException extends ATMException {
    public NotEnoughBanknotesException(int denomination) {
        super(String.format("Not enough money with denomination '%d'", denomination));
    }
}
