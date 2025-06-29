import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for (int k = 0; k < testCases; k++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[][] matrix = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }

            System.out.println(minMax(matrix));
        }
    }

    /**
     * TC1:
     * 1 3 8
     * 0 2 1
     * 8 4 1
     * 
     * TC2:
     * 1 3 3 2
     * 2 3 2 2
     * 1 2 2 1
     * 3 3 2 3
     */
    public static int minMax(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        Map<Integer, List<int[]>> valuePosMap = new HashMap<>();
        ValueCount[] rowCount = new ValueCount[n];
        for (int i = 0; i < n; i++) {
            rowCount[i] = new ValueCount();
        }

        ValueCount[] colCount = new ValueCount[m];
        for (int j = 0; j < m; j++) {
            colCount[j] = new ValueCount();
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                valuePosMap.computeIfAbsent(matrix[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
                rowCount[i].add(matrix[i][j]);
                colCount[j].add(matrix[i][j]);
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }

        //System.out.println(Arrays.toString(rowCount));
        //System.out.println(Arrays.toString(colCount));

        final int finalMax = max;
        final List<int[]> maxPos = valuePosMap.get(max);
        //maxPos.forEach(p -> System.out.println("(" + p[0] + "," + p[1] + ")"));

        final int maxRowIdx = getMaxIdx(rowCount, finalMax);
        final int maxColIdx = getMaxIdx(colCount, finalMax);
        if (rowCount[maxRowIdx].getCount(finalMax) > colCount[maxColIdx].getCount(finalMax)) {
            rowCount[maxRowIdx].remove(finalMax);
            maxPos.forEach(pos -> {
                if (pos[0] == maxRowIdx) {
                    colCount[pos[1]].decrease(finalMax);
                }
            });
            //System.out.println(Arrays.toString(rowCount));
            //System.out.println(Arrays.toString(colCount));

            final int newMaxColIdx = getMaxIdx(colCount, finalMax);
            colCount[newMaxColIdx].remove(finalMax);
            maxPos.forEach(pos -> {
                if (pos[1] == newMaxColIdx) {
                    rowCount[pos[0]].decrease(finalMax);
                }
            });
            //System.out.println(Arrays.toString(rowCount));
            //System.out.println(Arrays.toString(colCount));
        } else {
            colCount[maxColIdx].remove(finalMax);
            maxPos.forEach(pos -> {
                if (pos[1] == maxColIdx) {
                    rowCount[pos[0]].decrease(finalMax);
                }
            });
            //System.out.println(Arrays.toString(rowCount));
            //System.out.println(Arrays.toString(colCount));

            final int newMaxRowIdx = getMaxIdx(rowCount, finalMax);
            rowCount[newMaxRowIdx].remove(finalMax);
            maxPos.forEach(pos -> {
                if (pos[0] == newMaxRowIdx) {
                    colCount[pos[1]].decrease(finalMax);
                }
            });
            //System.out.println(Arrays.toString(rowCount));
            //System.out.println(Arrays.toString(colCount));
        }

        boolean anyMaxLeft = false;
        for (int i = 0; i < n; i++) {
            anyMaxLeft |= rowCount[i].getCount(finalMax) > 0;
        }

        for (int j = 0; j < m; j++) {
            anyMaxLeft |= colCount[j].getCount(finalMax) > 0;
        }

        return anyMaxLeft ? finalMax : finalMax - 1;
    }

    private static int getMaxIdx(ValueCount[] valCounts, int maxVal) {
        int max = valCounts[0].getCount(maxVal);
        int maxIdx = 0;
        for (int i = 1; i < valCounts.length; i++) {
            if (valCounts[i].getCount(maxVal) > max) {
                max = valCounts[i].getCount(maxVal);
                maxIdx = i;
            }
        }

        return maxIdx;
    }

    private static class ValueCount {
        Map<Integer, Integer> map = new HashMap<>();

        void add(int val) {
            map.compute(val, (k, v) -> v == null ? 1 : v + 1);
        }

        void remove(int val) {
            map.put(val, 0);
        }

        void decrease(int val) {
            map.computeIfPresent(val, (k, v) -> v == 0 ? 0 : v - 1);
        }

        int getCount(int v) {
            return map.getOrDefault(v, 0);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            map.forEach((k, v) ->
                sb.append(String.format("(%s -> %s), ", k, v)));
            sb.append("}");
            return sb.toString();
        }
    }
}
