package printer;

import bank.domain.account.Account;

import java.util.stream.Collectors;

public class PrintAsString implements PrinterStrategy {

    @Override
    public String print(Account account) {
        return account.getHistoryActions()
                .stream()
                .map(historyAction -> historyAction.getOperationTypes() + "::" + historyAction.getAccount().getBalance())
                .collect(Collectors.joining("\n"));
    }
}
