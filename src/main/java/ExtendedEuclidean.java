
import java.math.BigInteger;


/**
 * The Euclidean Algorithm computes GCD(a,b)
 *
 * The Extended Euclidean Algorithm gives out 2 integers xx and yy such that
 * ax+by=GCD(a,b)ax+by=GCD(a,b); mainly used when GCD(a,b)=1
 *
 */
// TODO: implement both.
class ExtendedEuclidean
{

    /**
     * Performs the Extended Euclidean algorithm to find the gcd of A and B
     *
     * @param a - Positive number
     * @param b - Positive number
     * @return
     */
    public static BigInteger find(BigInteger a, BigInteger b) {

        if (b.intValue() == 0) {
            return a;
        }
        else {
            return find(b, a.mod(b));
        }
    }


    public static void main (String args[])
    {
        BigInteger a = BigInteger.valueOf(34293874);
        BigInteger b = BigInteger.valueOf(690487542);
        System.out.println("answer is " + find(a, b)); // answer is 4

        a = BigInteger.valueOf(5555);
        b = BigInteger.valueOf(4444);
        System.out.println("answer is " + find(a, b)); // answer is 111
    }
}
