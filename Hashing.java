package codes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.*;

public class Hashing {
	

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter text : ");
		String message = scanner.nextLine();
		
		byte[] hash = HashMessage1(message);
		System.out.println("Hash: " + byteToHex1(hash));
		
		String receivedMessage = "Hello Worlr";
		byte[] receivedHash = HashMessage1(receivedMessage);
		
		if(Arrays.equals(hash, receivedHash)) {
			System.out.println("Message is verified.");
		}else {
			System.out.println("Warning");
		}
		
		scanner.close();

	}

	private static String byteToHex1(byte[] bytes) {
		// TODO Auto-generated method stub
		StringBuilder hexString = new StringBuilder();
		for(byte b:bytes) {
			hexString.append(String.format("%02x",b));
		}
		return hexString.toString();
	}

	private static byte[] HashMessage1(String message) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		
		return digest.digest(message.getBytes());
	}

}
