package bank.domain.account;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@ToString
public class Balance {
    private BigDecimal value;
    private final Currency currency;

    private Balance(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public static Balance getInstance(BigDecimal amount, Currency currency) {
        return new Balance(amount, currency);
    }

    public Balance add(BigDecimal amount) {
        this.value = this.value.add(amount);
        return this;
    }

    public Balance minus(BigDecimal amount) {
        this.value = this.value.subtract(amount);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return Objects.equals(value, balance.value) && currency == balance.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }
}
