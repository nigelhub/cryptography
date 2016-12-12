/**
 * Created by nigelstuart on 12/4/16.
 */
public class MultiplicativeInverse {

    // private variables
    private int varInverseX;

    // constructors
    /**
     * a constructor
     * find the inverse of x in group m
     * @param x
     * @param m
     * @author tandhy
     */
    public MultiplicativeInverse(long x, long m)
    {
        // y is a multiplicative inverse of x modulo m if (xy) mod m = 1
        int y = 1;
        long remaining = 0;
        while (remaining != 1)
        {
            remaining = ( x * y ) % m ;
            y++;
            // while loop will end if remaining = 1 and we will get Y
        }

        varInverseX = y - 1;
    }

    /**
     * @return the inverse of x in group m
     * @author tandhy
     */
    public int getInverseX() {
        return varInverseX;
    }

}
