package codes;

import java.util.Scanner;

public class VernamCipher {

    // Method to perform encryption/decryption
    public static String vernamCipher(String text, String key) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            // XOR each character of text with the corresponding character of the key
            char encryptedChar = (char) (text.charAt(i) ^ key.charAt(i));
            result.append(encryptedChar);
        }
        
        return result.toString();
    }

    @SuppressWarnings("resource")
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input plaintext
        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();
        
        // Input key
        System.out.print("Enter key (same length as plaintext): ");
        String key = scanner.nextLine();

        // Check if the key length matches the plaintext length
        if (plaintext.length() != key.length()) {
            System.out.println("Error: Key must be the same length as the plaintext!");
            return;
        }

        // Encrypt the plaintext
        String ciphertext = vernamCipher(plaintext, key);
        System.out.println("Encrypted text: " + ciphertext);

        // Decrypt the ciphertext (Vernam Cipher decryption is the same as encryption)
        String decryptedText = vernamCipher(ciphertext, key);
        System.out.println("Decrypted text: " + decryptedText);
        
        scanner.close();
    }
}
