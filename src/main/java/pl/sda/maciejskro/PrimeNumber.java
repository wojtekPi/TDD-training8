package pl.sda.maciejskro;

public class PrimeNumber {
    public boolean isPrime(int number) {
        boolean result = false;
        if (number == 0 || number ==1 ) {
            return result;
        } else if (number <0) {
            return  result;
        } else if (number > 1) {
            for (int i = 2; i < number; i++) {
                if ((number % i) == 0) {
                    return result;
                } else {
                    result = true;
                    return result;
                }
            }
        } else {
            for (int i = number; i< -1 ; i++) {
                if ((number %i) ==0) {
                    return result;
                } else {
                    result = true;
                    return result;
                }
            }
        }
        return result;
    }
}
