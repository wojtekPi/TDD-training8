import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tdd training on 16.03.18.
 */

public class StringCalculatorTest {
    private StringCalculator testedObject;

    @Before
    public void setUp() {
        testedObject = new StringCalculator();
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();

    }

    @Test
    public void shouldReturnZeroForEmptyString() throws Exception {
        int result = testedObject.Add("");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldReturnOneForStringOne() throws Exception {

        int result = testedObject.Add("1");

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldReturnThreeForStringsOneandTwo() throws Exception {
        int result = testedObject.Add("1,2");
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void shouldReturn1112For1000Plus112() throws Exception {
        int result = testedObject.Add("1000,112");
        assertThat(result).isEqualTo(1112);
    }

    @Test
    public void shouldReturnMinus10For10PlusMinus10() throws Exception {
        int result = testedObject.Add("0,-10");
        assertThat(result).isEqualTo(-10);
    }

    @Test
    public void shouldReturnMinusForMinusArguments() throws Exception {
        int result = testedObject.Add("-3,-4");
        assertThat(result).isEqualTo(-7);
    }

    @Test
    public void shouldReturnMinusForZeroPlusMinusArgument() throws Exception {
        int result = testedObject.Add("0,-4");
        assertThat(result).isEqualTo(-4);
    }

    @Test
    public void shouldReturnSummaryForThreeArguments() throws Exception {
        int result = testedObject.Add("12,8,10");
        assertThat(result).isEqualTo(30);
    }
    @Test
    public void shouldReturnSummaryForFourArguments() throws Exception {
        int result = testedObject.Add("14,-7,7,10");
        assertThat(result).isEqualTo(24);
    }


}