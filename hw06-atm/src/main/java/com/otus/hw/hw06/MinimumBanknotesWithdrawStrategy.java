package com.otus.hw.hw06;

import com.otus.hw.hw06.exceptions.InvalidWithdrawSum;
import com.otus.hw.hw06.exceptions.NotEnoughBanknotesException;

import java.util.*;

public class MinimumBanknotesWithdrawStrategy implements WithdrawStrategy {
    @Override
    public Map<Integer, Integer> withdraw(int sum, List<MoneyCell> moneyCells) throws InvalidWithdrawSum, NotEnoughBanknotesException {
        int maxDenomination = findMaxWithdrawDenomination(sum, moneyCells);

        Map<Integer, Integer> cash = new TreeMap<>();
        for (MoneyCell cell : moneyCells) {
            int denomination = cell.getDenomination();
            if (denomination <= maxDenomination) {
                int withdrawBanknotesCount = Integer.min(cell.getBanknotesCount(), sum / denomination);

                cell.withdraw(withdrawBanknotesCount);
                cash.put(denomination, withdrawBanknotesCount);

                sum -= denomination * withdrawBanknotesCount;
                if (sum == 0) {
                    break;
                }
            }
        }

        return cash;
    }

    private int findMaxWithdrawDenomination(int sum, List<MoneyCell> moneyCells) throws InvalidWithdrawSum {
        List<Integer> banknoteCounts = calculateWithdrawBanknoteCounts(sum, moneyCells);

        int index = banknoteCounts.indexOf(Collections.min(banknoteCounts));
        if (banknoteCounts.get(index) == Integer.MAX_VALUE) {
            throw new InvalidWithdrawSum();
        }

        return moneyCells.get(index).getDenomination();
    }

    private List<Integer> calculateWithdrawBanknoteCounts(int sum, List<MoneyCell> moneyCells) {
        List<Integer> banknoteCounts = new ArrayList<>(Collections.nCopies(moneyCells.size(), 0));

        for (int i = 0; i < moneyCells.size(); ++i) {
            int rest = sum;

            if (moneyCells.get(i).getDenomination() <= sum) {
                for (int j = i; j < moneyCells.size(); ++j) {
                    int denomination = moneyCells.get(j).getDenomination();
                    int withdrawBanknotesCount = Integer.min(moneyCells.get(j).getBanknotesCount(), rest / denomination);

                    banknoteCounts.set(i, banknoteCounts.get(i) + withdrawBanknotesCount);

                    rest -= withdrawBanknotesCount * denomination;
                    if (rest == 0) {
                        break;
                    }
                }
            }

            if (rest != 0) {
                banknoteCounts.set(i, Integer.MAX_VALUE);
            }
        }

        return banknoteCounts;
    }
}
