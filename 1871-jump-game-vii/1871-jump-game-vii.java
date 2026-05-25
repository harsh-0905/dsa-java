class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        dp[0] = true;
        int prefixSum = 0;

        for (int i = 1; i < n; i++) {
            if (i >= minJump) prefixSum += dp[i - minJump] ? 1 : 0;
            if (i > maxJump) prefixSum -= dp[i - maxJump - 1] ? 1 : 0;
            if (s.charAt(i) == '0' && prefixSum > 0) dp[i] = true;
        }

        return dp[n - 1];
    }
}