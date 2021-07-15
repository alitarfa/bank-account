package bank.usecases;

import bank.domain.account.Account;
import bank.domain.account.AccountRepository;
import bank.domain.account.UseCase;
import bank.exception.AccountException;
import lombok.AllArgsConstructor;
import printer.PrinterStrategy;

import java.math.BigDecimal;

@AllArgsConstructor
public class AccountUseCase implements UseCase {

    private final AccountRepository accountRepository;

    @Override
    public Account deposit(String iban, BigDecimal amount) {
        return accountRepository.find(iban)
                .map(account -> account.deposit(amount))
                .map(accountRepository::save)
                .orElseThrow(() -> new AccountException("error.account.notfound"));
    }

    @Override
    public Account withdraw(String iban, BigDecimal amount) {
        return accountRepository.find(iban)
                .map(account -> account.withdraw(amount))
                .map(accountRepository::save)
                .orElseThrow(() -> new AccountException("error.account.notfound"));
    }

    @Override
    public String print(String iban, PrinterStrategy printerStrategy) {
        return accountRepository.find(iban)
                .map(account -> account.print(printerStrategy))
                .orElseThrow(() -> new AccountException("error.account.notfound"));
    }
}
