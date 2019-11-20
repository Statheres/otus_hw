package com.otus.hw.hw06;

import com.otus.hw.hw06.exceptions.InvalidWithdrawSum;
import com.otus.hw.hw06.exceptions.NotEnoughBanknotesException;

import java.util.List;
import java.util.Map;

public interface WithdrawStrategy {
    public Map<Integer, Integer> withdraw(int sum, List<MoneyCell> moneyCells) throws InvalidWithdrawSum, NotEnoughBanknotesException;
}
