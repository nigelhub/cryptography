import java.math.BigInteger;

/**
 * Created by nigelstuart on 12/4/16.
 */
public class MultiplicativeInverse {


    /**
     * Get the inverse of a in group m.  This is a naive method
     * to find modulor multiplicative inverse of 'a' under modulo 'm'.
     *
     * @param a - The number to get the inverse of.
     * @param m - The group
     */
    public static BigInteger find(BigInteger a, BigInteger m) {

        a = a.mod(m);
        BigInteger x = BigInteger.ONE;
        for( ; x.compareTo(m) < 0; ) {
            if (a.multiply(x).mod(m).equals(BigInteger.ONE)) {
                System.out.println("Multiplicative Inverse of " + a + " and " + m + " is " + x );
                return x;
            }
            x = x.add(BigInteger.ONE);
        }

        System.out.println("No Multiplicative Inverse exists...");
        return BigInteger.ZERO;
    }


    public static void main(String[] args) {
        BigInteger A = BigInteger.valueOf(3);
        BigInteger M = BigInteger.valueOf(11);
        BigInteger I = find(A, M);
    }

}
