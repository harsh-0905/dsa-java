class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        int[] nums = new int[m * n];
        int idx = 0;

        for (int[] row : grid)
            for (int val : row)
                nums[idx++] = val;

        int remainder = nums[0] % x;
        for (int num : nums)
            if (num % x != remainder) return -1;

        int medianIdx = nums.length / 2;
        int median = quickSelect(nums, 0, nums.length - 1, medianIdx);

        int ops = 0;
        for (int num : nums)
            ops += Math.abs(num - median) / x;

        return ops;
    }

    private int quickSelect(int[] nums, int lo, int hi, int k) {
        if (lo == hi) return nums[lo];

        int pivot = partition(nums, lo, hi);

        if (pivot == k)       return nums[pivot];
        else if (pivot < k)   return quickSelect(nums, pivot + 1, hi, k);
        else                  return quickSelect(nums, lo, pivot - 1, k);
    }

    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[hi], i = lo;

        for (int j = lo; j < hi; j++)
            if (nums[j] <= pivot)
                swap(nums, i++, j);

        swap(nums, i, hi);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}