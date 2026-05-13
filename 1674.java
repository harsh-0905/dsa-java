class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] count = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];

            int lo = Math.min(a, b) + 1;
            int hi = Math.max(a, b) + limit;

            count[2] += 2;
            count[lo]--;
            count[hi + 1]++;
            count[a + b]--;
            count[a + b + 1]++;
        }

        int result = Integer.MAX_VALUE;
        int curr = 0;
        for (int s = 2; s <= 2 * limit; s++) {
            curr += count[s];
            result = Math.min(result, curr);
        }
        return result;
    }
}