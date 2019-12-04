package com.otus.hw.hw06.department.events;

import com.otus.hw.hw06.atm.ATM;
import com.otus.hw.hw06.atm.exceptions.InvalidSnapshotException;

public class ATMResetEvent implements ATMDepartmentEvent {
    @Override
    public void doCommand(ATM atm) {
        try {
            atm.reset();
        } catch (InvalidSnapshotException e) {
            e.printStackTrace();
        }
    }
}
