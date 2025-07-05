import java.util.*;

/**
5
2
1 1 5 1
3 3
3
1 1 3 3
2 3 4
2
100 100 100 100
4 5
1
5 1 1 4
5
2
10000000 10000000 10000000 10000000
10000 10000

Yes
Yes
No
Yes
Yes
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for (int i = 0; i < testCases; i++) {
            int n = scanner.nextInt();
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            int[] as = new int[n];
            for (int j = 0; j < n; j++) {
                as[j] = scanner.nextInt();
            }

            System.out.println(pathPossible(new Point(x1, y1), new Point(x2, y2), as) ? "Yes" : "No");
        }
    }

    private static boolean pathPossible(Point p, Point q, int[] as) {
        int longest = Arrays.stream(as).max().getAsInt();
        int max = Arrays.stream(as).sum();
        int min = Math.max(0, 2 * longest - max);

        double distance = p.distance(q);
        return min <= distance && distance <= max;
    }

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double distance(Point o) {
            return Math.sqrt(Math.pow(x - o.x, 2) + Math.pow(y - o.y, 2));
        }
    }
}
