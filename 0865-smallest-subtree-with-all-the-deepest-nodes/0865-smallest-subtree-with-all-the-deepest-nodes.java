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
    TreeNode result;
    int maxDepth;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    private int dfs(TreeNode node, int depth) {
        if (node == null) return depth - 1;
        maxDepth = Math.max(maxDepth, depth);
        int left = dfs(node.left, depth + 1);
        int right = dfs(node.right, depth + 1);
        if (left == right && left == maxDepth) result = node;
        return Math.max(left, right);
    }
}