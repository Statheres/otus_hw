package com.otus.hw.hw06.atm;

import com.otus.hw.hw06.atm.exceptions.InvalidBanknoteException;
import com.otus.hw.hw06.atm.exceptions.InvalidBanknotesCountException;
import com.otus.hw.hw06.atm.exceptions.InvalidWithdrawSum;
import com.otus.hw.hw06.atm.exceptions.NotEnoughBanknotesException;
import com.otus.hw.hw06.atm.money.Banknote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ATMWithdrawTest extends ATMAbstractTest {
    private int initialBalance;

    @BeforeEach
    @Override
    void initATM() throws InvalidBanknoteException, InvalidBanknotesCountException {
        super.initATM();

        Map<Banknote, Integer> banknotes = new HashMap<>();
        banknotes.put(Banknote.ONE_THOUSAND, 1);
        banknotes.put(Banknote.FIVE_HUNDRED, 56);
        banknotes.put(Banknote.HUNDRED, 568);
        banknotes.put(Banknote.FIFTY, 35);
        getAtm().deposit(banknotes);

        initialBalance = getAtm().getTotalBalance();
    }

    @Test
    void withdraw_Ok() {
        int withdrawSum = 350;

        assertDoesNotThrow(() -> getAtm().withdraw(350));
        assertEquals(initialBalance - withdrawSum, getAtm().getTotalBalance());
    }

    @Test
    void withdraw_NotEnoughBanknotesException() {
        assertThrows(NotEnoughBanknotesException.class, () -> getAtm().withdraw(1000000));
        assertEquals(initialBalance, getAtm().getTotalBalance());
    }

    @Test
    void withdraw_InvalidWithdrawSum() {
        assertThrows(InvalidWithdrawSum.class, () -> getAtm().withdraw(-100));
        assertThrows(InvalidWithdrawSum.class, () -> getAtm().withdraw(0));
        assertEquals(initialBalance, getAtm().getTotalBalance());
    }
}
