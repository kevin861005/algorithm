package com.algorithm.binary_search_tree;

/**
 * TreeSort
 * 利用 BST 實作
 */
public class BST_TreeSort {

    private int[] nums;

    private BST_Node root;

    static class BST_Node {
        public BST_Node left;
        public BST_Node right;
        public int value;
        public String owner;

        public BST_Node () {}

        public BST_Node (int value) {
            this.value = value;
        }

        public BST_Node (int value, String owner) {
            this.value = value;
            this.owner = owner;
        }
    }

    public BST_TreeSort (int[] nums) {
        this.nums = nums;
    }

    public void build_tree () {
        for ( int i = 0; i < nums.length; i++ ) {
            add(this.nums[i]);
        }
    }

    // insert
    public void add (int value) {
        BST_Node root_after_add = add(this.root, value);

        if ( this.root != root_after_add ) {
            this.root = root_after_add;
        }
    }

    private BST_Node add (BST_Node node, int value) {
        // found the right spot for inserting new node
        if ( node == null ) {
            node = new BST_Node(value);
            return node;
        }

        if ( value == node.value ) {
            if ( node.left == null ) {
                node.left = new BST_Node(value);
            }
            else {
                BST_Node new_left_node = new BST_Node(value);
                new_left_node.left = node.left;
                node.left = new_left_node;
            }
        }
        else if ( value < node.value ) {
            BST_Node left = add( node.left, value );
            if ( node.left != left ) {
                node.left = left;
            }
        }
        else if ( value > node.value ) {
            BST_Node right = add( node.right, value );
            if ( node.right != right ) {
                node.right = right;
            }
        }

        return node;
    }

    // inorder traversal (DFS left)
    public void traverse_inorder () {
        if ( this.root == null ) return;

        traverse_inorder(this.root);
    }

    private void traverse_inorder(BST_Node node) {
        if ( node == null ) return;

        traverse_inorder(node.left);

        System.out.print(node.value + " ");

        traverse_inorder(node.right);
    }

    // inorder traversal (DFS right)
    public void traverse_inorder_dfs_right () {
        if ( this.root == null ) return;

        traverse_inorder_dfs_right(this.root);
    }

    public void traverse_inorder_dfs_right (BST_Node node) {
        if ( node == null ) return;

        traverse_inorder_dfs_right(node.right);

        System.out.print(node.value + " ");

        traverse_inorder_dfs_right(node.left);
    }

    // tree sort (DFS left)
    public static void tree_sort_ascending (int[] nums) {
        BST_TreeSort tree = new BST_TreeSort(nums);
        tree.build_tree();
        tree.traverse_inorder();
    }

    // tree sort (DFS right)
    public static void tree_sort_descending (int[] nums) {
        BST_TreeSort tree = new BST_TreeSort(nums);
        tree.build_tree();
        tree.traverse_inorder_dfs_right();
    }

    public static void main (String[] args) {
//        int[] nums = {5, 6, 7, 2, 4, 1, 3};
        int[] nums = {5, 6, 7, 2, 1, 6, 2}; // duplicate

        System.out.print("\ntree_sort_ascending (dfs left): ");
        BST_TreeSort.tree_sort_ascending(nums);
        System.out.print("\ntree_sort_descending (dfs right): ");
        BST_TreeSort.tree_sort_descending(nums);
    }
}
