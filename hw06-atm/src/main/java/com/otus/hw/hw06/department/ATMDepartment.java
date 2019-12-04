package com.otus.hw.hw06.department;

import com.otus.hw.hw06.atm.ATM;

public interface ATMDepartment {
    void addATM(ATM atm);

    int getTotalBalance();

    void reset();
}
