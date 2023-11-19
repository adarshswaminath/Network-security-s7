import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

// sample out
// Encrypted Message:/zAcwylGTlFt2At+j5a2Lg==
// Decrypted MessageHello WOrld

public class AES {

    public static void main(String[] args) {
        try {
            SecretKey secretKey = generateAESKey();
            String originalMessage = "Hello WOrld";
            String encryptedMessage = encrypt(originalMessage, secretKey);
            System.out.println("Encrypted Message:" + encryptedMessage);
            String decryptedMessage = decrypt(encryptedMessage, secretKey);
            System.out.println("Decrypted Message" + decryptedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }
    private static String encrypt(String message, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes()));
    }

    private static String decrypt(String encryptedMessage, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedMessage)));
    }
}
