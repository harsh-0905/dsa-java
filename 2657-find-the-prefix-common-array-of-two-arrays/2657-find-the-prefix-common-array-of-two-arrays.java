class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] C = new int[n];
        Set<Integer> seen = new HashSet<>();
        int common = 0;

        for (int i = 0; i < n; i++) {
            if (seen.contains(A[i])) common++;
            else seen.add(A[i]);

            if (seen.contains(B[i])) common++;
            else seen.add(B[i]);

            C[i] = common;
        }

        return C;
    }
}