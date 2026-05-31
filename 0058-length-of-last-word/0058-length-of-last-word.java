class Solution {
    public int lengthOfLastWord(String s) {
        s = s.stripTrailing();
        return s.length() - s.lastIndexOf(' ') - 1;
    }
}