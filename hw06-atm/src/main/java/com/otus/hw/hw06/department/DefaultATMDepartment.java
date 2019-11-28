package com.otus.hw.hw06.department;

import com.otus.hw.hw06.atm.ATM;
import com.otus.hw.hw06.department.events.ATMResetEvent;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class DefaultATMDepartment extends Observable implements ATMDepartment {
    private Set<ATM> atms = new HashSet<>();

    @Override
    public void addATM(ATM atm) {
        atms.add(atm);
        addObserver(atm);
    }

    @Override
    public int getTotalBalance() {
        int sum = 0;
        for (ATM atm : atms) {
            sum += atm.getTotalBalance();
        }
        return atms.stream().mapToInt(ATM::getTotalBalance).sum();
    }

    @Override
    public void reset() {
        setChanged();
        notifyObservers(new ATMResetEvent());
    }
}
