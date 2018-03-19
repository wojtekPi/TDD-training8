import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tdd training on 16.03.18.
 */
@RunWith(JUnitParamsRunner.class)
public class StringCalculatorTest {
    private StringCalculator testedObject;

    @Before
    public void setUp() {
        testedObject = new StringCalculator();
    }

    private Object[][] paramsForTestingValue() {
        return new Object[][]{
                {"0", 0},
                {"1", 1},
                {"2", 2},
                {"1002,7003", 8005},
                {"1002,-1", 1001},
                {"1002,-1,-100", 901},
                {"-3,-1,-4", -8},
                {"-8,0", -8},
                {"1,2,3", 6},
                {"1,2,30000", 30003},
        };
    }

    @Test
    @Parameters(method = "paramsForTestingValue")
    public void shouldReturnCorrectValue(String input, int expectedResult) {
        int result = testedObject.Add(input);

        assertThat(result).isEqualTo(expectedResult);
    }
}