class Solution {
    int[] memo;

    public int maxJumps(int[] arr, int d) {
        memo = new int[arr.length];
        int ans = 0;
        for (int i = 0; i < arr.length; i++)
            ans = Math.max(ans, dp(arr, d, i));
        return ans;
    }

    private int dp(int[] arr, int d, int i) {
        if (memo[i] != 0) return memo[i];

        int best = 1;

        for (int x = 1; x <= d; x++) {
            int j = i + x;
            if (j >= arr.length || arr[j] >= arr[i]) break;
            best = Math.max(best, 1 + dp(arr, d, j));
        }

        for (int x = 1; x <= d; x++) {
            int j = i - x;
            if (j < 0 || arr[j] >= arr[i]) break;
            best = Math.max(best, 1 + dp(arr, d, j));
        }

        memo[i] = best;
        return best;
    }
}