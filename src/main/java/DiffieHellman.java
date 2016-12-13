import java.math.BigInteger;

import static com.sun.tools.corba.se.idl.constExpr.Expression.one;

/**
 * Diffie Hellman is an algorithm used to establish a shared secret between two parties.
 * It is primarily used as a method of exchanging cryptography keys for use in symmetric
 * encryption algorithms like AES.
 *
 * STEPS:
 * 1. Alice and Bob agree on a prime number p and a base g.
 * 2. Alice chooses a secret number a, and sends Bob (g^a mod p).
 * 3. Bob chooses a secret number b, and sends Alice (g^b mod p).
 * 4. Alice computes ((g^b mod p)^a mod p)
 * 5. Bob computes ((g^a mod p) b mod p)
 *
 */
public class DiffieHellman {

    public String NAME;                // The name of the user for example purposes.
    public BigInteger P;               // The shared prime number between alice and bob.
    public BigInteger G;               // The shared base number, which should be a primitive root of P modulo P.
    public BigInteger PRIVATE_KEY;     // Secret number that only alice or bob knows.  It is less than P.
    public BigInteger PUBLIC_KEY;      // Number shared between alice and bob
    public BigInteger SHARED_KEY;      // Peers shared public key
    public BigInteger X;               // The identical value Bob and Alice have without directly sharing.


    /**
     * This emulates Alice because she produces the prime and
     * base number, which gets shared with Bob.
     */
    public DiffieHellman() {
        NAME = "Alice";
        P = BigInteger.valueOf(71);
        G = BigInteger.valueOf(20);//PrimitiveRoot.find(P).get(1);
        PRIVATE_KEY = BigInteger.valueOf(10);
        PUBLIC_KEY = G.modPow(PRIVATE_KEY, P);
    }

    /**
     * This emulates Bob because he gets info about the
     * prime number and the base from Alice.
     *
     * @param prime - prime number
     * @param base  - the base
     */
    public DiffieHellman(BigInteger prime, BigInteger base) {
        this.NAME = "Bob";
        this.P = prime;
        this.G = base;
        PRIVATE_KEY = BigInteger.valueOf(6);
        PUBLIC_KEY = G.modPow(PRIVATE_KEY, P);
    }

    /**
     * This emulates Eve.  We'll assume the data taken into this
     * constructor are sniffed out over the network.
     *
     * @param prime - prime number
     * @param base  - the base
     */
    public DiffieHellman(BigInteger prime, BigInteger base, BigInteger sharedKey) {
        this.NAME = "Eve";
        this.P = prime;
        this.G = base;
        PRIVATE_KEY = BigInteger.valueOf(7);
        PUBLIC_KEY = G.modPow(PRIVATE_KEY, P);
        SHARED_KEY = sharedKey;
    }

    public void setPeerSharedKey(BigInteger sharedKey) {
        SHARED_KEY = sharedKey;
    }


    public BigInteger encrypt(String msg) {
        System.out.println(NAME + " is preparing to encrypt plain message : \"" + msg + "\"");
        byte[] bytes = msg.getBytes();
        BigInteger M = new BigInteger(bytes);
        X = SHARED_KEY.modPow(PRIVATE_KEY, P);
        BigInteger e = M.multiply(X);//.mod(P);
        System.out.println(NAME + " encrypted message to be : " + e);
        return e;
    }

    public String decrypt(BigInteger encryptedMsg) {

        //X = G.modPow(PRIVATE_KEY, P);
        X = SHARED_KEY.modPow(PRIVATE_KEY, P);


        System.out.println(NAME + " got encrypted message : " + encryptedMsg);
        BigInteger decryptedMsg = encryptedMsg.divide(X);
        byte[] ba = decryptedMsg.toByteArray();
        String plainMsg = new String(ba);
        System.out.println(NAME + " decrypted message to be : " + plainMsg);
        return plainMsg;
    }

    public static BigInteger getNextPrime(String number) {
        BigInteger test = new BigInteger(number);
        while (!test.isProbablePrime(50))
            test = test.add(one);
        return test;
    }

    public String toString() {
        String s = "\n\n";
        s += "Name       = " + NAME          + "\n";
        s += "P          = " + P             + "\n";
        s += "G          = " + G             + "\n";
        s += "Secret Key = " + PRIVATE_KEY   + "\n";
        s += "X          = " + X             + "\n";
        return s;
    }

    public static void main(String args[]) {
        DiffieHellman alice = new DiffieHellman();
        DiffieHellman bob = new DiffieHellman(alice.P, alice.G);
        bob.setPeerSharedKey(alice.PUBLIC_KEY);
        alice.setPeerSharedKey(bob.PUBLIC_KEY);

        System.out.println("Alice  " + alice.SHARED_KEY + "^" + alice.PRIVATE_KEY + " % " + alice.P);
        BigInteger KeyACalculates = alice.SHARED_KEY.modPow(alice.PRIVATE_KEY, alice.P);

        System.out.println("Bob   : " + bob.SHARED_KEY + "^" + bob.PRIVATE_KEY + " % " + bob.P);
        BigInteger KeyBCalculates = bob.SHARED_KEY.modPow(bob.PRIVATE_KEY, bob.P);


        BigInteger e = alice.encrypt("Hi Bob");
        String d = bob.decrypt(e);
        System.out.println(alice);
        System.out.println(bob);
    }
}