package com.otus.hw.hw06.atm.exceptions;

import com.otus.hw.hw06.atm.money.Banknote;

public class InvalidBanknoteException extends ATMException {
    public InvalidBanknoteException(Banknote banknote) {
        super(String.format("Invalid denomination '%s'", banknote));
    }
}
