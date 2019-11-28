package com.otus.hw.hw06.department.events;

import com.otus.hw.hw06.atm.ATM;

public class ATMResetEvent implements ATMDepartmentEvent {
    @Override
    public void doCommand(ATM atm) {
        atm.reset();
    }
}
