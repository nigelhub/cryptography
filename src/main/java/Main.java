import function.FastExponentiation;
import function.MillerRabin;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by nigelstuart on 12/3/16.
 */
public class Main {


    public static void main(String[] args) {

        BigInteger base = BigInteger.TEN;
        BigInteger exponent = BigInteger.valueOf(237812);
        BigInteger modulus = BigInteger.valueOf(32574);
        BigInteger result = FastExponentiation.fastExponentiation(base, exponent, modulus);



        //MillerRabin.checkIfPrime(BigInteger.valueOf(561));

//        MillerRabin.checkIfPrime(BigInteger.valueOf(67));
//        MillerRabin.checkIfPrime(BigInteger.valueOf(1287));
//        MillerRabin.checkIfPrime(BigInteger.valueOf(9091));
//        MillerRabin.checkIfPrime(BigInteger.valueOf(3587));
//        MillerRabin.checkIfPrime(BigInteger.valueOf(104491));

    }

}
