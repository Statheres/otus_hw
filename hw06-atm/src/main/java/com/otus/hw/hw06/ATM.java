package com.otus.hw.hw06;

import com.otus.hw.hw06.exceptions.InvalidBanknotesCountException;
import com.otus.hw.hw06.exceptions.InvalidDenominationException;
import com.otus.hw.hw06.exceptions.InvalidWithdrawSum;
import com.otus.hw.hw06.exceptions.NotEnoughBanknotesException;

import java.util.List;
import java.util.Map;

public class ATM {
    private final WithdrawStrategy withdrawStrategy;
    private final List<MoneyCell> moneyCells;

    public ATM(WithdrawStrategy withdrawStrategy, List<MoneyCell> moneyCells) {
        this.withdrawStrategy = withdrawStrategy;
        this.moneyCells = moneyCells;

        this.moneyCells.sort((o1, o2) -> {
            if (o1.getDenomination() == o2.getDenomination()) {
                return 0;
            }

            return o1.getDenomination() < o2.getDenomination() ? 1 : -1;
        });
    }

    public void deposit(int denomination, int banknotesCount) throws InvalidDenominationException, InvalidBanknotesCountException {
        if (banknotesCount <= 0) {
            throw new InvalidBanknotesCountException();
        }

        MoneyCell moneyCell = moneyCells.stream()
                .filter(cell -> cell.getDenomination() == denomination)
                .findFirst()
                .orElseThrow(() -> new InvalidDenominationException(denomination));
        moneyCell.deposit(banknotesCount);;
    }

    public Map<Integer, Integer> withdraw(int sum) throws InvalidWithdrawSum, NotEnoughBanknotesException {
        return withdrawStrategy.withdraw(sum, moneyCells);
    }

    public int getTotalBalance() {
        return moneyCells.stream().mapToInt(MoneyCell::getBalance).sum();
    }
}
