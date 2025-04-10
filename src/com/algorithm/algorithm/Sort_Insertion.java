package com.algorithm.algorithm;

import java.util.Arrays;

/**
 * Insertion Sort 實作
 * 將元素插入前面已排序的區段中
 */
public class Sort_Insertion {
    public static void insertion_sort (int[] nums) {
        for ( int i_start = 0 ; i_start < nums.length ; i_start++ ) {
            for ( int j_run = i_start - 1 ; j_run >= 0 ; j_run-- ) {
                if ( nums[j_run + 1] > nums[j_run] ) {
                    swap(nums, j_run + 1, j_run);
                }
                else {
                    break;
                }
            }
        }
    }

    public static void insertion_sort_recursion (int[] nums) {
        int start = 0;

        insertion_sort_recursion_help01(nums, start);
    }

    private static void insertion_sort_recursion_help01 (int[] nums, int start) {
        if ( start >= nums.length ) return;

        int run = start - 1;
        insertion_sort_recursion_help02(nums, run);

        insertion_sort_recursion_help01(nums, start + 1);
    }

    private static void insertion_sort_recursion_help02 (int[] nums, int run) {
        if ( run < 0 ) return;

        if ( nums[run + 1] > nums[run] ) {
            swap(nums, run + 1, run);
        }
        else {
            return;
        }

        insertion_sort_recursion_help02(nums, run - 1);
    }

    private static void swap (int[] nums, int i_left, int i_right) {
        int tmp = nums[i_left];

        nums[i_left] = nums[i_right];

        nums[i_right] = tmp;
    }

    public static void main (String[] args) {
        int[] nums = new int[]{8, 2, 6, 10, 4};

//        Sort_Insertion.insertion_sort(nums);
        Sort_Insertion.insertion_sort_recursion(nums);

        System.out.println(Arrays.toString(nums));
    }
}
