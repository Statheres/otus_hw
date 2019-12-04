package com.otus.hw.hw06.atm;

import com.otus.hw.hw06.atm.exceptions.InvalidSnapshotException;
import com.otus.hw.hw06.atm.money.MoneyCell;

import java.util.ArrayList;
import java.util.List;

class ATMSnapshot {
    private final List<MoneyCell> moneyCells;

    ATMSnapshot(ATM atm) throws InvalidSnapshotException {
        moneyCells = cloneCells(atm.getMoneyCells());
    }

    List<MoneyCell> getMoneyCells() throws InvalidSnapshotException {
        return cloneCells(moneyCells);
    }

    private List<MoneyCell> cloneCells(List<MoneyCell> moneyCells) throws InvalidSnapshotException {
        try {
            List<MoneyCell> clonedCells = new ArrayList<>();
            for (MoneyCell cell : moneyCells) {
                clonedCells.add(cell.clone());
            }
            return clonedCells;
        }
        catch (CloneNotSupportedException e) {
            InvalidSnapshotException invalidSnapshotException = new InvalidSnapshotException();
            invalidSnapshotException.initCause(e);
            throw invalidSnapshotException;
        }
    }
}
