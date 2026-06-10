class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        long sumAll = sumSubMax(nums) - sumSubMin(nums);
        if (k == n * (n + 1) / 2) return sumAll;

        int lo = 0, hi = 0;
        for (int x : nums) { hi = Math.max(hi, x); lo = Math.min(lo, x); }
        lo = 0; hi = 0;
        int mn = nums[0], mx = nums[0];
        for (int x : nums) { mx = Math.max(mx, x); mn = Math.min(mn, x); }
        hi = mx - mn;

        int V = 0;
        lo = 0;
        while (lo < hi) {
            int mid = (lo + hi + 1) / 2;
            if (countGeq(nums, mid) >= k) lo = mid;
            else hi = mid - 1;
        }
        V = lo;
        long cntAbove = countGeq(nums, V + 1);
        long sumAbove = sumGeq(nums, V + 1, sumAll);
        return sumAbove + (long) V * (k - cntAbove);
    }

    private long countGeq(int[] nums, int V) {
        if (V == 0) return (long) nums.length * (nums.length + 1) / 2;
        int n = nums.length;
        Deque<Integer> maxDq = new ArrayDeque<>(), minDq = new ArrayDeque<>();
        int lo = 0; long cntLess = 0;
        for (int r = 0; r < n; r++) {
            while (!maxDq.isEmpty() && nums[maxDq.peekLast()] <= nums[r]) maxDq.pollLast();
            while (!minDq.isEmpty() && nums[minDq.peekLast()] >= nums[r]) minDq.pollLast();
            maxDq.addLast(r); minDq.addLast(r);
            while (!maxDq.isEmpty() && !minDq.isEmpty() && nums[maxDq.peekFirst()] - nums[minDq.peekFirst()] >= V) {
                lo++;
                if (maxDq.peekFirst() < lo) maxDq.pollFirst();
                if (minDq.peekFirst() < lo) minDq.pollFirst();
            }
            cntLess += r - lo + 1;
        }
        return (long) n * (n + 1) / 2 - cntLess;
    }

    private long sumGeq(int[] nums, int V, long sumAll) {
        if (V == 0) return sumAll;
        int n = nums.length;
        Deque<long[]> maxDq = new ArrayDeque<>(), minDq = new ArrayDeque<>();
        long sumMax = 0, sumMin = 0, sumLess = 0;
        int lo = 0;
        for (int r = 0; r < n; r++) {
            long cntMx = 1;
            while (!maxDq.isEmpty() && maxDq.peekLast()[0] <= nums[r]) {
                long[] top = maxDq.pollLast(); sumMax -= top[0] * top[1]; cntMx += top[1];
            }
            maxDq.addLast(new long[]{nums[r], cntMx}); sumMax += (long) nums[r] * cntMx;

            long cntMn = 1;
            while (!minDq.isEmpty() && minDq.peekLast()[0] >= nums[r]) {
                long[] top = minDq.pollLast(); sumMin -= top[0] * top[1]; cntMn += top[1];
            }
            minDq.addLast(new long[]{nums[r], cntMn}); sumMin += (long) nums[r] * cntMn;

            while (!maxDq.isEmpty() && !minDq.isEmpty() && maxDq.peekFirst()[0] - minDq.peekFirst()[0] >= V) {
                long[] mxF = maxDq.peekFirst(); sumMax -= mxF[0];
                if (mxF[1] == 1) maxDq.pollFirst(); else mxF[1]--;
                long[] mnF = minDq.peekFirst(); sumMin -= mnF[0];
                if (mnF[1] == 1) minDq.pollFirst(); else mnF[1]--;
                lo++;
            }
            sumLess += sumMax - sumMin;
        }
        return sumAll - sumLess;
    }

    private long sumSubMax(int[] nums) {
        int n = nums.length;
        long[] left = new long[n], right = new long[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) stack.pop();
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) stack.pop();
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }
        long res = 0;
        for (int i = 0; i < n; i++) res += (long) nums[i] * left[i] * right[i];
        return res;
    }

    private long sumSubMin(int[] nums) {
        int n = nums.length;
        long[] left = new long[n], right = new long[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) stack.pop();
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) stack.pop();
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }
        long res = 0;
        for (int i = 0; i < n; i++) res += (long) nums[i] * left[i] * right[i];
        return res;
    }
}