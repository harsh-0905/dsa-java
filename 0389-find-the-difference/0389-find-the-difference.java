class Solution {
    public char findTheDifference(String s, String t) {
        
        int sumS = 0;
        for(char ch: s.toCharArray()){
            sumS+=ch;
        }
        int sumT = 0;
        for(char ch : t.toCharArray()){
            sumT+=ch;
        }
        return (char)(sumT-sumS);
    }
}