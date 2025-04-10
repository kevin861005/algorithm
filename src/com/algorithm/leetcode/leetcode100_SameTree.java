package com.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/same-tree/description/">...</a>
 */
public class leetcode100_SameTree {
    public boolean isSameTree (TreeNode p, TreeNode q) {
        if ( p == null && q == null ) return true;
        if ( p == null || q == null ) return false;

        boolean result = true;

        if ( p.val != q.val ) {
            result = false;
        }
        else {
            boolean left = isSameTree(p.left, q.left);

            if ( ! left ) {
                result = false;
            }
            else {
                boolean right = isSameTree(p.right, q.right);

                if ( ! right ) {
                    result = false;
                }
            }
        }

        return result;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
}
