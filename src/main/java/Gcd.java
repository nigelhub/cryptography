import java.math.BigInteger;

/**
 * Created by nigelstuart on 12/11/16.
 */
class Gcd {

    public static BigInteger find(BigInteger x, BigInteger y) {

        while(y.compareTo(BigInteger.ZERO) != 0) {
                BigInteger r = x.mod(y);
                x = y;
                y = r;
            }
        System.out.print("GCD = " + x);
        return x;
    }

    public static void main(String args[]) {
        BigInteger x = BigInteger.valueOf(999999);
        BigInteger y = BigInteger.valueOf(66666);
        Gcd.find(x, y);
    }
}