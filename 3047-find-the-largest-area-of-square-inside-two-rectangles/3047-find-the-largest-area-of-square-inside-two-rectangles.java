class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        long best = 0;
        int n = bottomLeft.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x = Math.min(topRight[i][0], topRight[j][0]) - Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                int y = Math.min(topRight[i][1], topRight[j][1]) - Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                if (x > 0 && y > 0) {
                    long side = Math.min(x, y);
                    best = Math.max(best, side * side);
                }
            }
        }
        return best;
    }
}