class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < landStartTime.length; i++) {
            for (int j = 0; j < waterStartTime.length; j++) {
                int landFirst = Math.max(waterStartTime[j], landStartTime[i] + landDuration[i]) + waterDuration[j];
                int waterFirst = Math.max(landStartTime[i], waterStartTime[j] + waterDuration[j]) + landDuration[i];

                ans = Math.min(ans, Math.min(landFirst, waterFirst));
            }
        }

        return ans;
    }
}