class Solution {
    public int lastStoneWeightII(int[] stones) {
        int total = 0;
        for (int s : stones) total += s;

        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int s : stones) {
            for (int j = target; j >= s; j--) {
                dp[j] = dp[j] || dp[j - s];
            }
        }

        for (int j = target; j >= 0; j--) {
            if (dp[j]) return total - 2 * j;
        }

        return total;
    }
}