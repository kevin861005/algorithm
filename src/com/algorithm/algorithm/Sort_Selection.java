package com.algorithm.algorithm;

import java.util.Arrays;

/**
 * Selection Sort 實作
 * 每次從未排序區段中選出最小（大）元素放前面
 */
public class Sort_Selection {

    public static void selection_sort (int[] nums) {
        for ( int i_start = 0 ; i_start < nums.length ; i_start++ ) {
            int i_max = i_start;

            for ( int j_run = i_start + 1 ; j_run < nums.length ; j_run++ ) {
                if ( nums[j_run] > nums[i_max] ) {
                    i_max = j_run;
                }
            }

            swap(nums, i_start, i_max);
        }
    }

    public static void selection_sort_recursion (int[] nums) {
        int start = 0;

        selection_sort_recursion_help01(nums, start);

//        for ( int i_start = 0 ; i_start < nums.length ; i_start++ ) {
//            int i_max = i_start;
//
//            for ( int j_run = i_start + 1 ; j_run < nums.length ; j_run++ ) {
//                if ( nums[j_run] > nums[i_max] ) {
//                    i_max = j_run;
//                }
//            }
//
//            swap(nums, i_start, i_max);
//        }
    }

    private static void selection_sort_recursion_help01 (int[] nums, int start) {
        if ( start >= nums.length ) return;

        int max = start;
        int run = start + 1;

        max = selection_sort_recursion_help02(nums, max, run);

        swap(nums, start, max);

        selection_sort_recursion_help01(nums, start + 1);
    }

    private static int selection_sort_recursion_help02 (int[] nums, int max, int run) {
        if ( run >= nums.length ) return max;

        if ( nums[run] > nums[max] ) {
            max = run;
        }

        return selection_sort_recursion_help02(nums, max, run + 1);
    }

    private static void swap (int[] nums, int i_left, int i_right) {
        int tmp = nums[i_left];

        nums[i_left] = nums[i_right];

        nums[i_right] = tmp;
    }

    public static void main (String[] args) {
        int[] nums = new int[]{8, 2, 6, 10, 4};

//        Sort_Selection.selection_sort(nums);
        Sort_Selection.selection_sort_recursion(nums);

        System.out.println(Arrays.toString(nums));

    }
}
