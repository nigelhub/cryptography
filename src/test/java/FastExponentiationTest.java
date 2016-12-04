import static org.junit.Assert.assertEquals;
import java.math.BigInteger;
import org.junit.Test;
import function.FastExponentiation;



public class FastExponentiationTest {

    @Test
    public void fastExponentiation() {

        BigInteger base = BigInteger.TEN;
        BigInteger exponent = BigInteger.valueOf(237812);
        BigInteger modulus = BigInteger.valueOf(32574);
        BigInteger result = FastExponentiation.fastExponentiation(base, exponent, modulus);
        assertEquals(BigInteger.valueOf(31864), result);

        base = BigInteger.ONE;
        exponent = BigInteger.ONE;
        modulus = BigInteger.ONE;
        result = FastExponentiation.fastExponentiation(base, exponent, modulus);
        assertEquals(BigInteger.ZERO, result);
    }
}
