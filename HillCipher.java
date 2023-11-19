import java.util.Scanner;
public class HillCipher {
    public static int[][] keymat = {
        {1, 2, 1},
        {2, 3, 2},
        {2, 2, 1}
    };
    public static String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the plain text for encryption: ");
        String text = sc.next().toUpperCase().replaceAll("\\s", "");
        sc.close();

        text = padText(text);

        System.out.println("Padded text: " + text);
        String encryptedMessage = processText(text, true);
        System.out.println("Encrypted message: " + encryptedMessage);
        String decryptedMessage = processText(encryptedMessage, false);
        System.out.println("Decrypted message: " + decryptedMessage);
    }

    private static String padText(String text) {
        int n = text.length() % 3;
        if (n != 0) {
            for (int i = 0; i < 3 - n; i++) {
                text += 'X';
            }
        }
        return text;
    }

    private static String processText(String text, boolean encrypt) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 3) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);
            char c = text.charAt(i + 2);

            int[] encryptedVector = calculateVector(a, b, c, encrypt);

            result.append((char) ('A' + encryptedVector[0] % 26));
            result.append((char) ('A' + encryptedVector[1] % 26));
            result.append((char) ('A' + encryptedVector[2] % 26));
        }

        return result.toString();
    }

    private static int[] calculateVector(char a, char b, char c, boolean encrypt) {
        int[] vector = {a - 'A', b - 'A', c - 'A'};
        int[] result = new int[3];

        for (int i = 0; i < 3; i++) {
            result[i] = (keymat[i][0] * vector[0] + keymat[i][1] * vector[1] + keymat[i][2] * vector[2]) % 26;

            if (!encrypt) {
                result[i] = (result[i] < 0) ? (26 + result[i]) : result[i];
            }
        }

        return result;
    }
}
