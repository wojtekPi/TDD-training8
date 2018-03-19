package bank;

public class PaymentService {

    public void transferMoney(Account from, Account to, int howMany) {
        from.setBalance(-howMany);
        to.setBalance(howMany);
    }
}
