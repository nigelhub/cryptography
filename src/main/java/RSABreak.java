import java.math.BigInteger;

/**
 * Created by nigelstuart on 12/12/16.
 */
public class RSABreak {

    /**
     *
     * I found this really nice explaination provided by Jeff Moser
     *
     *
     * You can "break" RSA by knowing how to factor "n" into its "p" and "q" prime factors
     * The easiest way is probably to check all odd numbers starting just below the square root of n:
     * Floor[Sqrt[n]] = 100711415
     *
     *    n = 10142789312725007
     *    e = 5
     *
     *    Floor[Sqrt[10142789312725007]] = 100711415
     *
     *    You would get the first factor in 4 tries:
     *    10142789312725007 mod 100711415 = 100711367
     *    10142789312725007 mod 100711413 = 100711373
     *    10142789312725007 mod 100711411 = 100711387
     *    10142789312725007 mod 100711409 = 0 <-- Winner since it evenly divides n
     *
     *    So we have
     *    p = 100711409
     *
     *    Now,
     *    q = n / p
     *      = 10142789312725007 / 100711409
     *      = 100711423
     *
     *    Why is this important? It's because d is a special number such that
     *
     *    d = e^-1 mod phi(n)
     *      = e^-1 mod (p-1)*(q-1)
     *
     *    We can verify this
     *
     *    d * e = 40571156445208705 = 1 mod 10142789111302176
     *
     *    This is important because if you have a plaintext message m then the ciphertext is
     *
     *    c = m^e mod n
     *
     *    and you decrypt it by
     *
     *    m = c^d = (m^e)^d = (m^(e*d)) = (m^(e*e^-1)) = m^1 (mod n)
     *
     *    For example, I can "encrypt" the message 123456789 using your teacher's public key:
     *
     *    m = 123456789
     *    This will give me the following ciphertext:
     *
     *    c = m^e mod n
     *      = 123456789^5 mod 10142789312725007
     *      = 7487844069764171
     *
     *    (Note that "e" should be much larger in practice because for small values of "m" you don't even exceed n)
     *
     *    Anyways, now we have "c" and can reverse it with "d"
     *
     *    m = c^d mod n
     *      = 7487844069764171^8114231289041741 mod 10142789312725007
     *      = 123456789
     *
     *    Obviously, you can't compute "7487844069764171^8114231289041741" directly because it has 128,808,202,574,088,302 digits, so you must use the modular exponentiation trick.
     *
     */

    public static void crack(int n, int e) {

        System.out.println(Math.sqrt(n));
        System.out.println(Math.sqrt(n));
        System.out.println(Math.sqrt(n));
        System.out.println(Math.sqrt(n));
    }

    public static void main(String[] args) {
        int n = 115;
        int e = 5;
        crack(n, e);
        System.out.println("\n\n"+PrimitiveRoot.find(BigInteger.valueOf(n)));
    }

}
