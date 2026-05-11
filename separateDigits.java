class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int num : nums) {
            int[] digits = new int[6];
            int count = 0;
            while (num > 0) {
                digits[count++] = num % 10;
                num /= 10;
            }
            for (int i = count - 1; i >= 0; i--) {
                result.add(digits[i]);
            }
        }
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}