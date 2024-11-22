package codes;

import java.util.Scanner;

public class SimpleSubstitutionCipher {

    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();

        for (char c : plaintext.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                ciphertext.append(key.charAt(c - 'A'));
            } else {
                ciphertext.append(c);             }
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();

        for (char c : ciphertext.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                int index = key.indexOf(c);
                plaintext.append((char) ('A' + index));
            } else {
                plaintext.append(c); 
            }
        }
        return plaintext.toString();
    }

    @SuppressWarnings("resource")
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        System.out.print("Enter substitution key (26 unique uppercase letters): ");
        String key = scanner.nextLine().toUpperCase();

        if (key.length() != 26 || !key.matches("[A-Z]+")) {
            System.out.println("Invalid key. Please enter exactly 26 unique uppercase letters.");
            return;
        }

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted text: " + ciphertext);

        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }
}

