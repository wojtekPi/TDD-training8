import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Tdd training on 16.03.18.
 */

public class StringCalculatorTest {

    private StringCalculator testedObject;
    private Random random;
    private static Integer stala=10000;

    @Before
    public void setUp() {
        testedObject = new StringCalculator();
        random = new Random();
    }

    @Test
    public void shouldCreateObject() throws Exception {

        assertThat(testedObject).isNotNull();
        assertThat(testedObject.add("")).isEqualTo(0);
    }

    @Test
    public void shouldShowZeroForParameterZero() throws  Exception{

        int result = testedObject.add("0");

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldReturnZeroOnRandomString() throws  Exception {
        int result = testedObject.add("skss");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldRetrunNumberIfOneNumberIsGiven() throws Exception {

        int result = testedObject.add("1");

        assertThat(result).isEqualTo(1);
    }

    @Test
    public  void shouldReturnNumberIfNumberisGiven() throws Exception {


        int test  = random.nextInt(1000000);
        int result = testedObject.add(Integer.toString(test));

        assertThat(result).isEqualTo(test);
    }

    @Test
    public void shouldReturnSumOfTwoNumberWithColon() {
        int constant = 1000;
        int test1 = random.nextInt(constant);
        int test2 = random.nextInt(constant);
        int result = testedObject.add( Integer.toString(test1)+","+Integer.toString(test2));

        assertThat(result).isEqualTo(test1+test2);
    }

    @Test
    public  void shouldReturnSumOfSecondNegative() {

        int test1 = random.nextInt(stala);
        int test2 = random.nextInt(stala)-stala;
        int result = testedObject.add(Integer.toString(test1)+"," + Integer.toString(test2));
        assertThat(result).isEqualTo(test1+test2);
    }

    @Test
    public  void shouldReturnSumOfFirstNegative() {

        int test1 = random.nextInt(stala)-stala;
        int test2 = random.nextInt(stala);
        int result = testedObject.add(Integer.toString(test1)+"," + Integer.toString(test2));
        assertThat(result).isEqualTo(test1+test2);
    }

    @Test
    public  void shouldReturnSumOfTwoNegative() {

        int test1 = random.nextInt(stala)-stala;
        int test2 = random.nextInt(stala)-stala;
        int result = testedObject.add(Integer.toString(test1)+"," + Integer.toString(test2));
        assertThat(result).isEqualTo(test1+test2);
    }

    @Test
    public  void shouldReturnSumOfThreElement() {

        int test1 = random.nextInt(stala*2)-stala;
        int test2 = random.nextInt(stala*2)-stala;
        System.out.println("test1: " +test1 + " test2: " + test2 );
        int result = testedObject.add(Integer.toString(test1)+"," + Integer.toString(test2) + "," + Integer.toString(test1*test2));
        assertThat(result).isEqualTo(test1+test2+(test1*test2));
    }
}