package com.otus.hw.hw06;

import com.otus.hw.hw06.atm.ATM;
import com.otus.hw.hw06.atm.DefaultATM;
import com.otus.hw.hw06.atm.money.Banknote;
import com.otus.hw.hw06.atm.money.MoneyCell;
import com.otus.hw.hw06.atm.withdraw.MinimumBanknotesWithdrawStrategy;
import com.otus.hw.hw06.atm.exceptions.ATMException;
import com.otus.hw.hw06.atm.money.DefaultMoneyCell;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ATMException {
        List<MoneyCell> moneyCells = Arrays.asList(
                new DefaultMoneyCell(Banknote.ONE_THOUSAND),
                new DefaultMoneyCell(Banknote.FIVE_HUNDRED),
                new DefaultMoneyCell(Banknote.HUNDRED),
                new DefaultMoneyCell(Banknote.FIFTY),
                new DefaultMoneyCell(Banknote.TEN)
        );
        ATM atm = new DefaultATM(new MinimumBanknotesWithdrawStrategy(), moneyCells);

        Map<Banknote, Integer> banknotes = new HashMap<>();
        banknotes.put(Banknote.HUNDRED, 2);
        banknotes.put(Banknote.FIFTY, 2);
        banknotes.put(Banknote.TEN, 2);
        atm.deposit(banknotes);

        Map<Banknote, Integer> cash = atm.withdraw(160);
        cash.forEach(
                (banknote, count) -> {
                    System.out.println("Banknote: " + banknote + ", Count: " + count);
                }
        );

        System.out.println(atm.getTotalBalance());
    }
}
