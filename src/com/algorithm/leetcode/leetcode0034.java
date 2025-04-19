package com.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/">...</a>
 */
public class leetcode0034 {

    public int[] searchRange (int[] nums, int target) {
        if ( nums == null || nums.length == 0 ) return new int[]{-1, -1};

        return searchRange(nums, target, 0, nums.length - 1);
    }

    private int[] searchRange (int[] nums, int target, int start, int end) {
        if ( start > end ) return new int[]{-1, -1};

        int mid = (start + end) / 2;

        int[] result = new int[]{-1, -1};
        int[] temp_left;
        int[] temp_right;

        if ( target == nums[mid] ) {
            result[0] = mid;
            result[1] = mid;

            temp_left = searchRange(nums, target, start, mid - 1);
            if (temp_left[0] != -1) {
                result[0] = temp_left[0];
            }

            temp_right = searchRange(nums, target, mid + 1, end);
            if (temp_right[1] != -1) {
                result[1] = temp_right[1];
            }
        }
        else if ( target < nums[mid] ) {
            result = searchRange(nums, target, start, mid - 1);
        }
        else {
            result = searchRange(nums, target, mid + 1, end);
        }

        return result;
    }
}
