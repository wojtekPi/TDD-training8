package bank;

public class PaymentService {

    public void transferMoney(Account from, Account to, int i) {

        from.setBalance(-i);
        to.setBalance(i);

    }
}
