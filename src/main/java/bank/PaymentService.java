package bank;

public class PaymentService {
    private static final int LIMIT_AMOUNT = -500;
    private static final String NOT_ENOUGH_MONEY_TEXT =
            "I'm very sorry, but you don't have enough money...";

    public void transferMoney(Account from , Account to, Instrument howMoney) {
        if (from.getBalance().getAmount() < LIMIT_AMOUNT) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY_TEXT);
        }
        from.setBalance(from.getBalance().getAmount()-howMoney.getAmount());
        to.setBalance(to.getBalance().getAmount()+howMoney.getAmount());
    }
}
