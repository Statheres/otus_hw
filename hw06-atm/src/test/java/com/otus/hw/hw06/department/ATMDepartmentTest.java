package com.otus.hw.hw06.department;

import com.otus.hw.hw06.atm.ATM;
import com.otus.hw.hw06.atm.DefaultATM;
import com.otus.hw.hw06.atm.exceptions.*;
import com.otus.hw.hw06.atm.money.Banknote;
import com.otus.hw.hw06.atm.money.DefaultMoneyCell;
import com.otus.hw.hw06.atm.withdraw.MinimumBanknotesWithdrawStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ATMDepartmentTest {
    private ATMDepartment department;
    private List<ATM> atms;
    private int initialBalance;

    private ATM createATM() throws InvalidSnapshotException {
        return new DefaultATM(
                new MinimumBanknotesWithdrawStrategy(),
                Arrays.asList(
                        new DefaultMoneyCell(Banknote.ONE_THOUSAND, 25),
                        new DefaultMoneyCell(Banknote.FIVE_HUNDRED, 1),
                        new DefaultMoneyCell(Banknote.HUNDRED, 43),
                        new DefaultMoneyCell(Banknote.FIFTY, 198)
                )
        );
    }

    @BeforeEach
    void initTest() throws InvalidSnapshotException {
        atms = Arrays.asList(
                createATM(),
                createATM()
        );

        department = new DefaultATMDepartment();
        for (ATM atm : atms) {
            department.addATM(atm);
        }

        initialBalance = department.getTotalBalance();
    }

    @Test
    void getTotalBalance() throws InvalidBanknoteException, InvalidBanknotesCountException {
        Map<Banknote, Integer> banknotes = new HashMap<>();
        banknotes.put(Banknote.ONE_THOUSAND, 1);
        banknotes.put(Banknote.FIVE_HUNDRED, 56);
        banknotes.put(Banknote.HUNDRED, 568);

        int depositSum = banknotes.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getDenomination() * entry.getValue())
                .sum();

        atms.get(0).deposit(banknotes);

        assertEquals(initialBalance + depositSum, department.getTotalBalance());
    }

    @Test
    void reset() {
        // Deposit/reset
        Map<Banknote, Integer> banknotes = new HashMap<>();
        banknotes.put(Banknote.ONE_THOUSAND, 1);
        banknotes.put(Banknote.FIVE_HUNDRED, 56);
        banknotes.put(Banknote.HUNDRED, 568);

        int depositSum = banknotes.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getDenomination() * entry.getValue())
                .sum();

        atms.forEach(atm -> {
            try {
                atm.deposit(banknotes);
            } catch (InvalidBanknoteException | InvalidBanknotesCountException e) {
                e.printStackTrace();
            }
        });

        assertEquals(initialBalance + depositSum * atms.size(), department.getTotalBalance());

        department.reset();

        assertEquals(initialBalance, department.getTotalBalance());

        // Withdraw/reset
        int withdrawSum = 450;

        atms.forEach(atm -> {
            try {
                atm.withdraw(withdrawSum);
            } catch (InvalidWithdrawSum | NotEnoughBanknotesException e) {
                e.printStackTrace();
            }
        });

        assertEquals(initialBalance - withdrawSum * atms.size(), department.getTotalBalance());

        department.reset();

        assertEquals(initialBalance, department.getTotalBalance());
    }
}
