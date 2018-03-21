package bank;

public interface TransactionDB {

    void save(Account from, Account to, Instrument howMoney);

}
