package com.algorithm.binary_search_tree;

/**
 * 二元搜尋樹
 * 利用 Array 實作
 * attention: we can NOT implement delete in array form BST
 * why: it's too complex to maintain
 * example: delete one node, "ALL" of its child nodes have to be moved left
 */
public class BST_Array {
    private Integer[] nums;

    private Integer[] bst;

    public BST_Array(Integer[] nums) {
        this.nums = nums;
    }

    public void build_tree () {
        this.bst = new Integer[this.nums.length];
        for ( int i = 0; i < this.nums.length; i++ ) {
            add(this.nums[i]);
        }
    }

    public void add(int value) {
        int i_root = 0;

        add(i_root, value);
    }

    private void add(int i_node, int value) {
        // check storage size
        if ( i_node >= this.bst.length ) {
            expand_storage();
        }

        // found the right spot for inserting new node
        if ( this.bst[i_node] == null ) {
            this.bst[i_node] = value;
            return;
        }

        if ( this.bst[i_node] == value ) {
            System.out.println("node: " + value + " already exists, skip insert!");
        }
        else if ( value < this.bst[i_node] ) {
            int i_left = (i_node + 1) * 2 - 1;
            add(i_left, value);
        }
        else if ( value > this.bst[i_node] ) {
            int i_right = (i_node + 1) * 2 - 1 + 1;
            add(i_right, value);
        }
    }

    private void expand_storage() {
        Integer[] new_bst = new Integer[this.bst.length * 2];
        for ( int i = 0; i < this.bst.length; i++ ) {
            new_bst[i] = this.bst[i];
        }
        this.bst = new_bst;
    }

    public Integer search (int value) {
        int i_root = 0;
        if ( this.bst[i_root] == null ) return null;

        return search(i_root, value);
    }

    private Integer search (int i_node, int value) {
        if ( i_node >= this.bst.length ) return null;
        if ( this.bst[i_node] == null ) return null;

        Integer result = null;

        if ( this.bst[i_node] == value ) {
            result = this.bst[i_node];
        }
        else if ( value < this.bst[i_node] ) {
            // go left
            int i_left = (i_node + 1) * 2 - 1;
            result = search(i_left, value);
        }
        else if ( value > this.bst[i_node] ) {
            // go right
            int i_right = (i_node + 1) * 2 - 1 + 1;
            result = search(i_right, value);
        }

        return result;
    }

    public static void main (String[] args) {
        Integer[] nums = {5, 2, 6, 1, 4, 7, 3};
        BST_Array array = new BST_Array(nums);

        // build tree
        array.build_tree();
        System.out.println();

        // search
        Integer node_found = array.search(2);
        Integer node_not_found = array.search(10);
    }
}
