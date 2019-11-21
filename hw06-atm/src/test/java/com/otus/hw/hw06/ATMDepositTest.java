package com.otus.hw.hw06;

import com.otus.hw.hw06.atm.exceptions.InvalidBanknoteException;
import com.otus.hw.hw06.atm.exceptions.InvalidBanknotesCountException;
import com.otus.hw.hw06.atm.money.Banknote;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ATMDepositTest extends ATMAbstractTest {
    @Test
    void deposit_Ok() {
        Map<Banknote, Integer> banknotes = new HashMap<>();
        banknotes.put(Banknote.ONE_THOUSAND, 1);
        banknotes.put(Banknote.FIVE_HUNDRED, 56);
        banknotes.put(Banknote.HUNDRED, 568);
        banknotes.put(Banknote.FIFTY, 35);

        int sum = banknotes.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getDenomination() * entry.getValue())
                .sum();

        assertDoesNotThrow(() -> getAtm().deposit(banknotes));
        assertEquals(sum, getAtm().getTotalBalance());
    }

    @Test
    void deposit_InvalidBanknoteException() {
        Map<Banknote, Integer> banknotes = new HashMap<>();
        banknotes.put(Banknote.TEN, 44);

        assertThrows(InvalidBanknoteException.class, () -> getAtm().deposit(banknotes));
        assertEquals(0, getAtm().getTotalBalance());
    }

    @Test
    void deposit_InvalidBanknotesCountException() {
        Map<Banknote, Integer> zeroBanknotes = new HashMap<>();
        zeroBanknotes.put(Banknote.FIVE_HUNDRED, 0);

        Map<Banknote, Integer> negativeBanknotes = new HashMap<>();
        negativeBanknotes.put(Banknote.ONE_THOUSAND, -132);

        assertThrows(InvalidBanknotesCountException.class, () -> getAtm().deposit(zeroBanknotes));
        assertThrows(InvalidBanknotesCountException.class, () -> getAtm().deposit(negativeBanknotes));
        assertEquals(0, getAtm().getTotalBalance());
    }
}
