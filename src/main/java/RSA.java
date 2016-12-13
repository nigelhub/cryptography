import java.math.BigInteger;
import java.security.SecureRandom;


public class RSA {


    private final static SecureRandom randomNumber = new SecureRandom();

    // RSA attributes.
    public BigInteger n;
    public BigInteger publicKey;
    private BigInteger privateKey;
    private BigInteger modulus;
    private BigInteger p;
    private BigInteger q;
    private BigInteger phi;


    /**
     * Constructor for RSA.
     */
    RSA(int max) {
        this.p = BigInteger.probablePrime(max/2, randomNumber);
        this.q = BigInteger.probablePrime(max/2, randomNumber);
        this.n = p.multiply(q);
        this.phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        this.publicKey  = new BigInteger("65537");  // aka e
        this.privateKey = publicKey.modInverse(phi);  // TODO: replace the Java module for inverse with mine.
    }


    /**
     * Encrypt the plain message recieved.
     * @param message
     * @return
     */
    BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, n);
    }


    /**
     * Decrypt the given message with mod n.
     * @param encryptedMsg - The encrypted message.
     * @return Decrypted numberical value of plain message.
     */
    BigInteger decrypt(BigInteger encryptedMsg) {
        return encryptedMsg.modPow(privateKey, n);
    }

    public String toString() {
        String s = "";
        s += "RSA DETAILS"                 + "\n";
        s += "==========="                 + "\n";
        s += "public key  = " + publicKey  + "\n";
        s += "private key = " + privateKey + "\n";
        s += "p           = " + p          + "\n";
        s += "q           = " + q          + "\n";
        s += "n           = " + n          + "\n";
        s += "phi         = " + phi        + "\n";
        return s;
    }

    /**
     * Take the decrypted value, which is in BigInteger format and converts it to
     * a string so that it resembles exactly what the user supplied.
     *
     * @param decryptedMsg - The BigInteger message after it as been decrypted.
     * @return string - The human readable message in alphabet form.
     */
    public String makeHumanReadable(BigInteger decryptedMsg) {
        byte[] ba = decryptedMsg.toByteArray();
        return new String(ba);
    }

    public static void main(String[] args) {

        String msg = "CS789 is a fun challenge.";
        int n = 779;

        RSA rsa = new RSA(n);
        System.out.println(rsa);
        byte[] bytes = msg.getBytes();
        BigInteger message = new BigInteger(bytes);
        BigInteger encrypt = rsa.encrypt(message);
        BigInteger decrypt = rsa.decrypt(encrypt);

        System.out.println("RESULT:");
        System.out.println("=======");
        System.out.println("Plain Message      = " + message);
        System.out.println("Encrypted Message  = " + encrypt);
        System.out.println("Decrypted Message  = " + decrypt);
        System.out.println("Normalized Message = " + rsa.makeHumanReadable(decrypt));
    }
}

