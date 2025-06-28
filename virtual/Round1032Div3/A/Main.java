import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for (int i = 0; i < testCases; i++) {
            int n = scanner.nextInt();
            int s = scanner.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = scanner.nextInt();
            }

            System.out.println(minSteps(nums, s));
        }
    }

    private static int minSteps(int[] nums, int s) {
        int n = nums.length;
        int lowDist = s - nums[0];
        int highDist = s - nums[n - 1];
        if (lowDist * highDist > 0) {
            // both are on the same side
            return Math.max(Math.abs(lowDist), Math.abs(highDist));
        } else {
            // they are on opposite side
            return Math.min(Math.abs(lowDist), Math.abs(highDist)) + (nums[n - 1] - nums[0]);
        }
    }
}
