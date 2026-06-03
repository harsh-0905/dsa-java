class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long sum = 0;
        int negCount = 0, minAbs = Integer.MAX_VALUE;

        for (int[] row : matrix) {
            for (int val : row) {
                sum += Math.abs(val);
                if (val < 0) negCount++;
                minAbs = Math.min(minAbs, Math.abs(val));
            }
        }

        if (negCount % 2 == 0) return sum;
        return sum - 2 * minAbs;
    }
}