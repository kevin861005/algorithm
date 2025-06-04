package com.algorithm.binary_tree;

/**
 * 用 List 實作二元樹
 * DFS left: 能往左邊走到底，就先往左邊走到底
 * DFS right: 能往右邊走到底，就先往右邊走到底
 */
public class BinaryTree_List {
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

    public BinaryTree_List (Integer[] nums) {
        this.nums = nums;
    }

    // 非本章學習重點，本方法僅模擬測試資料用
    public void build_tree () {
        if ( this.nums.length <= 0 ) return;

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

    // pre-order traversal DFS left
    public void pre_order_traversal_dfs_left () {
        if ( this.root == null ) return;

        pre_order_traversal_dfs_left(this.root);
    }

    private void pre_order_traversal_dfs_left (Node node) {
        if ( node == null ) return;

        System.out.print(node.value + " ");

        pre_order_traversal_dfs_left(node.left);
        pre_order_traversal_dfs_left(node.right);
    }

    // in-order traversal DFS left
    public void in_order_traversal_dfs_left () {
        if ( this.root == null ) return;

        in_order_traversal_dfs_left(this.root);
    }

    private void in_order_traversal_dfs_left (Node node) {
        if ( node == null ) return;

        in_order_traversal_dfs_left(node.left);

        System.out.print(node.value + " ");

        in_order_traversal_dfs_left(node.right);
    }

    // post-order traversal DFS left
    public void post_order_traversal_dfs_left () {
        if ( this.root == null ) return;

        post_order_traversal_dfs_left(this.root);
    }

    private void post_order_traversal_dfs_left (Node node) {
        if ( node == null ) return;

        post_order_traversal_dfs_left(node.left);

        post_order_traversal_dfs_left(node.right);

        System.out.print(node.value + " ");
    }

    // pre-order traversal DFS right
    public void pre_order_traversal_dfs_right () {
        if ( this.root == null ) return;

        pre_order_traversal_dfs_right(this.root);
    }

    private void pre_order_traversal_dfs_right (Node node) {
        if ( node == null ) return;

        System.out.print(node.value + " ");

        pre_order_traversal_dfs_right(node.right);
        pre_order_traversal_dfs_right(node.left);
    }

    // in-order traversal DFS right
    public void in_order_traversal_dfs_right () {
        if ( this.root == null ) return;

        in_order_traversal_dfs_right(this.root);
    }

    private void in_order_traversal_dfs_right (Node node) {
        if ( node == null ) return;

        in_order_traversal_dfs_right(node.right);

        System.out.print(node.value + " ");

        in_order_traversal_dfs_right(node.left);
    }

    // post-order traversal DFS right
    public void post_order_traversal_dfs_right () {
        if ( this.root == null ) return;

        post_order_traversal_dfs_right(this.root);
    }

    private void post_order_traversal_dfs_right (Node node) {
        if ( node == null ) return;

        post_order_traversal_dfs_right(node.right);

        post_order_traversal_dfs_right(node.left);

        System.out.print(node.value + " ");
    }

    public static void main (String[] args) {
        Integer[] nums = new Integer[]{
                5,
                2, 6,
                1, 4, null, 7,
                null, null, 3, null, null, null,
                null, null
        };

        BinaryTree_List binaryTree_list = new BinaryTree_List(nums);
        binaryTree_list.build_tree();

        // DFS left
        System.out.print("DFS left pre-order: ");
        binaryTree_list.pre_order_traversal_dfs_left();
        System.out.print("\nDFS left in-order: ");
        binaryTree_list.in_order_traversal_dfs_left();
        System.out.print("\nDFS left post-order: ");
        binaryTree_list.post_order_traversal_dfs_left();

        // DFS right
        System.out.print("\nDFS right pre-order: ");
        binaryTree_list.pre_order_traversal_dfs_right();
        System.out.print("\nDFS right in-order: ");
        binaryTree_list.in_order_traversal_dfs_right();
        System.out.print("\nDFS right post-order: ");
        binaryTree_list.post_order_traversal_dfs_right();

        System.out.println();
    }
}
