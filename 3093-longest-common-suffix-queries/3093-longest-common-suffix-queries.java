class Solution {
    int[][] children;
    int[] best;
    int[] wordLen;
    int nodeCount;

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int totalLen = 0;
        for (String w : wordsContainer) totalLen += w.length();
        
        nodeCount = 1;
        children = new int[totalLen + 1][26];
        best = new int[totalLen + 1];
        Arrays.fill(best, -1);

        wordLen = new int[wordsContainer.length];
        for (int i = 0; i < wordsContainer.length; i++)
            wordLen[i] = wordsContainer[i].length();

        for (int i = 0; i < wordsContainer.length; i++)
            insert(wordsContainer[i], i);

        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++)
            ans[i] = query(wordsQuery[i]);

        return ans;
    }

    private void insert(String word, int idx) {
        int node = 0;
        updateBest(node, idx);
        for (int i = word.length() - 1; i >= 0; i--) {
            int c = word.charAt(i) - 'a';
            if (children[node][c] == 0) children[node][c] = nodeCount++;
            node = children[node][c];
            updateBest(node, idx);
        }
    }

    private int query(String word) {
        int node = 0;
        for (int i = word.length() - 1; i >= 0; i--) {
            int c = word.charAt(i) - 'a';
            if (children[node][c] == 0) break;
            node = children[node][c];
        }
        return best[node];
    }

    private void updateBest(int node, int idx) {
        if (best[node] == -1 || wordLen[idx] < wordLen[best[node]])
            best[node] = idx;
    }
}