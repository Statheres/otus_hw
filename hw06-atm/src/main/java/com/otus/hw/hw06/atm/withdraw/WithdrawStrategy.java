package com.otus.hw.hw06.atm.withdraw;

import com.otus.hw.hw06.atm.exceptions.InvalidWithdrawSum;
import com.otus.hw.hw06.atm.exceptions.NotEnoughBanknotesException;
import com.otus.hw.hw06.atm.money.Banknote;
import com.otus.hw.hw06.atm.money.MoneyCell;

import java.util.List;
import java.util.Map;

public interface WithdrawStrategy {
    public Map<Banknote, Integer> withdraw(int sum, List<MoneyCell> moneyCells) throws InvalidWithdrawSum, NotEnoughBanknotesException;
}
