package pl.sda.primenumber;

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
    public  void  shouldReturnFalseIfNotNumber() {

        boolean result = pm.isPrime(0);
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
}
