package bank.domain.account;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> find(String iban);

    Account save(Account account);

    Account update(Account account);

    void delete(String iban);

}
