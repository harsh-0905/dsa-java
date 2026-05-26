class Solution {
    public int numberOfSpecialChars(String word) {
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (char c : word.toCharArray()) set.add(c);
        for (char c = 'a'; c <= 'z'; c++) {
            if (set.contains(c) && set.contains(Character.toUpperCase(c))) count++;
        }
        return count;
    }
}