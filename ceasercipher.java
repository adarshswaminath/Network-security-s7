import java.util.Scanner;
public class ceasercipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the message ");
        String message = scanner.nextLine();
        int shiftKey = 3;
        String encrypted = encrypt(message,shiftKey);
        System.out.println("Encrypted: "+ encrypted);
        System.out.println("Decrypted: "+ encrypt(encrypted,26-3));
        scanner.close();
    }
    public static String encrypt(String message,int shiftKey){
        StringBuilder result = new StringBuilder();
        for(char ch: message.toCharArray())
        result.append(Character.isLetter(ch) ? (char) ((ch - (Character.isUpperCase(ch) ? 'A' : 'a') + shiftKey) % 26 + (Character.isUpperCase(ch) ? 'A' : 'a')) : ch);
        return result.toString();
    }
}