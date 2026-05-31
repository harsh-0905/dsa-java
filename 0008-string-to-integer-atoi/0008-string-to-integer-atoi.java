class Solution {
    public int myAtoi(String s) {
        s = s.strip();
        if (s.isEmpty()) return 0;

        int sign = 1, i = 0;
        if (s.charAt(0) == '-') { sign = -1; i++; }
        else if (s.charAt(0) == '+') i++;

        int res = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            int d = s.charAt(i++) - '0';
            if (res > (Integer.MAX_VALUE - d) / 10) 
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + d;
        }

        return sign * res;
    }
}