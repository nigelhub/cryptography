import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * A primitive root of a prime number P is an integer G if and only if GCD( G, N )=1
 *
 */
public class PrimitiveRoot {


    public static List<BigInteger> find(BigInteger number)
    {
        List<BigInteger> coprimes = coprimesOf(number);
        List<BigInteger> primitiveRoots = new ArrayList<BigInteger>();
        BigInteger temp = null;
        List<BigInteger> residues = new ArrayList<BigInteger>();

        for (BigInteger coprime : coprimes) {
            residues.clear();

            verifyCongruent: for (int k = 1; k < number.intValue(); k++) {
                temp = (coprime.pow(k)).mod(number);

                // If zero of the same as one in the list already skip.
                if (temp == BigInteger.ZERO || residues.contains(temp)) {
                    break verifyCongruent;
                }
                residues.add(temp);
            }

            if (residues.size() == number.intValue() - 1) {
                primitiveRoots.add(coprime);
            }
        }

        System.out.println(primitiveRoots.size() + " roots found.");
        return primitiveRoots;
    }


    /**
     * A number is co-prime if the only positive integer
     * that divides it is 1.
     *
     * @param number
     * @return
     */
    private static List<BigInteger> coprimesOf(BigInteger number) {
        List<BigInteger> coprimes = new ArrayList<BigInteger>();
        BigInteger decrementer = number.subtract(BigInteger.ONE);

        while (decrementer.compareTo(BigInteger.ZERO) != 0) {
            if (decrementer.gcd(number).compareTo(BigInteger.ONE) == 0) {
                coprimes.add(decrementer);
            }
            decrementer = decrementer.subtract(BigInteger.ONE);
        }
        return coprimes;
    }


    public static void main(String[] args) {
        System.out.println(PrimitiveRoot.find(BigInteger.valueOf(23)));
    }

}