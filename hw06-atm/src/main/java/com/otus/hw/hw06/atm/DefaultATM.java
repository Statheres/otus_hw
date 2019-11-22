package com.otus.hw.hw06.atm;

import com.otus.hw.hw06.atm.exceptions.InvalidBanknoteException;
import com.otus.hw.hw06.atm.exceptions.InvalidBanknotesCountException;
import com.otus.hw.hw06.atm.exceptions.InvalidWithdrawSum;
import com.otus.hw.hw06.atm.exceptions.NotEnoughBanknotesException;
import com.otus.hw.hw06.atm.money.Banknote;
import com.otus.hw.hw06.atm.money.MoneyCell;
import com.otus.hw.hw06.atm.withdraw.WithdrawStrategy;

import java.util.List;
import java.util.Map;

public class DefaultATM implements ATM {
    private final WithdrawStrategy withdrawStrategy;
    private final List<MoneyCell> moneyCells;

    public DefaultATM(WithdrawStrategy withdrawStrategy, List<MoneyCell> moneyCells) {
        this.withdrawStrategy = withdrawStrategy;
        this.moneyCells = moneyCells;

        this.moneyCells.sort((o1, o2) -> {
            if (o1.getDenomination() == o2.getDenomination()) {
                return 0;
            }

            return o1.getDenomination() < o2.getDenomination() ? 1 : -1;
        });
    }

    @Override
    public void deposit(Map<Banknote, Integer> banknotes) throws InvalidBanknoteException, InvalidBanknotesCountException {
        for (Map.Entry<Banknote, Integer> entry : banknotes.entrySet()) {
            int banknotesCount = entry.getValue();
            Banknote banknote = entry.getKey();

            if (banknotesCount <= 0) {
                throw new InvalidBanknotesCountException();
            }

            MoneyCell moneyCell = moneyCells.stream()
                    .filter(cell -> cell.getBanknote() == banknote)
                    .findFirst()
                    .orElseThrow(() -> new InvalidBanknoteException(banknote));
            moneyCell.deposit(banknotesCount);
        }
    }

    @Override
    public Map<Banknote, Integer> withdraw(int sum) throws InvalidWithdrawSum, NotEnoughBanknotesException {
        return withdrawStrategy.withdraw(sum, moneyCells);
    }

    @Override
    public int getTotalBalance() {
        return moneyCells.stream().mapToInt(MoneyCell::getBalance).sum();
    }
}
