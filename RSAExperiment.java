package codes;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSAExperiment {
    private BigInteger n; // Modulus
    private BigInteger e; // Public exponent
    private BigInteger d; // Private exponent

    // Key generation method
    public void generateKeys(int keySize) {
        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(keySize / 2, random);
        BigInteger q = BigInteger.probablePrime(keySize / 2, random);

        n = p.multiply(q); // Modulus
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); // Euler's totient

        e = BigInteger.probablePrime(keySize / 2, random); // Public exponent
        while (!phi.gcd(e).equals(BigInteger.ONE)) { // Ensure e is coprime with phi
            e = e.add(BigInteger.ONE);
        }

        d = e.modInverse(phi); // Private exponent
    }

    // Encrypt a message
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n); // Encryption: (message^e) % n
    }

    // Decrypt a message
    public BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(d, n); // Decryption: (encrypted^d) % n
    }

    public static void main(String[] args) {
        RSAExperiment rsa = new RSAExperiment();
        Scanner scanner = new Scanner(System.in);

        // Key size configuration
        int keySize = 1024;
        System.out.println("Generating RSA key pair...");

        // Measure key generation time
        long startTime = System.nanoTime();
        rsa.generateKeys(keySize);
        long endTime = System.nanoTime();
        System.out.println("Keys generated successfully!");
        System.out.println("Key Generation Time: " + (endTime - startTime) / 1_000_000 + " ms");

        // Get user input
        System.out.print("\nEnter a string to encrypt: ");
        String input = scanner.nextLine();

        // Convert string to BigInteger
        BigInteger message = new BigInteger(input.getBytes());
        System.out.println("Original Message as BigInteger: " + message);

        // Measure encryption time
        startTime = System.nanoTime();
        BigInteger encrypted = rsa.encrypt(message);
        endTime = System.nanoTime();
        System.out.println("Encrypted Message: " + encrypted);
        System.out.println("Encryption Time: " + (endTime - startTime) / 1_000_000 + " ms");

        // Measure decryption time
        startTime = System.nanoTime();
        BigInteger decrypted = rsa.decrypt(encrypted);
        endTime = System.nanoTime();
        String decryptedMessage = new String(decrypted.toByteArray());
        System.out.println("Decrypted Message: " + decryptedMessage);
        System.out.println("Decryption Time: " + (endTime - startTime) / 1_000_000 + " ms");

        // Verify correctness
        if (input.equals(decryptedMessage)) {
            System.out.println("\nEncryption and Decryption successful!");
        } else {
            System.out.println("\nError: Decryption did not match the original input.");
        }

        scanner.close();
    }
}
