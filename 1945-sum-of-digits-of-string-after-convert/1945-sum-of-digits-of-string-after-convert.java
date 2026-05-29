class Solution {
    public int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) sb.append(c - 'a' + 1);

        int sum = 0;
        for (char c : sb.toString().toCharArray()) sum += c - '0';

        for (int i = 1; i < k; i++) {
            int next = 0;
            while (sum > 0) { next += sum % 10; sum /= 10; }
            sum = next;
        }

        return sum;
    }
}