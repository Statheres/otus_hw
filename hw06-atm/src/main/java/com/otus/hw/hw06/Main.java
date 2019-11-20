package com.otus.hw.hw06;

import com.otus.hw.hw06.exceptions.ATMException;

import java.util.Arrays;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ATMException {
        MoneyCell[] moneyCells = {
                new MoneyCell(1000),
                new MoneyCell(500),
                new MoneyCell(100),
                new MoneyCell(50),
                new MoneyCell(10),
        };
        ATM atm = new ATM(new MinimumBanknotesWithdrawStrategy(), Arrays.asList(moneyCells));
        atm.deposit(100, 2);
        atm.deposit(50, 2);
        atm.deposit(10, 3);

        Map<Integer, Integer> cash = atm.withdraw(160);
        for (Map.Entry<Integer, Integer> entry : cash.entrySet()) {
            System.out.println("Denomination: " + entry.getKey() + ", Count: " + entry.getValue());
        }

        System.out.println(atm.getTotalBalance());
    }
}
