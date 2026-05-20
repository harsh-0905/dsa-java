class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length / 2;
        int total = 0;
        for (int x : nums) total += x;

        List<List<Integer>> leftSums = new ArrayList<>();
        List<List<Integer>> rightSums = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            leftSums.add(new ArrayList<>());
            rightSums.add(new ArrayList<>());
        }

        for (int mask = 0; mask < (1 << n); mask++) {
            int cnt = 0, sum = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    cnt++;
                    sum += nums[i];
                }
            }
            leftSums.get(cnt).add(sum);
        }

        for (int mask = 0; mask < (1 << n); mask++) {
            int cnt = 0, sum = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    cnt++;
                    sum += nums[n + i];
                }
            }
            rightSums.get(cnt).add(sum);
        }

        for (int k = 0; k <= n; k++) Collections.sort(rightSums.get(k));

        int ans = Integer.MAX_VALUE;
        int half = total / 2;

        for (int k = 0; k <= n; k++) {
            List<Integer> right = rightSums.get(n - k);
            for (int leftSum : leftSums.get(k)) {
                int target = half - leftSum;
                int lo = 0, hi = right.size() - 1;
                while (lo <= hi) {
                    int mid = (lo + hi) / 2;
                    int diff = total - 2 * (leftSum + right.get(mid));
                    ans = Math.min(ans, Math.abs(diff));
                    if (right.get(mid) < target) lo = mid + 1;
                    else hi = mid - 1;
                }
            }
        }

        return ans;
    }
}