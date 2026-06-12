class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Long> list = new ArrayList<>();
        for (int x : nums) list.add((long) x);
        int ops = 0;

        while (!isNonDecreasing(list)) {
            int idx = 0;
            long minSum = Long.MAX_VALUE;
            for (int i = 0; i < list.size() - 1; i++) {
                long s = list.get(i) + list.get(i + 1);
                if (s < minSum) { minSum = s; idx = i; }
            }
            list.set(idx, minSum);
            list.remove(idx + 1);
            ops++;
        }
        return ops;
    }

    private boolean isNonDecreasing(List<Long> list) {
        for (int i = 1; i < list.size(); i++)
            if (list.get(i) < list.get(i - 1)) return false;
        return true;
    }
}