package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(JUnitParamsRunner.class)
public class PaymentServiceTest {
    private static final String FROM = "from";
    private static final String TO = "to";
    private static final String NOT_ENOUGH_MONEY_TEXT = "I'm very sorry, but you don't have enough money...";
    private PaymentService testedObject;
    private Account from;
    private Account to;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void setUp() {
        testedObject = new PaymentService();
        from = new Account(FROM, 0);
        to = new Account(TO, 0);
    }

    @Test
    public void shouldCreatePaymentServiceAndCallMethod() throws Exception {
        PaymentService testedObject = new PaymentService();

        testedObject.transferMoney(from, to, 0);
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
    public void shouldReturnCorrectAmountOn3SetParameters(int fromAmount, int toAmount,
                                                          int transactionAmount, int fromExpected, int toExpected) {
        from.setBalance(fromAmount);
        to.setBalance(toAmount);

        testedObject.transferMoney(from, to, transactionAmount);

        assertThat(from.getBalance()).isEqualTo(fromExpected);
        assertThat(to.getBalance()).isEqualTo(toExpected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNotEnoughMoney() throws Exception {
        from.setBalance(-501);

        testedObject.transferMoney(from, to, 7);
    }

    @Test
    public void shouldHasCorrectExcepionMessageWhenNotEnoughMoney() {
        from.setBalance(-501);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(NOT_ENOUGH_MONEY_TEXT);

        testedObject.transferMoney(from, to, 7);

        //This assertions will be not triggered!!!
        assertThat(from.getBalance()).isEqualTo(0);
    }

    @Test
    public void shouldNotChengeBalanceWhenExceptionWasThrown() throws Exception {
        from.setBalance(-501);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> testedObject.transferMoney(from, to, 7))
                .withMessage(NOT_ENOUGH_MONEY_TEXT);

        assertThat(from.getBalance()).isEqualTo(-501);
        assertThat(to.getBalance()).isEqualTo(0);
    }
}
