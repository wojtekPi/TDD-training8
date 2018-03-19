package pl.sda.maciejskro;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PrimeNumberTest {

    private PrimeNumber pm;

    @Before
    public void setUp() {
        pm = new PrimeNumber();

    }

    @Test
    public  void  shouldReturnFalseOnZero() {
        boolean result = pm.isPrime(0);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void shouldReturnFalseOnOne() {
        boolean result = pm.isPrime(1);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void shouldReturnFalseOnFirstOddNumber() {
        boolean result = pm.isPrime(4);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void shouldReturnTrueOnFirstPrime() {
        boolean result = pm.isPrime(3);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void shouldReturnTrueOn5() {
        boolean result = pm.isPrime(5);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void shouldReturnTrueOn7() {
        boolean result = pm.isPrime(7);
        assertThat(result).isEqualTo(true);
    }
    @Test
    public void shouldReturnTrueOn9() {
        boolean result = pm.isPrime(9);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void shouldReturnFalseOnBigEvenNumber() {
        boolean result = pm.isPrime(34230450);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void shouldReturnTrueOnBigPrimeNumber() {
        boolean result= pm.isPrime(15487177);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void shouldReturnTrueOnBigNegativePrimeNumber() {
        boolean result = pm.isPrime(-15487177);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void shouldReturnFalseOnNegativeEvenNumber() {
        boolean result = pm.isPrime( -34);
        assertThat(result).isEqualTo(false);
    }
}
