class Solution {
    static final int MOD = 1_000_000_007;
    static final int LOG = 17;
    int[] depth;
    int[][] up;
    List<List<Integer>> adj;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) { adj.get(e[0]).add(e[1]); adj.get(e[1]).add(e[0]); }

        depth = new int[n + 1];
        up = new int[n + 1][LOG];
        dfs(1, 0, 0);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            int lca = lca(u, v);
            int d = depth[u] + depth[v] - 2 * depth[lca];
            ans[i] = d == 0 ? 0 : power(2, d - 1);
        }
        return ans;
    }

    private void dfs(int node, int parent, int d) {
        depth[node] = d;
        up[node][0] = parent;
        for (int k = 1; k < LOG; k++)
            up[node][k] = up[up[node][k-1]][k-1];
        for (int nb : adj.get(node))
            if (nb != parent) dfs(nb, node, d + 1);
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) { int t = u; u = v; v = t; }
        int diff = depth[u] - depth[v];
        for (int k = 0; k < LOG; k++)
            if (((diff >> k) & 1) == 1) u = up[u][k];
        if (u == v) return u;
        for (int k = LOG - 1; k >= 0; k--)
            if (up[u][k] != up[v][k]) { u = up[u][k]; v = up[v][k]; }
        return up[u][0];
    }

    private int power(long base, long exp) {
        long res = 1; base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = res * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return (int) res;
    }
}