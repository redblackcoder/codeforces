import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int wordCount = scanner.nextInt();
        for (int i = 0; i < wordCount; i++) {
            String word = scanner.next();
            System.out.println(abbreviate(word));
        }
    }

    private static String abbreviate(String s) {
        if (s.length() <= 10) {
            return s;
        }

        return String.format("%c%d%c", s.charAt(0), (s.length() - 2), s.charAt(s.length() - 1));
    }
}
