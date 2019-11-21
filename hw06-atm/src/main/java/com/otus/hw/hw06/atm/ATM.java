package com.otus.hw.hw06.atm;

import com.otus.hw.hw06.atm.exceptions.InvalidBanknoteException;
import com.otus.hw.hw06.atm.exceptions.InvalidBanknotesCountException;
import com.otus.hw.hw06.atm.exceptions.InvalidWithdrawSum;
import com.otus.hw.hw06.atm.exceptions.NotEnoughBanknotesException;
import com.otus.hw.hw06.atm.money.Banknote;

import java.util.Map;

public interface ATM {
    void deposit(Map<Banknote, Integer> banknotes) throws InvalidBanknoteException, InvalidBanknotesCountException;

    Map<Banknote, Integer> withdraw(int sum) throws InvalidWithdrawSum, NotEnoughBanknotesException;

    int getTotalBalance();
}
