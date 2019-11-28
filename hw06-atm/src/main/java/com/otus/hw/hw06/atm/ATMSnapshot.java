package com.otus.hw.hw06.atm;

import com.otus.hw.hw06.atm.money.MoneyCell;

import java.util.ArrayList;
import java.util.List;

class ATMSnapshot {
    private final List<MoneyCell> moneyCells;

    ATMSnapshot(ATM atm) {
        moneyCells = new ArrayList<>();
        try {
            for (MoneyCell cell : atm.getMoneyCells()) {
                moneyCells.add(cell.clone());
            }
        }
        catch (CloneNotSupportedException e) {
            System.err.println(e);
        }
    }

    List<MoneyCell> getMoneyCells() {
        return moneyCells;
    }
}
