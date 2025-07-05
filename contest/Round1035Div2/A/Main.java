import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for (int i = 0; i < testCases; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            System.out.println(findMinCost(a, b, x, y));
        }
    }

    // x -> cost of +
    // y -> cost of xor
    private static long findMinCost(int a, int b, long x, long y) {
        if (a == b) {
            return 0;
        }

        if (a > b) {
            if (a%2 == 0 || a > b + 1) {
                return -1l;
            }

            if (a == b + 1) {
                return y;
            }
        }

        if (x <= y) {
            return (b - a) * x;
        }

        // a -> b has i steps
        // going from odd to higher even is possible only using +
        // going from even to higher odd is possible using both + and xor. Since xor is cheaper, prefe that.
        // number of steps => b - a
        // steps going from odd to higher even => a%2 == 0 ? ceil((b - a - 1)/2) : ceil((b - a)/2)
        // a = 1, b = 6. steps => 5. 1 -> 2, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 6
        // a = 2, b = 6. steps => 3. 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 6
        int totalSteps = b - a;
        int oddtoEvenSteps;
        if (a%2 == 0) {
            oddtoEvenSteps = (int)Math.ceil((double)(b - a - 1)/2.0);
        } else {
            oddtoEvenSteps = (int)Math.ceil((double)(b - a)/2.0);
        }

        return oddtoEvenSteps * x + (totalSteps - oddtoEvenSteps) * y;
    }
}
