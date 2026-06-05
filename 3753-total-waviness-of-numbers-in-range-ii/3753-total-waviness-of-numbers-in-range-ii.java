class Solution {
    long[][][][] cntMemo;
    long[][][][] wavMemo;
    int[] digits;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n <= 0) return 0;
        String s = Long.toString(n);
        digits = new int[s.length()];
        for (int i = 0; i < s.length(); i++) digits[i] = s.charAt(i) - '0';
        int L = s.length();
        cntMemo = new long[L][12][12][2];
        wavMemo = new long[L][12][12][2];
        for (long[][][] a : cntMemo) for (long[][] b : a) for (long[] c : b) Arrays.fill(c, -1);
        for (long[][][] a : wavMemo) for (long[][] b : a) for (long[] c : b) Arrays.fill(c, -1);
        return dp(0, 11, 11, true, 0)[1];
    }

    // returns [count, waviness_sum]
    private long[] dp(int pos, int pp, int prev, boolean tight, int placed) {
        if (pos == digits.length) return new long[]{1, 0};
        int ti = tight ? 1 : 0;
        if (cntMemo[pos][pp][prev][ti] != -1)
            return new long[]{cntMemo[pos][pp][prev][ti], wavMemo[pos][pp][prev][ti]};

        int limit = tight ? digits[pos] : 9;
        long totalCnt = 0, totalWav = 0;

        for (int d = 0; d <= limit; d++) {
            boolean nt = tight && d == limit;
            if (placed == 0 && d == 0) {
                long[] res = dp(pos + 1, 11, 11, nt, 0);
                totalCnt += res[0];
                totalWav += res[1];
            } else {
                int wave = 0;
                if (placed >= 2) {
                    if (prev > pp && prev > d) wave = 1;
                    else if (prev < pp && prev < d) wave = 1;
                }
                int newPp = placed == 0 ? 11 : prev;
                int newPrev = d;
                int newPlaced = Math.min(placed + 1, 2);
                long[] res = dp(pos + 1, newPp, newPrev, nt, newPlaced);
                totalCnt += res[0];
                totalWav += res[1] + wave * res[0];
            }
        }

        cntMemo[pos][pp][prev][ti] = totalCnt;
        wavMemo[pos][pp][prev][ti] = totalWav;
        return new long[]{totalCnt, totalWav};
    }
}