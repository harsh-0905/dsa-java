class Solution {
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (arr[i] == 0) return true;
            if (visited[i]) continue;
            visited[i] = true;
            
            if (i + arr[i] < arr.length) queue.offer(i + arr[i]);
            if (i - arr[i] >= 0) queue.offer(i - arr[i]);
        }
        
        return false;
    }
}