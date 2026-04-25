import java.util.Arrays;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] pos = new long[n];
        long perimeter = 4L * side;

        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            if (y == 0) {
                pos[i] = x;
            } else if (x == side) {
                pos[i] = side + y;
            } else if (y == side) {
                pos[i] = 2L * side + (side - x);
            } else {
                pos[i] = 3L * side + (side - y);
            }
        }

        Arrays.sort(pos);

        long[] doubled = new long[2 * n];
        for (int i = 0; i < n; i++) {
            doubled[i] = pos[i];
            doubled[i + n] = pos[i] + perimeter;
        }

        long lo = 1, hi = 2L * side, ans = 0;
        while (lo <= hi) {
            long mid = (lo + hi) / 2;
            if (canPlace(doubled, n, k, mid, perimeter)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return (int) ans;
    }

    private boolean canPlace(long[] doubled, int n, int k, long d, long perimeter) {
        for (int start = 0; start < n; start++) {
            int count = 1;
            long cur = doubled[start];
            int idx = start;

            while (count < k) {
                long target = cur + d;
                int lo = idx + 1, hi = 2 * n - 1, next = -1;
                while (lo <= hi) {
                    int m = (lo + hi) / 2;
                    if (doubled[m] >= target) {
                        next = m;
                        hi = m - 1;
                    } else {
                        lo = m + 1;
                    }
                }

                if (next == -1 || doubled[next] >= doubled[start] + perimeter) break;

                cur = doubled[next];
                idx = next;
                count++;
            }

            if (count == k) {
                long lastGap = doubled[start] + perimeter - cur;
                if (lastGap >= d) return true;
            }
        }

        return false;
    }
}