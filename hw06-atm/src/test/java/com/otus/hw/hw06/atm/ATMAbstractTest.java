package com.otus.hw.hw06.atm;

import com.otus.hw.hw06.atm.exceptions.InvalidBanknoteException;
import com.otus.hw.hw06.atm.exceptions.InvalidBanknotesCountException;
import com.otus.hw.hw06.atm.exceptions.InvalidSnapshotException;
import com.otus.hw.hw06.atm.money.Banknote;
import com.otus.hw.hw06.atm.money.DefaultMoneyCell;
import com.otus.hw.hw06.atm.withdraw.MinimumBanknotesWithdrawStrategy;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

public abstract class ATMAbstractTest {
    private ATM atm;

    @BeforeEach
    void initATM() throws InvalidBanknoteException, InvalidBanknotesCountException, InvalidSnapshotException {
        atm = new DefaultATM(
                new MinimumBanknotesWithdrawStrategy(),
                Arrays.asList(
                        new DefaultMoneyCell(Banknote.ONE_THOUSAND),
                        new DefaultMoneyCell(Banknote.FIVE_HUNDRED),
                        new DefaultMoneyCell(Banknote.HUNDRED),
                        new DefaultMoneyCell(Banknote.FIFTY)
                )
        );
    }

    protected ATM getAtm() {
        return atm;
    }
}
