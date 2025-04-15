package com.algorithm.binary_search_tree;

/**
 * 二元搜尋樹
 * 利用 List 實作
 */
public class BST_List {

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

    public BST_List (int[] nums) {
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
            System.out.println("node: " + value + " already exists, skip insert!");
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

    // delete
    public void delete (int value) {
        if ( this.root == null ) return;

        BST_Node root_after_delete = delete(this.root, value);
        if ( this.root != root_after_delete ) {
            this.root = root_after_delete;
        }
    }

    // return the current node (only changed if deleted)
    private BST_Node delete (BST_Node node, int value) {
        if ( node == null ) return null;

        BST_Node node_final = node;

        if ( value == node.value ) {
            // x x 接骨概念 - 被刪除的節點，兩邊都無 - 將 null 回傳給上一層接上
            if ( node.left == null && node.right == null ) {
                node_final = null;
            }
            // x right 接骨概念 - 被刪除的節點，只剩右邊 - 將右節點回傳給上一層接上
            else if ( node.left == null && node.right != null ) {
                node_final = node.right;
            }
            // left x 接骨概念 - 被刪除的節點，只剩左邊 - 將左節點回傳給上一層接上
            else if ( node.left != null && node.right == null ) {
                node_final = node.left;
            }
            // left right 接骨概念 - 被刪除的節點，只剩左右邊
            else {
                // 將被刪除節點與右子樹中最小的節點互換「值」
                BST_Node node_min = getMinNode(node.right);
                swap_node_value(node, node_min);
                // 開啟第二戰場，最後會停在「被刪除節點，只剩右邊」這個狀態
                BST_Node node_child = delete(node.right, value);
                if ( node.right != node_child ) {
                    node.right = node_child;
                }
            }
        }
        else if ( value < node.value ) {
            BST_Node left = delete( node.left, value ); // traverse left
            if ( node.left != left ) {  // if any child link changed, update it
                node.left = left;
            }
        }
        else if ( value > node.value ) {
            BST_Node right = delete( node.right, value );   // traverse right
            if ( node.right != right ) {    // if any child link changed, update it
                node.right = right;
            }
        }

        return node_final;
    }

    public BST_Node getMinNode () {
        return getMinNode(this.root);
    }

    private BST_Node getMinNode (BST_Node root) {
        if ( root == null ) return null;

        BST_Node min = root;
        while ( true ) {
            if ( min.left == null ) break;

            min = min.left;
        }

        return min;
    }

    public BST_Node getMaxNode () {
        return getMaxNode(this.root);
    }

    private BST_Node getMaxNode (BST_Node root) {
        if ( root == null ) return null;

        BST_Node max = root;
        while ( true ) {
            if ( max.right == null ) break;

            max = max.right;
        }

        return max;
    }

    private void swap_node_value (BST_Node left, BST_Node right) {
        int temp = left.value;
        left.value = right.value;
        right.value = temp;
    }

    // search
    public BST_Node search (int value) {
        if ( this.root == null ) return null;

        return search(this.root, value);
    }

    private BST_Node search (BST_Node node, int value) {
        if ( node == null ) return null;

        BST_Node result = null;

        if ( value == node.value ) {    // traverse stop
            result = node;
        }
        else if ( value < node.value ) {    // traverse left
            result = search( node.left, value );
        }
        else if ( value > node.value ) {    // traverse right
            result = search( node.right, value );
        }

        return result;
    }

    public static void main (String[] args) {
        int[] nums = {5, 2, 6, 1, 4, 7, 3};
        BST_List list = new BST_List(nums);

        // build tree
        list.build_tree();
        System.out.println();

        // delete
        // x x
//        list.delete(7);
//        System.out.println();

        // x right
//        list.delete(6);
//        System.out.println();

        // left x
//        list.delete(4);
//        System.out.println();

        // left right
//        list.delete(2);
//        System.out.println();

        // delete root
//        list.delete(5);
//        System.out.println();

        // search
//        BST_Node node_found = list.search(2);
//        BST_Node node_not_found = list.search(10);

        // in-order traversal = n * (getMinNode + delete)
//        while ( true ) {
//            BST_Node node_min = list.getMinNode();
//            if ( node_min == null ) break;
//
//            System.out.print( node_min.value + " " );
//            list.delete(node_min.value);
//        }

        // in-order traversal = n * (getMaxNode + delete)
//        while ( true ) {
//            BST_Node node_max = list.getMaxNode();
//            if ( node_max == null ) break;
//
//            System.out.print( node_max.value + " " );
//            list.delete(node_max.value);
//        }
    }
}
