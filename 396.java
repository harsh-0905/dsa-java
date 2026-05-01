class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int total = 0;
        int f = 0;

        for (int i = 0; i < n; i++) {
            total += nums[i];
            f += i * nums[i];
        }

        int maxF = f;

        for (int k = 1; k < n; k++) {
            f = f + total - n * nums[n - k];
            maxF = Math.max(maxF, f);
        }

        return maxF;
    }
}