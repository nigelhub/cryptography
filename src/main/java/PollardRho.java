import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Pollard's rho algorithm is a special-purpose integer factorization algorithm.
 * It is particularly effective for a composite number having a small prime factor.
 *
 * In layman terms: It allows you to determine all primes numbers that can be multiplied
 * together to give you the original value
 */
class PollardRho {

    private final static int CERTAINTY = 50;
    private final static BigInteger ZERO = new BigInteger("0");
    private final static BigInteger ONE  = new BigInteger("1");
    private final static BigInteger TWO  = new BigInteger("2");
    private final static SecureRandom random = new SecureRandom();


    public static BigInteger runPollardRho(BigInteger number) {
        BigInteger divisor;
        BigInteger c  = new BigInteger(number.bitLength(), random);
        BigInteger x  = new BigInteger(number.bitLength(), random);
        BigInteger xx = x;

        // If divisible by two then return.
        if (number.mod(TWO).compareTo(ZERO) == 0) return TWO;

        do {
            x  =  x.multiply(x).mod(number).add(c).mod(number);
            xx = xx.multiply(xx).mod(number).add(c).mod(number);
            xx = xx.multiply(xx).mod(number).add(c).mod(number);
            divisor = x.subtract(xx).gcd(number);
        }
        while((divisor.compareTo(ONE)) == 0);

        return divisor;
    }

    public static void factor(BigInteger number) {

        if (number.compareTo(ONE) == 0)
            return;

        if (number.isProbablePrime(CERTAINTY)) {
            System.out.println(number);
            return;
        }

        BigInteger divisor = runPollardRho(number);
        factor(divisor);
        factor(number.divide(divisor));
    }


    public static void main(String[] args) {
        BigInteger HELL = BigInteger.valueOf(665);
        factor(HELL);
    }
}
