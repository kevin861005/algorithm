package com.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/koko-eating-bananas/">...</a>
 */
public class leetcode0875 {

    public int minEatingSpeed (int[] piles, int h) {
        if ( piles == null || piles.length == 0 ) return -1;

        int max_banana_number = 1000000000;
        int min_banana_number = 1;

        return minEatingSpeed(piles, h, min_banana_number, max_banana_number);
    }

    private int minEatingSpeed (int[] piles, int h, int min_banana_number, int max_banana_number) {
        if ( min_banana_number > max_banana_number ) return -1;

        int result = -1;
        int temp_result = -1;

        int mid = (min_banana_number + max_banana_number) / 2;

        long total_hour = calculateHour(piles, mid);

        if ( total_hour <= h ) {
            result = mid;

            if ( min_banana_number < mid ) {    // it means the current mid may be still too big, so pick smaller one
                temp_result = minEatingSpeed(piles, h, min_banana_number, mid - 1);
                if ( temp_result != -1 ) result = temp_result;
            }
        }
        else {
            if ( mid < max_banana_number ) {
                result = minEatingSpeed(piles, h, mid + 1, max_banana_number);
            }
        }

        return result;
    }

    private long calculateHour (int[] piles, int mid) {
        int i = 0;
        long total_hour = 0;

        while ( true ) {
            int banana_number = piles[i];

            if ( banana_number % mid == 0 ) {
                total_hour += banana_number / mid;
            }
            else {
                total_hour += (banana_number / mid + 1);
            }

            i ++;

            if ( i >= piles.length ) break;
        }

        return total_hour;
    }

    public static void main (String[] args) {
        int[] piles = new int[]{805306368, 805306368, 805306368};
        int h = 1000000000;

        leetcode0875 leetcode0875 = new leetcode0875();

        long min = leetcode0875.minEatingSpeed(piles, h);

        System.out.println(min);
    }
}
