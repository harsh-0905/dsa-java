class Solution {
    public long maxTotalValue(int[] nums, int k) {
        long max = nums[0], min = nums[0];
        for (int n : nums) {
            if (n > max) max = n;
            if (n < min) min = n;
        }
        return (max - min) * k;
    }
}