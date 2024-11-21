package codes;

import java.util.Scanner;

public class VigenereCipher {

    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        key = generateKey(plaintext, key);
        
        for (int i = 0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);
            char k = key.charAt(i);

            char c = (char) (((p - 'A') + (k - 'A')) % 26 + 'A');
            ciphertext.append(c);
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        key = generateKey(ciphertext, key);

        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            char k = key.charAt(i);

            char p = (char) (((c - 'A') - (k - 'A') + 26) % 26 + 'A');
            plaintext.append(p);
        }
        return plaintext.toString();
    }

    private static String generateKey(String text, String key) {
        StringBuilder expandedKey = new StringBuilder(key);
        
        while (expandedKey.length() < text.length()) {
            expandedKey.append(key);
        }
        return expandedKey.substring(0, text.length());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext (uppercase letters only): ");
        String plaintext = scanner.nextLine().toUpperCase();

        System.out.print("Enter key (uppercase letters only): ");
        String key = scanner.nextLine().toUpperCase();

        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted text: " + ciphertext);

        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }
}
