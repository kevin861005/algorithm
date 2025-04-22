package com.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/">find-the-index-of-the-first-occurrence-in-a-string</a>
 */
public class leetcode0028 {

    // BigO: i * j
    public int strStr_01 (String haystack, String needle) {
        if ( haystack == null || needle == null || needle.length() > haystack.length() ) return -1;

        if ( needle.isEmpty() ) return 0;

        for ( int i = 0 ; i <= haystack.length() - needle.length() ; i++ ) {
            if ( haystack.charAt(i) == needle.charAt(0) ) {
                boolean found = true;

                for ( int j = 0 ; j < needle.length() ; j++ ) {
                    if ( haystack.charAt(i + j) != needle.charAt(j) ) {
                        found = false;
                        break;
                    }
                }

                if ( found ) return i;
            }
        }

        return -1;
    }

    int magic_num = 31;
    int module_num = 1000000000 + 7;

    // BigO: n
    public int strStr_02 (String haystack, String needle) {
        if ( haystack == null || needle == null || needle.length() > haystack.length() ) {
            return - 1;
        }

        if ( needle.isEmpty() ) {
            return 0;
        }

        long target_hash = hash(needle);

        long now_hash = 0;
        for ( int i = 0 ; i <= haystack.length() - needle.length() ; i++ ) {
            if ( i == 0 ) {
                now_hash = hash((haystack.substring(0, needle.length())));
            }
            else {
                // rolling hash

                // abc - a
                long power = (long) Math.pow(magic_num, needle.length() - 1);
                now_hash -= haystack.charAt(i - 1) * power;
                now_hash = mod(now_hash, module_num);

                // _bc -> bc_ move left
                now_hash *= magic_num;
                now_hash = mod(now_hash, module_num);

                // bc_ + d -> bcd
                now_hash += haystack.charAt(i + needle.length() + 1);
                now_hash = mod(now_hash, module_num);
            }

            if ( now_hash == target_hash ) {
                return i;
            }
        }

        return - 1;
    }

    private long hash (String needle) {
        long result = 0;

        for ( int i = 0 ; i < needle.length() ; i++ ) {
            // ab -> ab_ move left
            result *= magic_num;
            result = mod(result, module_num);

            // ab_ + c -> abc
            result += needle.charAt(i);
            result = mod(result, module_num);
        }

        return result;
    }

    private long mod (long num, long mod) {
        return (num % mod + mod) % mod;
    }

    // BigO: O(1)
    public int strStr (String haystack, String needle) {
        if ( haystack == null || needle == null || needle.length() > haystack.length() ) return -1;

        if ( needle.isEmpty() ) return 0;

        for ( int i = 0 ; i <= haystack.length() - needle.length() ; i++ ) {
            String haystack_part = haystack.substring(i, i + needle.length());
            if ( haystack_part.equals(needle) ) {
                return i;
            }
        }

        return -1;
    }
}
