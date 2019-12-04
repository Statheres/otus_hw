package com.otus.hw.hw06.atm.money;

import com.otus.hw.hw06.atm.exceptions.NotEnoughBanknotesException;

public class DefaultMoneyCell implements MoneyCell {
    private final Banknote banknote;

    private int banknotesCount;

    public DefaultMoneyCell(Banknote banknote) {
        this(banknote, 0);
    }

    public DefaultMoneyCell(Banknote banknote, int banknotesCount) {
        this.banknote = banknote;
        this.banknotesCount = banknotesCount;
    }

    @Override
    public Banknote getBanknote() {
        return banknote;
    }

    @Override
    public int getDenomination() {
        return banknote.getDenomination();
    }

    @Override
    public int getBanknotesCount() {
        return banknotesCount;
    }

    @Override
    public int getBalance() {
        return  banknote.getDenomination() * banknotesCount;
    }

    @Override
    public void deposit(int banknotesCount) {
        this.banknotesCount += banknotesCount;
    }

    @Override
    public int withdraw(int banknotesCount) throws NotEnoughBanknotesException {
        return this.banknotesCount -= banknotesCount;
    }

    @Override
    public MoneyCell clone() throws CloneNotSupportedException {
        return (MoneyCell)super.clone();
    }
}
