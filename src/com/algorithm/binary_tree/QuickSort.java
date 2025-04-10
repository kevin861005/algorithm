package com.algorithm.binary_tree;

/**
 * 實作 QuickSort
 * 概念非常像是 BST's Pre-order traversal
 */
public class QuickSort {
    public static void quick_sort (int[] nums) {
        quick_sort(nums, 0, nums.length - 1);
    }

    private static void quick_sort (int[] nums, int i_left, int i_right) {
        // end condition - one-element array is sorted already
        if ( i_left == i_right ) return;    // one element means sorted already
        if ( i_left > i_right ) return; // out of boundary sub-array

        // main logic - conquer - pick pivot
        int i_pivot_guess = pick_pivot_index(nums, i_left, i_right);

        // main logic - conquer - sort the current array chunk - pivot index would be moved around here
        int i_pivot_final = sort(nums, i_left, i_right, i_pivot_guess);

        quick_sort(nums, i_left, i_pivot_final - 1);
        quick_sort(nums, i_pivot_final + 1, i_right);
    }

    private static int sort (int[] nums, int i_start, int i_end, int pivot) {
        // goal: use existing space only
        int i_pivot_now = pivot;

        // step1: swap pivot value to the last element
        swap(nums, i_pivot_now, i_end); // swap value
        i_pivot_now = i_end;    // update index

        // step2: sort the rest of array
        int i_left = i_start;
        int i_right = i_end - 1;    // exclude pivot from the following process
        while ( true ) {

            while ( true ) {
                if ( i_left == i_right ) break;
                if ( nums[i_left] > nums[i_pivot_now] ) break;  // 找到不應該出現在「左」側的數

                i_left ++;
            }

            while ( true ) {
                if ( i_left == i_right ) break;
                if ( nums[i_right] < nums[i_pivot_now] ) break;  // 找到不應該出現在「右」側的數

                i_right --;
            }

            if ( i_left == i_right ) break; // means end sort

            swap(nums, i_left, i_right);
        }

        // step3: put pivot element to the right place
        int i_meeting_point = i_left;   // or i_meeting_point = i_right
        if ( nums[i_meeting_point] >= nums[i_pivot_now] ) {
            swap(nums, i_pivot_now, i_meeting_point);
            i_pivot_now = i_meeting_point;
        }

        return i_pivot_now;
    }

    private static void swap (int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static int pick_pivot_index (int[] nums, int i_left, int i_right) {
        return (i_left + i_right) / 2;
    }

    public static void main (String[] args) {
        int[] nums = {20, 10, 50, 30, 70, 60, 40};

        QuickSort.quick_sort(nums);

        System.out.println();
    }
}
