import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by nigelstuart on 12/12/16.
 */
public class ExtendedEuclideanTest {


    @Test
    public void findTest() {
        BigInteger a = BigInteger.valueOf(34293874);
        BigInteger b = BigInteger.valueOf(690487542);
        assertEquals(ExtendedEuclidean.find(a, b), BigInteger.valueOf(2));

        a = BigInteger.valueOf(5555);
        b = BigInteger.valueOf(4444);
        assertEquals(ExtendedEuclidean.find(a, b), BigInteger.valueOf(1111));
    }
}
