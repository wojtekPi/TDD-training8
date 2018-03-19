package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class PaymentServiceTest {
    private static final String FROM = "from";
    private static final String TO = "to";
    private PaymentService testedObject;

    @Before
    public void setUp() {
        testedObject = new PaymentService();
    }

    @Test
    public void shouldCreatePaymentServiceAndCallMethod() throws Exception {
        PaymentService testedObject = new PaymentService();
        Account from = new Account("", 0);
        Account to = new Account("", 0);

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
        Account from = new Account(FROM, fromAmount);
        Account to = new Account(TO, toAmount);
        testedObject.transferMoney(from, to, transactionAmount);
        assertThat(from.getBalance()).isEqualTo(fromExpected);
        assertThat(to.getBalance()).isEqualTo(toExpected);
    }

}
