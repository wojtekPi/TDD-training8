package bank;

public class PaymentService {
    private static final int LIMIT_AMOUNT = -500;
    private static final String NOT_ENOUGH_MONEY_TEXT =
            "I'm very sorry, but you don't have enough money...";
    public static final String CURRENCIES_ARE_INCOMPATIBILE = "Currencies  are incompatibile ";
    private ExchangeService exchangeService;
    private TransactionDB transactionDB;

    public void transferMoney(Account from, Account to, Instrument howMoney) {
        if (from.getBalance().getAmount() < LIMIT_AMOUNT) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY_TEXT);
        } else if (from.getBalance().getCurrency() != howMoney.getCurrency()) {
            throw new IllegalArgumentException(CURRENCIES_ARE_INCOMPATIBILE);
        }

        int targetAmountOnToAccount = to.getBalance().getAmount() + howMoney.getAmount();

        if (from.getBalance().getCurrency() != to.getBalance().getCurrency()) {
            targetAmountOnToAccount = to.getBalance().getAmount() + exchangeService
                    .calculate(howMoney, to.getBalance().getCurrency());
        }

        from.setBalance(from.getBalance().getAmount() - howMoney.getAmount());
        to.setBalance(targetAmountOnToAccount);

        transactionDB.save(from, to, howMoney);

    }

    public void setTransactionDB(TransactionDB transactionDB) {
        this.transactionDB = transactionDB;
    }

    public void setExchangeService(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }
}
