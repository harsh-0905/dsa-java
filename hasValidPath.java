class Solution {
    private static final int[][] connections = {{},{0,1},{2,3},{0,3},{1,3},{0,2},{1,2}};
    private static final int[] opposite = {1, 0, 3, 2};
    private static final int[] dr = {0, 0, -1, 1};
    private static final int[] dc = {-1, 1, 0, 0};

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];

            if (r == m - 1 && c == n - 1) return true;

            for (int dir : connections[grid[r][c]]) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (visited[nr][nc]) continue;

                int needed = opposite[dir];
                boolean connects = false;
                for (int d : connections[grid[nr][nc]]) {
                    if (d == needed) { connects = true; break; }
                }

                if (connects) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return false;
    }
}