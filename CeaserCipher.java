package codes;

import java.util.*;

public class CeaserCipher {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Text : ");
		String PlainText = scanner.nextLine();
		
		System.out.println("Enter Shifts : ");
		int shift = scanner.nextInt();
		
		String encryptedMessage = process(PlainText,shift);
		System.out.println("Encrypted Message : " + encryptedMessage);
		
		String decryptedMessage = process(encryptedMessage,-shift);
		System.out.println("Decrypted Message : " + decryptedMessage);
	    
		scanner.close();
	}

	private static String process(String text, int shift) {
		// TODO Auto-generated method stub
		StringBuilder result = new StringBuilder();
		
		for(int i=0;i<text.length();i++) {
			char character = text.charAt(i);
			
			if(Character.isUpperCase(character)) {
				char ch = (char) (((character - 'A' + shift + 26)%26)+'A');
				result.append(ch);
			}else if(Character.isLowerCase(character)) {
				char ch = (char) (((character - 'a' + shift + 26)%26)+'a');
				result.append(ch);
			}else {
				result.append(character);
			}
		}
				
		return result.toString();
	}
}
