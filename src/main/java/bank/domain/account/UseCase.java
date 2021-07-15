package bank.domain.account;

import printer.PrinterStrategy;

import java.math.BigDecimal;

public interface UseCase {

    Account deposit(String iban, BigDecimal amount);

    Account withdraw(String iban, BigDecimal amount);

    String print(String iban, PrinterStrategy printerStrategy);

}
