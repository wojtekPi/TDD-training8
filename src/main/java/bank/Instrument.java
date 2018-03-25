package bank;

import java.util.Objects;

public class Instrument {

    private Currency currency;
    private int amount;

    public Instrument(Currency currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instrument)) return false;
        Instrument that = (Instrument) o;
        return getAmount() == that.getAmount() &&
                getCurrency() == that.getCurrency();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCurrency(), getAmount());
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}
