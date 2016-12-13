
import java.util.*;
import java.math.BigInteger;



/**
 * e baby-step giant-step is a meet-in-the-middle algorithm for computing the
 * discrete logarithm. The discrete log problem is of fundamental importance
 * to the area of public key cryptography. This will come in handy for Eve to use
 * when working to hack DiffieHellman.
 */
public class BabyStepGiantStep {

    private static BigInteger ONE = BigInteger.ONE;


    public static BigInteger find(BigInteger base, BigInteger publicKey, BigInteger modulus) {

        BigInteger m = SquareRoot.find(modulus).add(ONE);
        Hashtable hash = new Hashtable();
        BigInteger basePower = BigInteger.ONE;

        // Build the table
        // base^j is the key nice name
        // index j is the value assigned to the key
        for (BigInteger j=BigInteger.ZERO; j.compareTo(m) < 0 ; j=j.add(ONE)) {
            hash.put(basePower,j);
            basePower = basePower.multiply(base).mod(modulus);
        }

        // Get inverse of base^m modulo p
        BigInteger inverse = base.modPow(m,modulus).modInverse(modulus);
        BigInteger key = new BigInteger(publicKey.toByteArray());

        // Search the hash for a key base^j
        // While iterating through  the loop we will use y to match keys in the hash.
        BigInteger keyValue;
        for (BigInteger i = BigInteger.valueOf(0);i.compareTo(m)<0;i=i.add(ONE)) {
            keyValue = (BigInteger)hash.get(key);

            if (keyValue != null) {
                // Ahah! Found it! We found a match in the hash table!!
                return i.multiply(m).add(keyValue);
            }

            key = key.multiply(inverse).mod(modulus);
        }
        throw new NoSuchElementException("Could not find a discrete logarithm...");
    }


    public static void main(String[] args) {

        // solve for log2 3 in Z29
		BigInteger G = BigInteger.valueOf(2);
		BigInteger base = BigInteger.valueOf(15);
		BigInteger number = BigInteger.valueOf(29);
		BigInteger pk = BabyStepGiantStep.find(G, base, number);
		System.out.println("Private Key : " + pk);  // private key should be 27
    }

}

