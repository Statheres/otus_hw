package com.otus.hw.hw06.atm.money;

import com.otus.hw.hw06.atm.exceptions.NotEnoughBanknotesException;

public interface MoneyCell extends Cloneable {
    Banknote getBanknote();

    int getDenomination();

    int getBanknotesCount();

    int getBalance();

    void deposit(int banknotesCount);

    int withdraw(int banknotesCount) throws NotEnoughBanknotesException;

    MoneyCell clone() throws CloneNotSupportedException;
}
