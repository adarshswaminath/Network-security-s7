import java.util.Scanner;

public class PlayfairCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the key: ");
        String key = scanner.nextLine().toUpperCase();

        char[][] matrix = createMatrix(key);

        System.out.print("Enter the message: ");
        String message = scanner.nextLine().toUpperCase();

        String encryptedMessage = encrypt(message, matrix);

        System.out.println("Playfair Matrix:");
        displayMatrix(matrix);
        System.out.println("Encrypted Message: " + encryptedMessage);

        scanner.close();
    }

    private static char[][] createMatrix(String key) {
        char[][] matrix = new char[5][5];
        String keyWithoutDuplicates = key.replaceAll("J", "I").replaceAll("(.)(?=.*\\1)", "");

        for (int k = 0, i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                matrix[i][j] = (k < keyWithoutDuplicates.length()) ? keyWithoutDuplicates.charAt(k++) : (char) (k + 'A');

        return matrix;
    }

    private static void displayMatrix(char[][] matrix) {
        for (int i = 0; i < 5; i++, System.out.println())
            for (int j = 0; j < 5; j++)
                System.out.print(matrix[i][j] + " ");
    }

    private static String encrypt(String message, char[][] matrix) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < message.length(); i += 2) {
            char a = message.charAt(i);
            char b = (i + 1 < message.length()) ? message.charAt(i + 1) : 'X';

            result.append(encryptPair(matrix, a, b));
        }

        return result.toString();
    }

    private static String encryptPair(char[][] matrix, char a, char b) {
        int[] posA = new int[2];
        int[] posB = new int[2];
        findPosition(a, posA, matrix);
        findPosition(b, posB, matrix);

        return (posA[0] == posB[0]) ? "" + matrix[posA[0]][(posA[1] + 1) % 5] + matrix[posB[0]][(posB[1] + 1) % 5] :
                (posA[1] == posB[1]) ? "" + matrix[(posA[0] + 1) % 5][posA[1]] + matrix[(posB[0] + 1) % 5][posB[1]] :
                        "" + matrix[posA[0]][posB[1]] + matrix[posB[0]][posA[1]];
    }

    private static void findPosition(char ch, int[] pos, char[][] matrix) {
        if (ch == 'J') ch = 'I';
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == ch) {
                    pos[0] = i;
                    pos[1] = j;
                    return;
                }
    }
}
