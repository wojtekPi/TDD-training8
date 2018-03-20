package bank;

public class Account {

    private String id;
    private Instrument balance;

    public Account(String id, int balance, Currency curr) {
        this.id = id;
        this.balance = new Instrument(curr, balance);
    }

    public Account(String id, Instrument balance) {
        this.id = id;
        this.balance = balance;
    }

    public Instrument getBalance() {
        return balance;
    }

    public void setBalance(int amount) {
         this.balance.setAmount( amount);
    }

 }
