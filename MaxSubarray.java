public class MaxSubarray {
    public static int maxSubarraySum(int[] arr) {
        int maxEnd = arr[0];    // best sum ending at current index
        int maxSoFar = arr[0];  // global best

        for (int i = 1; i < arr.length; i++) {
            maxEnd = Math.max(arr[i], maxEnd + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEnd);
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubarraySum(arr)); // 6  →  subarray: [4,-1,2,1]
    }
}
