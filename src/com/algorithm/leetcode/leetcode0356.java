package com.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/line-reflection/">line-reflection(premium)</a>
 */
public class leetcode0356 {

    public boolean isReflected (int[][] points) {
        // mid lind = (min + max) / 2

        int x_min = Integer.MAX_VALUE;
        int x_max = Integer.MIN_VALUE;

        Map<Integer, Integer> map = new HashMap<>();

        int length = points.length;
        for ( int i = 0 ; i < length ; i++ ) {

            // odd * even = even number
            // even * even = even number
            points[i][0] *= 10;
            points[i][1] *= 10;

            int x = points[i][0];
            int y = points[i][1];

            if ( x < x_min ) {
                x_min = x;
            }
            if ( x > x_max ) {
                x_max = x;
            }

            int key = hash(x, y);

            map.put(key, i);
        }

        int x_mid = (x_min + x_max) / 2;

        for ( int i = 0 ; i < length ; i++ ) {
            int x_one_side = points[i][0];
            int y = points[i][1];

            int x_other_side = x_mid + (x_mid - x_one_side);

            int key = hash(x_other_side, y);

            if ( ! map.containsKey(key) ) {
                return false;
            }
        }

        return true;
    }

    private int hash (int x, int y) {
        return x * 31 + y * 31 * 31;
    }
}
