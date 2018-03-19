package bank;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class PaymentServiceTest {

    private PaymentService testedObject;

    @Before
    public  void setUp() {
        testedObject = new PaymentService();
    }

    @Test
    public void shouldCreatePaymentServiceAndCallMethod() throws Exception {
        PaymentService testedObject = new PaymentService();
        Account from = new Account("", 0);
        Account to = new Account("", 0);

        testedObject.transferMoney(from, to, 0);
    }

    @Test
    public void shouldHaveCorrectAmountsAfterTransfer() {
        PaymentService testedObject = new PaymentService();
        Account from = new Account("from", 10);
        Account to = new Account("to", 20);

        testedObject.transferMoney(from, to, 1);

        assertThat(from.getBalance()).isEqualTo(9);
        assertThat(to.getBalance()).isEqualTo(21);
    }

    private Object[][]  paramsForTestingBankTransfer() {
        return new Object[][] {
                {"from",20, "to",40,2, 18 , 42},
                {"from",0,"to",40, 2, -2, 42},
                {"from",20,"to",40,30 ,-10 , 70},
                {"from",10,"to",10,-20, 30,-10}
        };
    }

    @Test
    @Parameters(method = "paramsForTestingBankTransfer")
    public void shouldReturnCorrectAmountOn3SetParameters(String fromId , int fromAmount , String toId , int toAmount,
                                                          int transactionAmount, int fromExpected,  int toExpected ) {
        Account from = new Account(fromId, fromAmount);
        Account to = new Account(toId, toAmount);
        testedObject.transferMoney(from,to,transactionAmount);
        assertThat(from.getBalance()).isEqualTo(fromExpected);
        assertThat(to.getBalance()).isEqualTo(toExpected);
    }

}
