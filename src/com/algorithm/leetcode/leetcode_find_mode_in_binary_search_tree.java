package com.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/find-mode-in-binary-search-tree/">...</a>
 */

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
 */
public class leetcode_find_mode_in_binary_search_tree {

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

    Integer prev_value = null;
    Integer count_max = 0;
    Integer count_now = 0;
    List<Integer> nodes = new ArrayList<>();

    public int[] findMode (TreeNode root) {
        if (root == null) return new int[0];

        findMode_helper(root);

        return convertToArray(nodes);
    }

    private int[] convertToArray (List<Integer> nodes) {
        int[] result = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            result[i] = nodes.get(i);
        }
        return result;
    }

    private void findMode_helper (TreeNode node) {
        if (node == null) return;

        findMode(node.left);

        if ( prev_value == null ) { // first node
            prev_value = node.val;

            count_now++;
        }
        else if ( prev_value != node.val ) {
            prev_value = node.val;

            count_now = 1;
        }
        else if ( prev_value == node.val ) {
            count_now++;
        }

        if ( count_now > count_max ) {
            count_max = count_now;
            nodes.clear();
            nodes.add(node.val);
        }
        else if ( count_now == count_max ) {
            nodes.add(node.val);
        }

        findMode(node.right);
    }
}
