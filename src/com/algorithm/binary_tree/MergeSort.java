package com.algorithm.binary_tree;

/**
 * 實作 MergeSort
 * 概念非常像是 BST's Post-order traversal
 */
public class MergeSort {

    public static void merge_sort (int[] nums) {
        merge_sort(nums, 0, nums.length - 1);
    }

    private static void merge_sort (int[] nums, int i_start, int i_end) {
        if ( i_start == i_end ) return;

        int i_middle = (i_start + i_end) / 2;
        merge_sort(nums, i_start, i_middle);
        merge_sort(nums, i_middle + 1, i_end);

        // main logic
        sort(nums, i_start, i_middle, i_end);
    }

    private static void sort (int[] nums, int i_left, int i_middle, int i_right) {
        int[] nums_tmp = new int[nums.length];

        int i_nums_tmp = i_left;    // i_left ~ i_right
        int i_left_array = i_left;  // i_left ~ i_middle
        int i_right_array = i_middle + 1;   // i_middle + 1 ~ i_right

        while ( true ) {
            if ( i_nums_tmp > i_right ) break;

            Integer num_left = null;
            Integer num_right = null;

            // pick one number from left array
            if ( i_left_array <= i_middle ) {
                num_left = nums[i_left_array];
            }

            // pick one number from right array
            if ( i_right_array <= i_right ) {
                num_right = nums[i_right_array];
            }

            // compare and put into nums_tmp array
            if ( num_left != null && num_right != null ) {
                if ( num_left <= num_right ) {
                    nums_tmp[i_nums_tmp] = num_left;
                    i_left_array ++;
                }
                else {
                    nums_tmp[i_nums_tmp] = num_right;
                    i_right_array ++;
                }
            }
            else if ( num_left != null && num_right == null ) {
                nums_tmp[i_nums_tmp] = num_left;
                i_left_array ++;
            }
            else if ( num_left == null && num_right != null ) {
                nums_tmp[i_nums_tmp] = num_right;
                i_right_array ++;
            }

            i_nums_tmp ++;
        }

        // copy back to original array
        int index = i_left;
        while ( true ) {
            if ( index > i_right ) break;

            nums[index] = nums_tmp[index];

            index ++;
        }
    }

    public static void main (String[] args) {
        int[] array = {24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12};

        MergeSort.merge_sort(array);

        System.out.println();
    }
}
