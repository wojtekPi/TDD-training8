package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.times;

@RunWith(JUnitParamsRunner.class)
public class PaymentServiceTest {
    private static final String FROM = "from";
    private static final String TO = "to";
    private static final String NOT_ENOUGH_MONEY_TEXT = "I'm very sorry, but you don't have enough money...";
    public static final String CURRENCIES_ARE_INCOMPATIBILE = "Currencies  are incompatibile ";
    private PaymentService testedObject;
    private TransactionDB transactionDB;
    private ExchangeService exchangeService;
    private Account from;
    private Account to;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void setUp() {
        testedObject = new PaymentService();
        from = new Account(FROM, 0, Currency.EUR);
        to = new Account(TO, 0, Currency.EUR);
        this.transactionDB = Mockito.mock(TransactionDB.class);
        this.exchangeService = Mockito.mock(ExchangeService.class);
    }


    private Object[][] paramsForTestingBankTransfer() {
        return new Object[][]{
                {20, 40, 2, 18, 42},
                {0, 40, 2, -2, 42},
                {20, 40, 30, -10, 70},
                {10, 10, -20, 30, -10}
        };
    }

    @Test
    @Parameters(method = "paramsForTestingBankTransfer")
    public void shouldReturnCorrectAmountWithNewObjectAsParams(int fromAmount, int toAmount,
                                                               int transactionAmount, int fromExpected, int toExpected) {
        from.setBalance(fromAmount);
        to.setBalance(toAmount);


        Instrument instrument = new Instrument(Currency.EUR, transactionAmount);
        testedObject.setDatabaseAccess(transactionDB);
        testedObject.transferMoney(from, to, instrument);

        assertThat(from.getBalance().getAmount()).isEqualTo(fromExpected);
        assertThat(to.getBalance().getAmount()).isEqualTo(toExpected);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNotEnoughMoney() throws Exception {
        from.setBalance(-501);
        Instrument sevenEUR = new Instrument(Currency.EUR, 7);

        testedObject.transferMoney(from, to, sevenEUR);
    }

    @Test
    public void shouldHasCorrectExcepionMessageWhenNotEnoughMoney() {
        from.setBalance(-501);
        Instrument sevenEUR = new Instrument(Currency.EUR, 7);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(NOT_ENOUGH_MONEY_TEXT);

        testedObject.transferMoney(from, to, sevenEUR);

        //This assertions will be not triggered!!!
        assertThat(from.getBalance().getAmount()).isEqualTo(0);
    }

    @Test
    public void shouldNotChengeBalanceWhenExceptionWasThrown() throws Exception {
        from.setBalance(-501);
        Instrument sevenEUR = new Instrument(Currency.EUR, 7);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> testedObject.transferMoney(from, to, sevenEUR))
                .withMessage(NOT_ENOUGH_MONEY_TEXT);

        assertThat(from.getBalance().getAmount()).isEqualTo(-501);
        assertThat(to.getBalance().getAmount()).isEqualTo(0);
    }

    @Test
    public void shouldThrowExceptionWhenAllCurrenciesAreNotCompatibile() {
        Account from1 = new Account("from1", 100, Currency.EUR);
        Account to1 = new Account("to1", 100, Currency.EUR);
        Instrument transferMoney = new Instrument(Currency.PLN, 10);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> testedObject.transferMoney(from1, to1, transferMoney))
                .withMessage(CURRENCIES_ARE_INCOMPATIBILE);

        assertThat(from1.getBalance().getAmount()).isEqualTo(100);
        assertThat(to1.getBalance().getAmount()).isEqualTo(100);
    }

    @Test
    public void shouldCallExchangeServiceWhenCurrencyIsDifferentOnToAccount() throws Exception {
        Account to = new Account("to1", 100, Currency.PLN);
        Instrument transferMoney = new Instrument(Currency.EUR, 10);
        testedObject.setExchangeService(exchangeService);
        testedObject.setDatabaseAccess(transactionDB);
        testedObject.transferMoney(from, to, transferMoney);

        Mockito.verify(exchangeService, times(1))
                .calculate(transferMoney, Currency.PLN);

        assertThat(to.getBalance().getAmount()).isEqualTo(100);
    }

    @Test
    public void shouldUseValueFromExchangeServiceWhenCurrencyIsDifferentOnToAccount() throws Exception {
        Account to = new Account("to1", 100, Currency.PLN);
        Instrument transferMoney = new Instrument(Currency.EUR, 10);
        testedObject.setExchangeService(exchangeService);
        testedObject.setDatabaseAccess(transactionDB);
        Mockito.when(exchangeService.calculate(transferMoney, Currency.PLN))
                .thenReturn(40);

        testedObject.transferMoney(from, to, transferMoney);

        assertThat(to.getBalance().getAmount()).isEqualTo(140);
    }

    @Test
    public  void shouldSaveDateToDababaseWhenTransferIsCaled() throws  Exception{
        Account to = new Account("to1", 100, Currency.PLN);
        Instrument transferMoney = new Instrument(Currency.EUR, 10);
        testedObject.setDatabaseAccess (transactionDB);
        testedObject.setExchangeService(exchangeService);

        testedObject.transferMoney(from, to, transferMoney);

        Mockito.verify(transactionDB).save(from, to, transferMoney);

        //assertThat(to.getBalance().getAmount()).isEqualTo(140);
    }
}
