class RailFenceCipherHelper {
    String encode(String msg, int depth) {
        int c = msg.length() / depth;
        char[][] mat = new char[depth][c];
        int k = 0;

        StringBuilder enc = new StringBuilder();

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < depth; j++) {
                mat[j][i] = (k != msg.length()) ? msg.charAt(k++) : 'X';
            }
        }

        for (char[] row : mat) {
            enc.append(row);
        }

        return enc.toString();
    }

    String decode(String encmsg, int depth) {
        int c = encmsg.length() / depth;
        char[][] mat = new char[depth][c];
        int k = 0;

        StringBuilder dec = new StringBuilder();

        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < c; j++) {
                mat[i][j] = encmsg.charAt(k++);
            }
        }

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < depth; j++) {
                dec.append(mat[j][i]);
            }
        }

        return dec.toString();
    }
}

public class Railfence {
    public static void main(String[] args) {
        RailFenceCipherHelper rf = new RailFenceCipherHelper();
        String msg = System.console().readLine();
        int depth = 2;

        String enc = rf.encode(msg, depth);
        String dec = rf.decode(enc, depth);

        System.out.println("Plain text: " + msg);
        System.out.println("Encrypted text: " + enc);
        System.out.println("Decoded text: " + dec);
    }
}
