package bank;

import java.time.LocalDateTime;

public interface TransactionDB {

    void save (Account from , Account to , Instrument transfer);

    void save(Account from, Account to, Instrument transferMoney, LocalDateTime now);
}

