package com.otus.hw.hw06;

import com.otus.hw.hw06.exceptions.NotEnoughBanknotesException;

public class MoneyCell {
    private final int denomination;

    private int banknotesCount;

    public MoneyCell(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }

    public int getBanknotesCount() {
        return banknotesCount;
    }

    public int getBalance() {
        return  denomination * banknotesCount;
    }

    public void deposit(int banknotesCount) {
        this.banknotesCount += banknotesCount;
    }

    public int withdraw(int banknotesCount) throws NotEnoughBanknotesException {
        if (this.banknotesCount < banknotesCount) {
            throw new NotEnoughBanknotesException(denomination);
        }

        return this.banknotesCount -= banknotesCount;
    }
}
