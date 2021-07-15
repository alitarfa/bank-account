package printer;

import bank.domain.account.Account;

public interface PrinterStrategy {
    String print(Account account);
}
