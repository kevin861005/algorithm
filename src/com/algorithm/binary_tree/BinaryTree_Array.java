package com.algorithm.binary_tree;

/**
 * 用 Array 實作二元樹
 */
public class BinaryTree_Array {

    private Integer[] nums;

    public BinaryTree_Array (Integer[] nums) {
        this.nums = nums;
    }

    // pre-order traversal DFS left
    public void pre_order_traversal_dfs_left () {
        if ( this.nums.length <= 0 ) return;

        if ( this.nums[0] == null ) return;

        pre_order_traversal_dfs_left(0);
    }

    private void pre_order_traversal_dfs_left (int index) {
        if ( index >= this.nums.length ) return;

        int index_plus_one = index + 1;
        int index_left_plus_one = index_plus_one * 2;
        int index_right_plus_one = index_plus_one * 2 + 1;

        int index_left = index_left_plus_one - 1;
        int index_right = index_right_plus_one - 1;

        if ( this.nums[index]  != null) System.out.print(this.nums[index] + " ");

        pre_order_traversal_dfs_left(index_left);
        pre_order_traversal_dfs_left(index_right);
    }

    // in-order traversal DFS left
    public void in_order_traversal_dfs_left () {
        if ( this.nums.length <= 0 ) return;

        if ( this.nums[0] == null ) return;

        in_order_traversal_dfs_left(0);
    }

    private void in_order_traversal_dfs_left (int index) {
        if ( index >= this.nums.length ) return;

        int index_plus_one = index + 1;
        int index_left_plus_one = index_plus_one * 2;
        int index_right_plus_one = index_plus_one * 2 + 1;

        int index_left = index_left_plus_one - 1;
        int index_right = index_right_plus_one - 1;

        in_order_traversal_dfs_left(index_left);

        if ( this.nums[index]  != null) System.out.print(this.nums[index] + " ");

        in_order_traversal_dfs_left(index_right);
    }

    // post-order traversal DFS left
    public void post_order_traversal_dfs_left () {
        if ( this.nums.length <= 0 ) return;

        if ( this.nums[0] == null ) return;

        post_order_traversal_dfs_left(0);
    }

    private void post_order_traversal_dfs_left (int index) {
        if ( index >= this.nums.length ) return;


        int index_plus_one = index + 1;
        int index_left_plus_one = index_plus_one * 2;
        int index_right_plus_one = index_plus_one * 2 + 1;

        int index_left = index_left_plus_one - 1;
        int index_right = index_right_plus_one - 1;

        post_order_traversal_dfs_left(index_left);
        post_order_traversal_dfs_left(index_right);

        if ( this.nums[index]  != null) System.out.print(this.nums[index] + " ");
    }

    // pre-order traversal DFS right
    public void pre_order_traversal_dfs_right () {
        if ( this.nums.length <= 0 ) return;

        if ( this.nums[0] == null ) return;

        pre_order_traversal_dfs_right(0);
    }

    private void pre_order_traversal_dfs_right (int index) {
        if ( index >= this.nums.length ) return;

        int index_plus_one = index + 1;
        int index_left_plus_one = index_plus_one * 2;
        int index_right_plus_one = index_plus_one * 2 + 1;

        int index_left = index_left_plus_one - 1;
        int index_right = index_right_plus_one - 1;

        if ( this.nums[index]  != null) System.out.print(this.nums[index] + " ");

        pre_order_traversal_dfs_right(index_right);
        pre_order_traversal_dfs_right(index_left);
    }

    // in-order traversal DFS right
    public void in_order_traversal_dfs_right () {
        if ( this.nums.length <= 0 ) return;

        if ( this.nums[0] == null ) return;

        in_order_traversal_dfs_right(0);
    }

    private void in_order_traversal_dfs_right (int index) {
        if ( index >= this.nums.length ) return;


        int index_plus_one = index + 1;
        int index_left_plus_one = index_plus_one * 2;
        int index_right_plus_one = index_plus_one * 2 + 1;

        int index_left = index_left_plus_one - 1;
        int index_right = index_right_plus_one - 1;

        in_order_traversal_dfs_right(index_right);

        if ( this.nums[index]  != null) System.out.print(this.nums[index] + " ");

        in_order_traversal_dfs_right(index_left);
    }

    // post-order traversal DFS right
    public void post_order_traversal_dfs_right () {
        if ( this.nums.length <= 0 ) return;

        if ( this.nums[0] == null ) return;

        post_order_traversal_dfs_right(0);
    }

    private void post_order_traversal_dfs_right (int index) {
        if ( index >= this.nums.length ) return;


        int index_plus_one = index + 1;
        int index_left_plus_one = index_plus_one * 2;
        int index_right_plus_one = index_plus_one * 2 + 1;

        int index_left = index_left_plus_one - 1;
        int index_right = index_right_plus_one - 1;

        post_order_traversal_dfs_right(index_right);
        post_order_traversal_dfs_right(index_left);

        if ( this.nums[index]  != null) System.out.print(this.nums[index] + " ");
    }

    public static void main (String[] args) {
        Integer[] nums = new Integer[]{
                5,
                2, 6,
                1, 4, null, 7,
                null, null, 3, null, null, null,
                null, null
        };

        BinaryTree_Array binaryTree_array = new BinaryTree_Array(nums);

        // DFS left
        System.out.print("DFS left pre-order: ");
        binaryTree_array.pre_order_traversal_dfs_left();
        System.out.print("\nDFS left in-order: ");
        binaryTree_array.in_order_traversal_dfs_left();
        System.out.print("\nDFS left post-order: ");
        binaryTree_array.post_order_traversal_dfs_left();

        // DFS right
        System.out.print("\nDFS right pre-order: ");
        binaryTree_array.pre_order_traversal_dfs_right();
        System.out.print("\nDFS right in-order: ");
        binaryTree_array.in_order_traversal_dfs_right();
        System.out.print("\nDFS right post-order: ");
        binaryTree_array.post_order_traversal_dfs_right();

        System.out.println();
    }
}
