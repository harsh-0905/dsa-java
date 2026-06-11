class Solution {
    static final int MOD = 1_000_000_007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) { adj.get(e[0]).add(e[1]); adj.get(e[1]).add(e[0]); }

        int maxDepth = bfs(adj, n);
        return power(2, maxDepth - 1);
    }

    private int bfs(List<List<Integer>> adj, int n) {
        int[] depth = new int[n + 1];
        Arrays.fill(depth, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(1); depth[1] = 0;
        int maxD = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int nb : adj.get(node)) {
                if (depth[nb] == -1) {
                    depth[nb] = depth[node] + 1;
                    maxD = Math.max(maxD, depth[nb]);
                    q.add(nb);
                }
            }
        }
        return maxD;
    }

    private int power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = res * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return (int) res;
    }
}