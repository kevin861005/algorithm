package com.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/max-area-of-island/description/">max-area-of-island</a>
 *
 * 本題就如同計算一顆二元樹的階層數量的邏輯
 */
public class leetcode0695 {

    static int stepped = -1;
    int best = 0;

    public int maxAreaOfIsland (int[][] grid) {
        if ( grid == null || grid.length == 0 ) return 0;

        int row = grid.length;
        int col = grid[0].length;

        for ( int i = 0 ; i < row ; i++ ) {
            for ( int j = 0 ; j < col ; j++ ) {
                int count = go(grid, i, j);

                if ( count > best ) {
                    best = count;
                }
            }
        }

        return best;
    }

    private int go (int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length) return 0;
        if (col < 0 || col >= grid[0].length) return 0;
        if (grid[row][col] == stepped) return 0;
        if (grid[row][col] == 0) {
            grid[row][col] = stepped;
            return 0;
        }

        grid[row][col] = stepped;

        // up
        int up = go(grid, row - 1, col);

        // down
        int down = go(grid, row + 1, col);

        // left
        int left = go(grid, row, col - 1);

        // right
        int right = go(grid, row, col + 1);

        return 1 + up + down + left + right;
    }
}
