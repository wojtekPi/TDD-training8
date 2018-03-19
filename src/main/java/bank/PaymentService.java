package bank;

public class PaymentService {
    private static final int LIMIT_AMOUNT = -500;
    private static final String NOT_ENOUGH_MONEY_TEXT =
            "I'm very sorry, but you don't have enough money...";

    public void transferMoney(Account from, Account to, int howMany) {
        if (from.getBalance() < LIMIT_AMOUNT) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY_TEXT);
        }
        from.setBalance(-howMany);
        to.setBalance(howMany);
    }
}
