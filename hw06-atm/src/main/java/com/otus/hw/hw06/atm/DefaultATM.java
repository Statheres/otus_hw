package com.otus.hw.hw06.atm;

import com.otus.hw.hw06.atm.exceptions.*;
import com.otus.hw.hw06.atm.money.Banknote;
import com.otus.hw.hw06.atm.money.MoneyCell;
import com.otus.hw.hw06.atm.withdraw.WithdrawStrategy;
import com.otus.hw.hw06.department.events.ATMDepartmentEvent;

import java.util.List;
import java.util.Map;
import java.util.Observable;

public class DefaultATM implements ATM {
    private final WithdrawStrategy withdrawStrategy;
    private final ATMSnapshot snapshot;
    private List<MoneyCell> moneyCells;

    public DefaultATM(WithdrawStrategy withdrawStrategy, List<MoneyCell> moneyCells) throws InvalidSnapshotException {
        this.withdrawStrategy = withdrawStrategy;
        this.moneyCells = moneyCells;

        this.moneyCells.sort((o1, o2) -> {
            if (o1.getDenomination() == o2.getDenomination()) {
                return 0;
            }

            return o1.getDenomination() < o2.getDenomination() ? 1 : -1;
        });

        snapshot = new ATMSnapshot(this);
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

    @Override
    public void reset() throws InvalidSnapshotException {
        moneyCells = snapshot.getMoneyCells();
    }

    @Override
    public List<MoneyCell> getMoneyCells() {
        return moneyCells;
    }

    @Override
    public void update(Observable o, Object arg) {
        ATMDepartmentEvent event = (ATMDepartmentEvent) arg;
        event.doCommand(this);
    }
}
