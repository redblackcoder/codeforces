import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for (int i = 0; i < testCases; i++) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = scanner.nextInt();
            }

            for (int j = 0; j < n; j++) {
                b[j] = scanner.nextInt();
            }

            List<Operation> operations = new ArrayList<>();
            arrange(a, b, 0, operations);
            System.out.println(operations.size());
            operations.forEach(o -> System.out.println(o));
            // System.out.println(Arrays.toString(a));
            // System.out.println(Arrays.toString(b));
        }
    }

    private static void arrange(int[] a, int[] b, int s, List<Operation> operations) {
        int n = a.length;
        for (int i = n - 1; i > s; i--) {
            if (a[i] < a[i - 1]) {
                operations.add(swap(a, i - 1, 1));
            }

            if (b[i] < b[i - 1]) {
                operations.add(swap(b, i - 1, 2));
            }
        }

        if (a[s] > b[s]) {
            operations.add(swap(a, b, s));

            for (int i = n - 1; i > s; i--) {
                if (b[i] < b[i - 1]) {
                    operations.add(swap(b, i - 1, 2));
                }
            }
        }

        if (s < n - 1) {
            arrange(a, b, s + 1, operations);
        }
    }

    private static Operation swap(int[] arr, int i, int code) {
        int temp = arr[i];
        arr[i] = arr[i + 1];
        arr[i + 1] = temp;
        return new Operation(code, i + 1);
    }

    private static Operation swap(int[] a, int[] b, int i) {
        int temp = a[i];
        a[i] = b[i];
        b[i] = temp;
        return new Operation(3, i + 1);
    }

    private static class Operation {
        // 1, 2, or 3
        int code;

        // i IN [1, n]
        int index;

        Operation(int c, int i) {
            code = c;
            index = i;
        }

        @Override
        public String toString() {
            return code + " " + index;
        }
    }
}
