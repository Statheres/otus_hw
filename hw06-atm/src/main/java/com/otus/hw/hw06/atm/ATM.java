package com.otus.hw.hw06.atm;

import com.otus.hw.hw06.atm.exceptions.*;
import com.otus.hw.hw06.atm.money.Banknote;
import com.otus.hw.hw06.atm.money.MoneyCell;

import java.util.List;
import java.util.Map;
import java.util.Observer;

public interface ATM extends Observer {
    void deposit(Map<Banknote, Integer> banknotes) throws InvalidBanknoteException, InvalidBanknotesCountException;

    Map<Banknote, Integer> withdraw(int sum) throws InvalidWithdrawSum, NotEnoughBanknotesException;

    int getTotalBalance();

    void reset() throws InvalidSnapshotException;

    List<MoneyCell> getMoneyCells();
}
