import bank.domain.HistoryAction.HistoryAction;
import bank.domain.HistoryAction.HistoryActionRepository;
import bank.domain.account.Account;
import bank.domain.account.Balance;
import bank.domain.account.Currency;
import bank.domain.account.OperationTypes;
import bank.usecases.HistoryActionUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class HistoryActionTest {

    private static final BigDecimal VALUE_AMOUNT_200 = new BigDecimal(200);
    private static final BigDecimal VALUE_AMOUNT_100 = new BigDecimal(200);
    public static final String ALI_IBAN = "AL35202111090000000001234520";

    @Mock
    private HistoryActionRepository historyActionRepositoryMock;

    @Test
    void should_audit() {
        Balance balance = Balance.getInstance(VALUE_AMOUNT_100, Currency.EURO);
        Account account = Account.getInstance(balance, ALI_IBAN);
        HistoryAction historyAction = HistoryAction.getInstance(OperationTypes.DEPOSIT, account, VALUE_AMOUNT_200);
        HistoryActionUseCase historyActionUseCase = new HistoryActionUseCase(historyActionRepositoryMock);

        Mockito.when(historyActionRepositoryMock.save(any())).thenReturn(historyAction);
        historyActionUseCase.audit(OperationTypes.DEPOSIT, account, VALUE_AMOUNT_200);

        Mockito.verify(historyActionRepositoryMock).save(any());
    }
}
