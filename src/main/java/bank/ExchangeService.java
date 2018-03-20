package bank;

public interface ExchangeService {
    int calculate(Instrument input, Currency targetCurrency);
}
