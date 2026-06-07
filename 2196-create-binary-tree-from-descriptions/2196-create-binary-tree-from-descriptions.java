/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] d : descriptions) {
            int p = d[0], c = d[1], isLeft = d[2];
            map.putIfAbsent(p, new TreeNode(p));
            map.putIfAbsent(c, new TreeNode(c));
            if (isLeft == 1) map.get(p).left = map.get(c);
            else map.get(p).right = map.get(c);
            children.add(c);
        }

        for (int key : map.keySet())
            if (!children.contains(key)) return map.get(key);

        return null;
    }
}