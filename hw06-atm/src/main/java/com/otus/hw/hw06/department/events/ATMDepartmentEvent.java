package com.otus.hw.hw06.department.events;

import com.otus.hw.hw06.atm.ATM;

public interface ATMDepartmentEvent {
    void doCommand(ATM atm);
}
