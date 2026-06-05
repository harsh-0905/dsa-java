class Solution {
    public double separateSquares(int[][] squares) {
        double lo = Double.MAX_VALUE, hi = Double.MIN_VALUE;
        for (int[] s : squares) {
            lo = Math.min(lo, s[1]);
            hi = Math.max(hi, s[1] + s[2]);
        }
        
        while (hi - lo > 1e-6) {
            double mid = (lo + hi) / 2;
            if (areaBelow(squares, mid) < areaAbove(squares, mid)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
    
    private double areaBelow(int[][] squares, double y) {
        double area = 0;
        for (int[] s : squares) {
            double bottom = s[1], top = s[1] + s[2], side = s[2];
            if (y <= bottom) continue;
            double h = Math.min(y, top) - bottom;
            area += h * side;
        }
        return area;
    }
    
    private double areaAbove(int[][] squares, double y) {
        double area = 0;
        for (int[] s : squares) {
            double bottom = s[1], top = s[1] + s[2], side = s[2];
            if (y >= top) continue;
            double h = top - Math.max(y, bottom);
            area += h * side;
        }
        return area;
    }
}