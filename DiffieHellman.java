package codes;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DiffieHellman {
    final static BigInteger PRIME = new BigInteger("23");    // Shared prime
    final static BigInteger GENERATOR = new BigInteger("5"); // Shared generator

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();

        // Alice's keys
        BigInteger alicePrivate = new BigInteger(5, random);
        BigInteger alicePublic = GENERATOR.modPow(alicePrivate, PRIME);

        // Bob's keys
        BigInteger bobPrivate = new BigInteger(5, random);
        BigInteger bobPublic = GENERATOR.modPow(bobPrivate, PRIME);

        // Anna's keys pretending to be Alice and Bob
        BigInteger annaPrivateAlice = new BigInteger(5, random);
        BigInteger annaPublicAlice = GENERATOR.modPow(annaPrivateAlice, PRIME);

        BigInteger annaPrivateBob = new BigInteger(5, random);
        BigInteger annaPublicBob = GENERATOR.modPow(annaPrivateBob, PRIME);

        // Key exchange simulation: Anna intercepts messages
        BigInteger aliceReceivedKey = annaPublicBob;  // Anna pretending to be Bob
        BigInteger bobReceivedKey = annaPublicAlice;  // Anna pretending to be Alice

        // Alice and Bob generate their shared keys using Anna's intercepts
        BigInteger aliceSharedKey = aliceReceivedKey.modPow(alicePrivate, PRIME);
        BigInteger bobSharedKey = bobReceivedKey.modPow(bobPrivate, PRIME);

        // Anna generates her shared keys with Alice and Bob
        BigInteger annaSharedKeyWithAlice = alicePublic.modPow(annaPrivateBob, PRIME);
        BigInteger annaSharedKeyWithBob = bobPublic.modPow(annaPrivateAlice, PRIME);

        // Output the results
        System.out.println("Alice's Shared Key (Anna pretending to be Bob): " + aliceSharedKey);
        System.out.println("Bob's Shared Key (Anna pretending to be Alice): " + bobSharedKey);
        System.out.println("Anna's shared key with Alice: " + annaSharedKeyWithAlice);
        System.out.println("Anna's shared key with Bob: " + annaSharedKeyWithBob);
    }
}

