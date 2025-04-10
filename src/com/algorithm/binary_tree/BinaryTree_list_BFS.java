package com.algorithm.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用 List 實作二元樹
 * 使用 BFS
 */
public class BinaryTree_list_BFS {
    private Integer nums[];
    private Node root;

    static class Node {
        public Node left;
        public Node right;
        public Integer value;

        public Node () {}

        public Node (Integer value) {
            this.value = value;
        }
    }

    public BinaryTree_list_BFS (Integer[] nums) {
        this.nums = nums;
    }

    // 非本章學習重點，本方法僅模擬測試資料用
    public void build_tree () {
        if ( this.nums.length < 0 ) return;

        if ( this.nums[0] == null ) return;

        Node[] nodes_tmp = new Node[this.nums.length];
        for ( int i = 0 ; i < this.nums.length ; i++ ) {
            if ( this.nums[i] == null ) continue;

            nodes_tmp[i] = new Node(this.nums[i]);
        }

        // set root
        this.root = nodes_tmp[0];

        for ( int i = 0 ; i < this.nums.length ; i++ ) {
            int i_left = (i + 1) * 2 - 1;
            int i_right = (i + 1) * 2 + 1 - 1;

            Node node = nodes_tmp[i];
            if ( node == null ) continue;

            if ( i_left < this.nums.length ) {
                node.left = nodes_tmp[i_left];
            }

            if ( i_right < this.nums.length ) {
                node.right = nodes_tmp[i_right];
            }
        }
    }

    public void traverse_level_order_bfs () {
        if ( this.root == null ) return;

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(this.root);

        while ( true ) {
            if ( queue.isEmpty() ) break;

            Node node = queue.poll();

            if ( node == null ) continue;

            System.out.print( node.value + " " );

            queue.add(node.left);
            queue.add(node.right);
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

        BinaryTree_list_BFS binaryTree_list_bfs = new BinaryTree_list_BFS(nums);
        binaryTree_list_bfs.build_tree();

        binaryTree_list_bfs.traverse_level_order_bfs();

        System.out.println();
    }
}
