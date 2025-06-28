import java.util.*;

/**
 * Given a string s, find if there exists non-empty strings a, b, and c
 * such that a + b + c = s, and b is a substring of a + c.
 * 
 * E.g.
 * 1. artrtbb => a = art, b = rtb, c = b
 * 2. fftyftyzrt => a = ffty, b = fty, c = zrt
 * 3. abcba => a = abc, b = b, c = a
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for (int i = 0; i < testCases; i++) {
            int length = scanner.nextInt();
            String s = scanner.next();
            System.out.println(satisfy(s));
        }
    }

    private static String satisfy(String s) {
        boolean[] seen = new boolean[26];
        seen[s.charAt(0) - 'a'] = true;
        seen[s.charAt(s.length() - 1) - 'a'] = true;
        for (int i = 1; i < s.length() - 1; i++) {
            int c = s.charAt(i) - 'a';
            if (seen[c]) {
                return "Yes";
            }

            seen[c] = true;
        }

        return "No";
    }
}
