import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;

public class DES {
    public static void main(String[] args) throws Exception {
        String keyString = "12345678";
        byte[] keyBytes = keyString.getBytes();
        String message = "Hello World";
        byte[] encryptedByte = encrypt(message,keyBytes);
        System.out.println("Encryptes:"+ Base64.getEncoder().encodeToString(encryptedByte));
        String decryptedMessage = decrypt(encryptedByte,keyBytes);
        System.out.println("Decrypted "+decryptedMessage);
    }
    public static byte[] encrypt(String message,byte[] keyBytes) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(new DESKeySpec(keyBytes));
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(message.getBytes());
    }
    public static String decrypt(byte[] encryptedBytes,byte[] keyBytes) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(new DESKeySpec(keyBytes));
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptBytes);
  
  }
}