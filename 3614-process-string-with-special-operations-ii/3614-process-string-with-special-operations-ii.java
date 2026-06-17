class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] lens = new long[n + 1];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*') lens[i + 1] = Math.max(0, lens[i] - 1);
            else if (c == '#') lens[i + 1] = lens[i] * 2;
            else if (c == '%') lens[i + 1] = lens[i];
            else lens[i + 1] = lens[i] + 1;
        }

        if (k >= lens[n]) return '.';

        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            long prevLen = lens[i];

            if (c == '%') {
                k = prevLen - 1 - k;
            } else if (c == '#') {
                if (k >= prevLen) k -= prevLen;
            } else if (c != '*') {
                if (k == prevLen) return c;
            }
        }

        return '.';
    }
}