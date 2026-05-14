class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length - 1;
        int[] freq = new int[201];
        for (int num : nums) freq[num]++;
        for (int i = 1; i < n; i++) {
            if (freq[i] != 1) return false;
        }
        return freq[n] == 2;
    }
}