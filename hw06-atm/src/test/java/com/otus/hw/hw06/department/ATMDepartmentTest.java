package com.otus.hw.hw06.department;

import com.otus.hw.hw06.atm.ATM;
import com.otus.hw.hw06.atm.DefaultATM;
import com.otus.hw.hw06.atm.exceptions.InvalidBanknoteException;
import com.otus.hw.hw06.atm.exceptions.InvalidBanknotesCountException;
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

    private ATM createATM() {
        return new DefaultATM(
                new MinimumBanknotesWithdrawStrategy(),
                Arrays.asList(
                        new DefaultMoneyCell(Banknote.ONE_THOUSAND),
                        new DefaultMoneyCell(Banknote.FIVE_HUNDRED),
                        new DefaultMoneyCell(Banknote.HUNDRED),
                        new DefaultMoneyCell(Banknote.FIFTY)
                )
        );
    }

    @BeforeEach
    void initTest() {
        atms = Arrays.asList(
                createATM(),
                createATM()
        );

        department = new DefaultATMDepartment();
        for (ATM atm : atms) {
            department.addATM(atm);
        }
    }

    @Test
    void getTotalBalance() throws InvalidBanknoteException, InvalidBanknotesCountException {
        Map<Banknote, Integer> banknotes = new HashMap<>();
        banknotes.put(Banknote.ONE_THOUSAND, 1);
        banknotes.put(Banknote.FIVE_HUNDRED, 56);
        banknotes.put(Banknote.HUNDRED, 568);

        int balance = banknotes.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getDenomination() * entry.getValue())
                .sum();

        assertEquals(0, department.getTotalBalance());

        atms.get(0).deposit(banknotes);

        assertEquals(balance, department.getTotalBalance());
    }

    @Test
    void reset() {
        Map<Banknote, Integer> banknotes = new HashMap<>();
        banknotes.put(Banknote.ONE_THOUSAND, 1);
        banknotes.put(Banknote.FIVE_HUNDRED, 56);
        banknotes.put(Banknote.HUNDRED, 568);

        assertEquals(0, department.getTotalBalance());

        atms.forEach(atm -> {
            try {
                atm.deposit(banknotes);
            } catch (InvalidBanknoteException | InvalidBanknotesCountException e) {
                e.printStackTrace();
            }
        });

        assertNotEquals(0, department.getTotalBalance());

        department.reset();

        assertEquals(0, department.getTotalBalance());
    }
}
