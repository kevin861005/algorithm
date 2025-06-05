package com.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

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
 * <a href="https://leetcode.com/problems/find-leaves-of-binary-tree/description/">find-leaves-of-binary-tree</a>
 */
public class leetcode0366 {

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

    List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> findLeaves (TreeNode root) {
        dfs(root);
        return lists;
    }

    private int dfs (TreeNode root) {
        if ( root == null ) {
            return 0;
        }

        int level_left = dfs(root.left);
        int level_right = dfs(root.right);

        int level_max = Math.max(level_left, level_right);

        if ( lists.size() == level_max ) {
            lists.add(new ArrayList<>());
        }

        List<Integer> list = lists.get(level_max);
        list.add(root.val);

        return level_max + 1;
    }
}
