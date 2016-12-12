import java.math.BigInteger;

/**
 * BlumBlumShub is a random number generator algorithm.
 *
 * Lecture Notes RE: Algorithm.
 *
 * Blum – Blum – Shub Random Number Generator
 *
 * 1. Randomly choose two large primes p and q , such that p = 3mod 4 and q = 3 mod 4 ( p and q to be kept secret)
 *
 * 2. Compute n = p * q ( n does not have to be secret and can be reused many times).
 *
 * 3. Choose any seed s0 within group Zn s0 and compute s1=s0^2%n, s2=s1^2%n .... for each element within the group.
 *
 * 4. , , ... 0 1 2 s s s are integers from the range of 1 to n 1 relatively prime to n .
 *   The sequence of pseudo- random bits is , , , ... b0 b1 b2 , where b0  s0 % 2, b1  s1 % 2, b2  s2 % 2 etc.
 *
 * 30. Remark: The Blum-Blum-Shub algorithm can be proved to be secure, based on the difficulty of
 *     finding “good” square roots of elements from Z n  . Since factoring n is the only way to
 *     find square roots, the difficulty of factoring n into primes provides a security proof for
 *     the Blum-Blum-Shub algorithm. This is a great algorithm to be used when a very high level of
 *     security is requested.
 *
 * 31. Remark: If a sequence of pseudo-random bits produced by the Blum – Blum – Shub Generator is
 *     not a sequence of random bits, then there is a fast probabilistic algorithm to factor n
 *     into the product of p and q .
 *
 * Created by nigelstuart on 12/5/16.
 */
public class BlumBlumShub {

    private double P;  // prime number
    private double Q;  // prime number
    private double N;  // P * Q
    private double S;  // my seed


    public BlumBlumShub(BigInteger p, BigInteger q, BigInteger S) {
        this.P = p.intValue();
        this.Q = q.intValue();
        this.N = p.multiply(q).intValue();
        this.S = S.intValue();
    }

    private double gcd(double a, double b) {
        /**
         * TODO: Investigate why BigInteger vs Double issues preventing reuse of my BigInteger gcd() method.
         */
        if(b == 0) return a;
        return gcd(b, a%b);

    }

    public BigInteger find(int i) {
        double myGcd = gcd(P, Q);

        // divide phi by the gcd found.
        // TODO: Research this more to get a better understanding.
        double l = (P -1)*(Q -1)/myGcd;

        double e = 1;
        for(int j = 1; j <= i; ++j) {
            e = e * 2 % l;
        }

        double x = S * S;
        double r = x;
        for(double j = 2; j <= e; ++j ) {
            r = r * x % N;
        }

        double number = r/ N;
        String s = Double.toString(number);
        s = s.replace(".", "");
        return new BigInteger(s);
    }

    public static void main(String[] args) {

        int numbersToGen = 40000;
        BigInteger p = BigInteger.valueOf(101);
        BigInteger q = BigInteger.valueOf(71);
        BigInteger s = BigInteger.valueOf(7);
        BlumBlumShub bbs = new BlumBlumShub(p, q, s);

        for(int i = 0; i <= numbersToGen; ++i) {
            BigInteger n = bbs.find(i);
            System.out.println(n);
        }
    }



}
