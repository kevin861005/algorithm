package com.algorithm.algorithm;

import java.util.Arrays;

/**
 * 氣泡排序實作
 * 不斷比較相鄰元素並交換位置
 */
public class Sort_Bubble {

    public static void bubble_sort (int[] nums) {
        for ( int round = 0 ; round < nums.length ; round++ ) {
            int len = nums.length - round;
            for ( int i_run = 1 ; i_run < len ; i_run++ ) {
                if ( nums[i_run - 1] > nums[i_run] ) {  // 前者大於後者
                    swap(nums, i_run - 1, i_run);
                }
            }
        }
    }

    public static void bubble_sort_recursion (int[] nums) {
        int round = 0;

        bubble_sort_recursion_help01(nums, round);

//        for ( int round = 0 ; round < nums.length ; round++ ) {
//            int len = nums.length - round;
//            for ( int i_run = 1 ; i_run < len ; i_run++ ) {
//                if ( nums[i_run - 1] > nums[i_run] ) {  // 前者大於後者
//                    swap(nums, i_run - 1, i_run);
//                }
//            }
//        }
    }

    private static void bubble_sort_recursion_help01 (int[] nums, int round) {
        if ( round >= nums.length ) return;

        int i_run = 1;
        int len = nums.length - round;
        bubble_sort_recursion_help02(nums, i_run, len);

        bubble_sort_recursion_help01(nums, round + 1);
    }

    private static void bubble_sort_recursion_help02 (int[] nums, int i_run, int len) {
        if ( i_run >= len ) return;

        if ( nums[i_run - 1] > nums[i_run] ) {  // 前者大於後者
            swap(nums, i_run - 1, i_run);
        }

        bubble_sort_recursion_help02(nums, i_run + 1, len);
    }

    private static void swap (int[] nums, int i_left, int i_right) {
        int tmp = nums[i_left];

        nums[i_left] = nums[i_right];

        nums[i_right] = tmp;
    }

    public static void main (String[] args) {
        int[] nums = new int[]{8, 2, 6, 10, 4};

//        Sort_Bubble.bubble_sort(nums);
        Sort_Bubble.bubble_sort_recursion(nums);

        System.out.println(Arrays.toString(nums));
    }
}
