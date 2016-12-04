package function;

import java.math.BigInteger;

import helper.Util;

/**
 * The Miller-Rabin algorithm provides a test which determines
 * if a given number is prime or not.  This tests gives a higher
 * assurance of probability than the fermat test.  This test is
 * great for dealing with carmichael numbers.
 *
 * If used the following variables:
 *
 *   n - The number to test if prime or not.
 *   b -
 *   m -
 *   n -
 *   r -
 *
 *
 */
public class MillerRabin {

    final private static BigInteger ZERO = BigInteger.ZERO;
    final private static BigInteger ONE = BigInteger.ONE;
    final private static BigInteger TWO = BigInteger.valueOf(2);
    final private static int attempts = 20;

    public static boolean checkIfPrime(BigInteger n) {

        System.out.println("\n==== Miller Rabin Test ====");
        System.out.println("Test if " + n + " is prime");
        System.out.println("\t1. find n - 1 = 2^k * m");
        System.out.println();
        if (n.equals(TWO)) {
            System.out.println("Yes, 2 is prime.");
            return true;
        }

        if (n.equals(ZERO) || n.equals(ONE) || n.mod(TWO).equals(ZERO)) {
            System.out.println("No, 0, 1 or a number divisible by two is not prime.");
            return false;
        }

        // Generate 2 ^ r * m
        // r is incremented each time division by 2 provides no remainder.
        int r = 0;
        BigInteger m = n.subtract(ONE);
        BigInteger nMinusOne = n.subtract(ONE);
        while (m.mod(TWO).equals(ZERO)) {
            System.out.println("r: " + r);
            m = m.divide(TWO);
            r++;
        }

        for (int i = 0; i < attempts; i++) {
            // Picking a random number
            BigInteger b = Util.randomBigInteger(ONE, n.subtract(ONE));
            System.out.println("b : " + b);

            // Compute b ^ m mod n
            BigInteger z = FastExponentiation.fastExponentiation(b, m, n);
            System.out.println("z : " + z);

            // If y = 1 mod n or -1 mod n skip and try next random number
            if (!z.equals(ONE) && !z.equals(nMinusOne)) {
                boolean isWitness = false;
                for (int j = 0; j < r; j++) {
                    z = FastExponentiation.fastExponentiation(
                            b, TWO.pow(j).multiply(m), n);

                    // n is a composite
                    if (z.equals(ONE)) {
                        System.out.println("z is a composite.");
                        return false;
                    }

                    // b is a witness to n primality
                    if (z.equals(nMinusOne)) {
                        isWitness = true;
                        System.out.println("z is a witness to n.");
                        break;
                    }
                }

                if (!isWitness) {
                    System.out.println("z is not a witness.");
                    return false;
                }
            }
        }
        return true;
    }

}
