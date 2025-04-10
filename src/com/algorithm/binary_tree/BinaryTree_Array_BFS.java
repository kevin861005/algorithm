package com.algorithm.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用 Array 實作二元樹
 */
public class BinaryTree_Array_BFS {

    private Integer[] nums;

    public BinaryTree_Array_BFS (Integer[] nums) {
        this.nums = nums;
    }

    public void traverse_level_order_bfs () {
        if ( this.nums.length < 0 ) return;

        Queue<Integer> queue = new LinkedList<Integer>();
        int i_root = 0;
        queue.add(i_root);

        while ( true ) {
            if ( queue.isEmpty() ) break;

            Integer i = queue.poll();
            if ( i >= this.nums.length ) continue;
            if ( this.nums[i] == null ) continue;

            System.out.print(this.nums[i] + " ");

            int i_plus_one = i + 1;
            int i_plus_one_left = i_plus_one * 2;
            int i_plus_one_right = i_plus_one * 2 + 1;

            int i_left = i_plus_one_left - 1;
            int i_right = i_plus_one_right - 1;

            queue.add(i_left);
            queue.add(i_right);
        }
    }

    public static void main (String[] args) {
        Integer[] nums = new Integer[]{
                5,
                2, 6,
                1, 4, null, 7,
                null, null, 3, null, null, null,
                null, null
        };

        BinaryTree_Array_BFS binaryTree_array_bfs = new BinaryTree_Array_BFS(nums);

        binaryTree_array_bfs.traverse_level_order_bfs();

        System.out.println();
    }
}
