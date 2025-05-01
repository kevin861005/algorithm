package com.algorithm.leetcode;

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
 * <br/>
 * <br/>
 * <a href="https://leetcode.com/problems/validate-binary-search-tree/">validate-binary-search-tree</a>
 */
public class leetcode0098 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode () {}
        TreeNode (int val) { this.val = val; }
        TreeNode (int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isValidBST (TreeNode root) {
        if ( root == null ) return true;

        return isValidBST_help(root);
    }

    private boolean isValidBST_help (TreeNode node) {
        if ( node == null ) return true;

        boolean result = true;

        // find the max node on the left side
        if ( node.left != null ) {
            TreeNode left_max = findMax(node.left);
            if ( left_max.val >= node.val ) {
                result = false;
            }
        }

        // find the min node on the right side
        if ( node.right != null ) {
            TreeNode right_min = findMin(node.right);
            if ( right_min.val <= node.val ) {
                result = false;
            }
        }

        boolean result_left = isValidBST_help(node.left);
        if ( ! result_left ) result = false;

        boolean result_right = isValidBST_help(node.right);
        if ( ! result_right ) result = false;

        return result;
    }

    private TreeNode findMax (TreeNode root) {
        if ( root == null ) return null;

        TreeNode max = root;

        while ( true ) {
            if ( max.right == null ) break;

            max = max.right;
        }

        return max;
    }

    private TreeNode findMin (TreeNode root) {
        if ( root == null ) return null;

        TreeNode min = root;

        while ( true ) {
            if ( min.left == null ) break;

            min = min.left;
        }

        return min;
    }
}
