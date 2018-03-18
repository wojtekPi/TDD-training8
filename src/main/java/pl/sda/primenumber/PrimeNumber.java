package pl.sda.primenumber;

public class PrimeNumber {
    public boolean isPrime(int number) {
        boolean result = false;
        if (number == 0) {
            result = false;
        } else if (number > 1) {
            for (int i = 1; i < number; i++) {
                if ((number % i) == 0) {
                    result = true;
                    break;
                } else
                    result = false;
            }
        } else
            result = false;
        return result;
    }
}
