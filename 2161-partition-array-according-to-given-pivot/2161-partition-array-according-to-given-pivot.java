class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        List<Integer> less = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();

        for (int n : nums) {
            if (n < pivot) less.add(n);
            else if (n == pivot) equal.add(n);
            else greater.add(n);
        }

        int i = 0;
        for (int n : less) nums[i++] = n;
        for (int n : equal) nums[i++] = n;
        for (int n : greater) nums[i++] = n;
        return nums;
    }
}