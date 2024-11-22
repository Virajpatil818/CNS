package Assignment8;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class assignment8 {

    public static void main(String[] args) throws Exception{
        File imageFile=new File("D:\\CnsPractice\\src\\Assignment8\\image.png");
        byte[] imageBytes=new FileInputStream(imageFile).readAllBytes();

        KeyGenerator keyGen =KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey key=keyGen.generateKey();

        byte[]encryptedImage=encryptImage(imageBytes,key);
        byte[]hash=genHash(encryptedImage);


        if(verifyHash(encryptedImage,hash)){
            System.out.println("Hash verification passed. Decrypting the image...");
            byte[]decryptedImage=decryptImage(encryptedImage,key);
            FileOutputStream fos=new FileOutputStream("D:\\CnsPractice\\src\\Assignment8\\decryptedImage.png");
            fos.write(decryptedImage);
            fos.close();
            System.out.println("Image decrypted and saved as 'decrypted_image.jpg'.");

        }else{
            System.out.println("Hash verification failed. Possible tampering detected!");
        }

    }
    public static byte[] encryptImage(byte[] imageBytes, SecretKey key) throws Exception {
        Cipher cipher=Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,key);
        return cipher.doFinal(imageBytes);

    }
    public static byte[] decryptImage(byte[] encryptedImage,SecretKey key) throws Exception{
          Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,key);
            return cipher.doFinal(encryptedImage);
    }

    public static byte[] genHash(byte[] imageBytes) throws Exception{
         MessageDigest digest= MessageDigest.getInstance("SHA-256");
         return digest.digest(imageBytes);
    }
    public static boolean verifyHash(byte[] imageBytes,byte[] hash) throws Exception{
        byte[] calculatedHash=genHash(imageBytes);
        return Arrays.equals(calculatedHash,hash);
    }
}
