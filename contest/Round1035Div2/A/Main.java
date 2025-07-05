import java.util.*;

/**
You are given two non-negative integers a, b. You can apply two types of operations on 𝑎 any number of times and in any order:

a<-a+1. The cost of this operation is x;
a<-axor1. The cost of this operation is y.

Now you are asked to make a=b. If it's possible, output the minimum cost; otherwise, report it.

Input
Each test contains multiple test cases. The first line contains the number of test cases t
 (1<=t<=104
). The description of the test cases follows.

The only line of each test case contains four integers a,b,x,y
 (1<=a,b<=100,1<=x,y<=10^7
) — the two integers given to you and the respective costs of two types of operations.

Output
For each test case, output an integer — the minimum cost to make a=b
, or −1
 if it is impossible.

 E.g.
 Input
 7
 1 4 1 2
 1 5 2 1
 3 2 2 1
 1 3 2 1
 2 1 1 2
 3 1 1 2
 1 100 10000000 10000000

 Output
 3
 6
 1
 3
 -1
 -1
 990000000

 -------------
 2: 0010
 1: 0001

 2 xor 1 = 0010 xor 0001 = 0011 (3)
 3 xor 1 = 0011 xor 0001 = 0010 (2)
 */
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
