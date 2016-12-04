import function.MillerRabin;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.math.BigInteger;

/**
 * Created by nigelstuart on 12/3/16.
 */



public class MillerRabinTest {


    @Test
    public void validateNumbers() {
        assertFalse(MillerRabin.checkIfPrime(BigInteger.valueOf(788)));
        assertTrue(MillerRabin.checkIfPrime(BigInteger.valueOf(67)));
        assertFalse(MillerRabin.checkIfPrime(BigInteger.valueOf(99)));
        assertTrue(MillerRabin.checkIfPrime(BigInteger.valueOf(97)));

        // Carmichael numbers to test.
        assertFalse(MillerRabin.checkIfPrime(BigInteger.valueOf(561)));
        assertFalse(MillerRabin.checkIfPrime(BigInteger.valueOf(1105)));
        assertFalse(MillerRabin.checkIfPrime(BigInteger.valueOf(1729)));
    }

}
