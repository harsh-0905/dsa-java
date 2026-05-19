class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums1) set.add(n);
        
        Set<Integer> result = new HashSet<>();
        for (int n : nums2) {
            if (set.contains(n)) result.add(n);
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}