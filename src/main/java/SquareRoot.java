import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by nigelstuart on 12/12/16.
 */
public class SquareRoot {

    private static BigInteger ZERO = BigInteger.ZERO;
    private static BigInteger TWO = BigInteger.valueOf(2);


    /**
     * Since I couldn't find a library to find the square root
     * of a BigInter, created this method.
     *
     * @param number - A BigInteger to find the square root of.
     * @return The square root as a BigInteger
     */
    public static BigInteger find(BigInteger number) {

        if (number.compareTo(ZERO) ==0 )
            return ZERO;

        // Do some conversions to BigDecimal
        BigDecimal two = new BigDecimal(TWO);
        BigDecimal N = new BigDecimal(number);
        byte[] array = new byte[ number.bitLength() / 16 + 1 ];
        array[0] = (byte)255;

        BigDecimal R = new BigDecimal(new BigInteger(1, array));
        R = R.subtract(R.multiply(R)
                .subtract(N)
                .divide(R.multiply(two),BigDecimal.ROUND_UP)
        );

        // While R*R is greater than N
        while (R.multiply(R).compareTo(N) > 0) {
            R = R.subtract(R.multiply(R)
                    .subtract(N)
                    .divide(R.multiply(two),
                            BigDecimal.ROUND_UP
                    )
            );
        }
        return R.toBigInteger();


    }


}
