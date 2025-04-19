package com.algorithm.binary_search_tree;

/**
 * 二元搜尋樹
 * 利用 Array（已排序好的，看成一棵二元搜尋樹） 實作
 * no need to build tree, because we simply change our "view" to the underlying array
 */
public class BST_SortedArray {
    private Integer[] nums;

    public BST_SortedArray (Integer[] nums) {
        this.nums = nums;
    }

    public Integer search (int value) {
        // pick middle-left node as our root every time
        int i_start = 0;
        int i_end = nums.length - 1;
        int i_root = (i_start + i_end) / 2;

        if ( this.nums[i_root] == null ) return null;

        return search(value, i_start, i_end);
    }

    private Integer search (int value, int start, int end) {
        if ( start > end ) return null;

        int mid = (start + end) / 2;

        if ( value == this.nums[mid] ) {
            return this.nums[mid];
        }
        else if ( value < this.nums[mid] ) {
            // go left
            return search(value, start, mid - 1);
        }
        else if ( value > this.nums[mid] ) {
            // go right
            return search(value, mid + 1, end);
        }

        return null;
    }

    public Integer search_leftmost (int value) {
        // pick middle-left node as our root every time
        int i_start = 0;
        int i_end = nums.length - 1;
        int i_root = (i_start + i_end) / 2;

        if ( this.nums[i_root] == null ) return null;

        return search_leftmost(value, i_start, i_end);
    }

    private Integer search_leftmost (int value, int start, int end) {
        if ( start > end ) return null;

        int mid = (start + end) / 2;

        if ( value == this.nums[mid] ) {
//            return this.nums[mid];
            Integer result = this.nums[mid];

            Integer result_left = search_leftmost(value, start, mid - 1);
            if ( result_left != null ) {
                result = result_left;

                System.out.print("\n(found more left result)");
            }

            return result;
        }
        if ( value < this.nums[mid] ) {
            // go left
            return search_leftmost(value, start, mid - 1);
        }
        if ( value > this.nums[mid] ) {
            // go right
            return search_leftmost(value, mid + 1, end);
        }

        return null;
    }

    public Integer search_rightmost (int value) {
        // pick middle-left node as our root every time
        int i_start = 0;
        int i_end = nums.length - 1;
        int i_root = (i_start + i_end) / 2;

        if ( this.nums[i_root] == null ) return null;

        return search_rightmost(value, i_start, i_end);
    }

    private Integer search_rightmost (int value, int start, int end) {
        if ( start > end ) return null;

        int mid = (start + end) / 2;

        if ( value == this.nums[mid] ) {
            //            return this.nums[mid];
            Integer result = this.nums[mid];

            Integer result_right = search_rightmost(value, mid + 1, end);
            if ( result_right != null ) {
                result = result_right;

                System.out.print("\n(found more right result)");
            }

            return result;
        }
        if ( value < this.nums[mid] ) {
            // go left
            return search_rightmost(value, start, mid - 1);
        }
        if ( value > this.nums[mid] ) {
            // go right
            return search_rightmost(value, mid + 1, end);
        }

        return null;
    }

    public static void main (String[] args) {
        Integer[] nums = {0, 11, 22, 44, 44, 44};
        BST_SortedArray bstSortedArray = new BST_SortedArray(nums);

        // search
        Integer node_found = bstSortedArray.search(22);
        System.out.print("\nfound: " + (node_found != null ? node_found : "not found"));
        node_found = bstSortedArray.search(44);
        System.out.print("\nfound: " + (node_found != null ? node_found : "not found"));
        Integer node_found_leftmost = bstSortedArray.search_leftmost(44);
        System.out.print("\nfound: " + (node_found_leftmost != null ? node_found_leftmost : "not found"));
        Integer node_found_rightmost = bstSortedArray.search_rightmost(44);
        System.out.print("\nfound: " + (node_found_rightmost != null ? node_found_rightmost : "not found"));
        System.out.println();
    }
}
