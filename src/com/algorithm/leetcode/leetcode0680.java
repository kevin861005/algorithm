package com.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/valid-palindrome-ii/description/">valid-palindrome-ii</a>
 */
public class leetcode0680 {

    int count = 0;
    int limit = 1;

    public boolean validPalindrome (String s) {
        int i_left = 0;
        int i_right = s.length() - 1;

        return solve(s, i_left, i_right);
    }

    private boolean solve (String s, int i_left, int i_right) {
        if ( i_left > i_right ) return true;

        char c_left = s.charAt(i_left);
        char c_right = s.charAt(i_right);

        if ( c_left != c_right ) {
            if ( count < limit ) {
                count ++;

                boolean left_found = solve(s, i_left + 1, i_right);

                boolean right_found = solve(s, i_left, i_right - 1);

                if ( ! left_found && ! right_found ) {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else {
            return solve(s, i_left + 1, i_right - 1);
        }

        return true;
    }
}
