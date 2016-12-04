package function;

import java.math.BigInteger;

public class FastExponentiation {

    final private static BigInteger ZERO = BigInteger.ZERO;
    final private static BigInteger ONE = BigInteger.ONE;
    final private static BigInteger TWO = BigInteger.valueOf(2);

    /**
     *
     * The operation of modular exponentiation calculates the
     * remainder when an integer b (the base) raised to the
     * eth power (the exponent), be, is divided by a positive
     * integer m (the modulus).
     *
     * In symbols, given base b, exponent e, and modulus m, the
     * modular exponentiation c is: c ≡ be (mod m).
     *
     * @param b - The base.
     * @param e - The exponent.
     * @param m - The modulus, which should be positive.
     * @return
     */
    public static BigInteger fastExponentiation(BigInteger b,
                                                BigInteger e,
                                                BigInteger m) {
        return findFastExponentiation(
                b,
                e,
                m,
                ONE);
    }


    private static BigInteger findFastExponentiation(
            BigInteger b,
            BigInteger e,
            BigInteger m,
            BigInteger result) {

        System.out.println("\nbase:   " + b);
        System.out.println("expo:   " + e);
        System.out.println("mod:    " + m);
        System.out.println("result: " + result);

        if (e.equals(ZERO)) {
            return result;
        }
        else if (e.mod(TWO).equals(ONE)) {
            return findFastExponentiation(b,
                    e.subtract(ONE),
                    m,
                    b.multiply(result).mod(m));
        }
        else {
            return findFastExponentiation(
                    b.multiply(b).mod(m),
                    e.divide(TWO),
                    m,
                    result);
        }
    }
}