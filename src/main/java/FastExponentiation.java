import java.math.BigInteger;

/**
 * This algorithim makes it more efficient to compute numbers to high powers.
 * For example, how do you efficiently compute an for integer n? You could
 * multiply a*a*a*…*a, n-1 multiplications, but there are much more efficient
 * ways. For example, a8 could be computed by ((a2)2)2 in three multiplications.
 *
 * Created by nigelstuart on 12/11/16.
 */
public class FastExponentiation {

    private static BigInteger TWO = BigInteger.valueOf(2);


    public static BigInteger find(BigInteger base, BigInteger exponent, BigInteger modulus) {
        BigInteger result = BigInteger.ONE;

        // While exponent is larger than zero
        while (exponent.compareTo(BigInteger.ZERO) == 1) {

            // If equal to Zero
            if (exponent.mod(TWO).compareTo(BigInteger.ONE) == 0)
                result = result.multiply(base).mod(modulus);

            exponent = exponent.shiftRight(1);
            base = base.multiply(base).mod(modulus);
        }
        System.out.println("Fast Exponentiation Result : " + result);
        return result;
    }

    public static void main(String args[]) {
        BigInteger base = BigInteger.valueOf(99);
        BigInteger exponent = BigInteger.valueOf(66);
        BigInteger modulus = BigInteger.valueOf(45);

        FastExponentiation.find(base, exponent, modulus);
    }
}
