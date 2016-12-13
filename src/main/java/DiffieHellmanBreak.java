import java.math.BigInteger;
import java.util.Hashtable;

/**
 * Eve is an eavesdropper. She watches what is sent between Alice and Bob,
 * but she does not alter the contents of their communications.  This type of
 * attach is referred to as the man in the middle.
 *
 */
public class DiffieHellmanBreak {

    public static void main(String[] args) {

        // get alice's private key by getting the discrete log within his public key
        DiffieHellman alice = new DiffieHellman();
        BigInteger alicePK = BabyStepGiantStep.find(alice.G, alice.PUBLIC_KEY, alice.P);
        System.out.println("Alice's Private Key = " + alicePK);

        // get bob's private key by getting the discrete log within his public key
        DiffieHellman bob = new DiffieHellman(alice.P, alice.G);
        BigInteger bobPK = BabyStepGiantStep.find(bob.G, bob.PUBLIC_KEY, bob.P);
        System.out.println("Bob's Private Key = " + bobPK);


//        DiffieHellman alice = new DiffieHellman();
//        DiffieHellman bob   = new DiffieHellman(alice.P, alice.G);
//
//        // Assume Eve got it here in real-time.
//        bob.setPeerSharedKey(alice.PUBLIC_KEY);
//        alice.setPeerSharedKey(bob.PUBLIC_KEY);
//
//        // Alice sends bob a message.
//        NetworkConnection.send(bob, alice, alice.encrypt("Hi Bob!"));
//
//        // Bob sends alice a message.
//        NetworkConnection.send(alice, bob, bob.encrypt("Hi Alice!"));
    }
}

class NetworkConnection {

    /**
     * Send an message to someone over the network.  This is just an example to show how
     * alice can not decrpty the message without the private key.
     *
     * @param to   - The "Person" the message is being send to.
     * @param from - The "Person" the message is from.
     * @param eMsg - An encrypted message.
     */
    public static void send(DiffieHellman to, DiffieHellman from, BigInteger eMsg) {
        System.out.println("\n\n**************************************");
        System.out.println("**** SENDING MESSAGE OVER NETWORK ****");
        System.out.println("**************************************");
        System.out.println(from.NAME + " is sending a message to " + to.NAME);
        NetworkConnection.manInTheMiddle(to, from, eMsg);
    }

    private static void manInTheMiddle(DiffieHellman to, DiffieHellman from, BigInteger eMsg) {
        System.out.println("Eve intercepts a message that " + from.NAME + " is sending to " + to.NAME);
        DiffieHellman eve = new DiffieHellman(from.P, from.G, from.PUBLIC_KEY);

        String msg = eve.decrypt(eMsg);
        System.out.println("Eve tries to decrypt the message which states: \"" + msg + "\"");

        System.out.println("Eve forwards the message on to " + to.NAME);
        msg = to.decrypt(eMsg);
        System.out.println(to.NAME + " decrypts the message successfully: \"" + msg + "\"");
        System.out.println("Eve was not able to see the message!");
    }
}


