package bank.usecases;

import bank.domain.HistoryAction.HistoryAction;
import bank.domain.HistoryAction.HistoryActionRepository;
import bank.domain.account.Account;
import bank.domain.account.OperationTypes;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class HistoryActionUseCase {

    private final HistoryActionRepository historyActionRepository;

    public HistoryAction audit(OperationTypes operationTypes, Account account, BigDecimal value) {
        HistoryAction historyAction = HistoryAction.getInstance(operationTypes, account, value);
        return historyActionRepository.save(historyAction);
    }

}
