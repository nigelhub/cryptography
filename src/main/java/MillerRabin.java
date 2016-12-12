import java.math.BigInteger;
import java.util.Random;


/**
 * MillerRabin determines whether a given number is prime or not with
 * probablility based on the number of times ("certainty") the number is
 * fine combed.
 *
 * Created by nigelstuart on 12/11/16.
 */
public class MillerRabin {

    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger THREE = BigInteger.valueOf(3);


    /**
     * Checks if a number is prime or not with best possible judgement.
     *
     * PSEUDOCODE:
     *
     * Credit to Wikipedia to help me understand the implementation
     * better in conjunction with lecture notes.
     *
     *   write n − 1 as 2r·d with d odd by factoring powers of 2 from n − 1
     *
     *   WitnessLoop: repeat k times:
     *     pick a random integer a in the range [2, n − 2]
     *     x ← ad mod n
     *     if x = 1 or x = n − 1 then
     *       continue WitnessLoop
     *     repeat r − 1 times:
     *       x ← x2 mod n
     *       if x = 1 then
     *         return composite
     *       if x = n − 1 then
     *         continue WitnessLoop
     *     return composite
     *   return probably prime
     *
     * @param n - The number to check if prime of not.
     * @param certainty - Determines the accurancy.  Higher the number the more certainty.
     * @return false if n is composite, otherwise probably prime
     */
    public static boolean isProbablePrime(BigInteger n, int certainty) {

        // Three an odd integer to be tested for primality
        if (n.compareTo(THREE) < 0)
            return true;

        // Keep looping until numTimesDividedUntilOdd is odd.
        // write n − 1 as 2r·d with d odd by factoring powers of 2 from n − 1
        int iterations = 0;
        BigInteger numTimesDividedUntilOdd = n.subtract(ONE);
        while (numTimesDividedUntilOdd.mod(TWO).equals(ZERO)) {
            iterations++;
            numTimesDividedUntilOdd = numTimesDividedUntilOdd.divide(TWO);
        }

        
        for (int i = 0; i < certainty; i++) {

            // pick a random integer a in the range [2, n − 2]
            BigInteger a = randomNumberInRange(TWO, n.subtract(ONE));

            // x = ad mod n
            BigInteger x = a.modPow(numTimesDividedUntilOdd, n);
            if (x.equals(ONE) || x.equals(n.subtract(ONE)))
                continue;

            int r = 1;
            for (; r < iterations; r++) {
                x = x.modPow(TWO, n);
                if (x.equals(ONE))
                    return false;
                if (x.equals(n.subtract(ONE)))
                    break;
            }

            // If we get here that means that the number of times looped
            // equals the number of times we needed to divided n by 2. With
            // that said there is probable chances that this number is not
            // prime. In other words, none of the steps made x equal n-1.
            if (r == iterations)
                return false;
        }

        // If we get here, then the tests run against the number
        // show that the probability of the number being prime is true.
        return true;
    }


    private static BigInteger randomNumberInRange(BigInteger bottom, BigInteger upper) {
        Random r = new Random();
        BigInteger n;
        do {
            n = new BigInteger(upper.bitLength(), r);
        }
        // We need this because we will get an error if we
        // try to generate a random within the range of Zero.
        while (n.compareTo(bottom) < 0 || n.compareTo(upper) > 0);

        return n;
    }

    public static void main(String[] args) {
        // run with -ea to enable assertions
        String[] primes = { "1", "3", "113"};
        String[] nonPrimes = { "102", "2932021007405" };

        int certainty = 4000;
        for (String p : primes)
            System.out.println(isProbablePrime(new BigInteger(p), certainty) + " : " + p);

        /**
         *  TODO: Investigate: 102 is being reported as prime. Not a Carmichael number...
         */
        for (String n : nonPrimes)
            System.out.println(isProbablePrime(new BigInteger(n), certainty) + " : " + n);
    }
}