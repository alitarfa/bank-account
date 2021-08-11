package bank.domain.account;

import bank.domain.HistoryAction.HistoryAction;
import bank.exception.AccountException;
import lombok.Builder;
import lombok.Getter;
import printer.PrinterStrategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
public class Account {

    private static final BigDecimal OVER_DRAFT = new BigDecimal(100);

    private Balance balance;
    private final String iban;
    private final LocalDateTime createdAt;
    private final Set<HistoryAction> historyActions;

    private Account(Balance balance, String iban) {
        this.balance = balance;
        this.iban = iban;
        this.createdAt = LocalDateTime.now();
        this.historyActions = new HashSet<>();
    }

    public static Account getInstance(Balance balance, String iban) {
        return new Account(balance, iban);
    }

    public Account deposit(BigDecimal amount) {
        this.balance = balance.add(amount);
        this.historyActions.add(HistoryAction.getInstance(OperationTypes.DEPOSIT, this, amount));
        return this;
    }

    public Account withdraw(BigDecimal amount) {
        if (balance.add(OVER_DRAFT).getValue().compareTo(amount) > 0) {
            this.balance = this.balance.minus(amount);
        } else {
            throw new AccountException("error.account.balance.insufficient");
        }
        this.historyActions.add(HistoryAction.getInstance(OperationTypes.WITHDRAW, this, amount));
        return this;
    }

    public String print(PrinterStrategy printerStrategy) {
        return printerStrategy.print(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(iban, account.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, iban, createdAt, historyActions);
    }
}
