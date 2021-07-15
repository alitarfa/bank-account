package bank.domain.HistoryAction;

import bank.domain.account.Account;
import bank.domain.account.OperationTypes;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoryAction {

    private final LocalDateTime performedAt;
    private final OperationTypes operationTypes;
    private final Account account;
    private final BigDecimal value;

    public static HistoryAction getInstance(OperationTypes operationTypes, Account account, BigDecimal value) {
        return new HistoryAction(LocalDateTime.now(), operationTypes, account, value);
    }
}
