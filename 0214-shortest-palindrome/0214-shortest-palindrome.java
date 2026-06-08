    class Solution {
    public String shortestPalindrome(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + rev;
        int[] kmp = new int[combined.length()];

        for (int i = 1; i < combined.length(); i++) {
            int j = kmp[i - 1];
            while (j > 0 && combined.charAt(i) != combined.charAt(j)) j = kmp[j - 1];
            if (combined.charAt(i) == combined.charAt(j)) j++;
            kmp[i] = j;
        }

        int longest = kmp[combined.length() - 1];
        return rev.substring(0, s.length() - longest) + s;
    }
}