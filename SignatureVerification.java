package codes;

import javax.crypto.Cipher;
import java.security.*;
import java.util.*;

public class SignatureVerification {

	public static void main(String[] args) throws Exception {
		KeyPair xKeyPair = genarateKeyPair();
		PublicKey xPublicKey = xKeyPair.getPublic();
		PrivateKey xPrivateKey = xKeyPair.getPrivate();
		
		KeyPair yKeyPair = genarateKeyPair();
		PublicKey yPublicKey = yKeyPair.getPublic();
		PrivateKey yPrivateKey = yKeyPair.getPrivate();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Message : ");
		String message = scanner.nextLine();
		
		byte[] hash = HashMessage(message);
		System.out.println("Hash : "+byteToHex(hash));
		
		byte[] signature = signMessage(new String(hash),xPrivateKey);
		System.out.println("Signature : "+ byteToHex(signature));
		
		byte[] encryptedMessage = encryptMessage(message,yPublicKey);
		System.out.println("Encrypted Message : " + byteToHex(encryptedMessage));
		
		String decryptedMessage = decryptMessage(encryptedMessage,yPrivateKey);
		System.out.println("decrypted Message : " + decryptedMessage);
		
		boolean isVerified = verifySegnature(new String(hash), xPublicKey, signature);
		System.out.println("Is Verified : "+ isVerified);
		
		scanner.close();
		
	}

	private static boolean verifySegnature(String string, PublicKey xPublicKey, byte[] signature) throws Exception{
		// TODO Auto-generated method stub
		Signature signature1 = Signature.getInstance("SHA256withRSA");
		signature1.initVerify(xPublicKey);
		signature1.update(string.getBytes());
		return signature1.verify(signature);
	}

	private static String decryptMessage(byte[] encryptedMessage, PrivateKey yPrivateKey) throws Exception {
		// TODO Auto-generated method stub
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE,yPrivateKey);
		byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
		return new String(decryptedBytes);
	}

	private static byte[] encryptMessage(String message, PublicKey yPublicKey) throws Exception {
		// TODO Auto-generated method stub
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, yPublicKey);
		return cipher.doFinal(message.getBytes());
	}

	private static byte[] signMessage(String string, PrivateKey xPrivateKey) throws Exception {
		// TODO Auto-generated method stub
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(xPrivateKey);
		signature.update(string.getBytes());
		return signature.sign();
	}

	private static String byteToHex(byte[] hash) {
		// TODO Auto-generated method stub
		StringBuilder hexString = new StringBuilder();
		
		for(byte b : hash) {
			hexString.append(String.format("%02x",b));
		}
		return hexString.toString();
	}

	private static byte[] HashMessage(String message) throws Exception {
		// TODO Auto-generated method stub
		MessageDigest digest = MessageDigest.getInstance("SHA256");
		return digest.digest(message.getBytes());
	}

	private static KeyPair genarateKeyPair() throws Exception {
		// TODO Auto-generated method stub
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		return generator.generateKeyPair();
	}
	

}
