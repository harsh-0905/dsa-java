class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int layers = Math.min(m, n) / 2;
        
        for (int layer = 0; layer < layers; layer++) {
            List<Integer> elements = new ArrayList<>();
            
            int top = layer, bottom = m - 1 - layer;
            int left = layer, right = n - 1 - layer;
            
            for (int col = left; col <= right; col++) elements.add(grid[top][col]);
            for (int row = top + 1; row <= bottom; row++) elements.add(grid[row][right]);
            for (int col = right - 1; col >= left; col--) elements.add(grid[bottom][col]);
            for (int row = bottom - 1; row > top; row--) elements.add(grid[row][left]);
            
            int size = elements.size();
            int shift = k % size;
            
            List<Integer> rotated = new ArrayList<>();
            for (int i = shift; i < size; i++) rotated.add(elements.get(i));
            for (int i = 0; i < shift; i++) rotated.add(elements.get(i));
            
            int idx = 0;
            for (int col = left; col <= right; col++) grid[top][col] = rotated.get(idx++);
            for (int row = top + 1; row <= bottom; row++) grid[row][right] = rotated.get(idx++);
            for (int col = right - 1; col >= left; col--) grid[bottom][col] = rotated.get(idx++);
            for (int row = bottom - 1; row > top; row--) grid[row][left] = rotated.get(idx++);
        }
        
        return grid;
    }
}