package com.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/repeated-substring-pattern/description/">repeated-substring-pattern</a>
 */
public class leetcode0459 {

    public boolean repeatedSubstringPattern_01 (String s) {
        if ( s.isEmpty() ) return false;

        int s_size = s.length();
        for ( int i = 0 ; i < s_size ; i++ ) {
            // "abcabcabcabc"
            // "a"
            // "ab"
            // "abc"

            int substr_size = i + 1;
            if ( s_size % substr_size != 0 || s_size == substr_size ) continue;

            String substr = s.substring(0, substr_size);
            boolean found = true;

            for ( int j = 0 ; j < s_size ; j++ ) {

                int j_substr = j % substr.length(); // % -> circular
                if ( s.charAt(j) != substr.charAt(j_substr) ) {
                    found = false;
                    break;
                }
            }

            if ( found ) return true;
        }

        return false;
    }

    int magic_num = 31;
    int module_num = 1000000000 + 7;
    public boolean repeatedSubstringPattern_02 (String s) {
        if ( s.isEmpty() ) return false;

        long target_hash = hash(s);
        long now_hash = 0;
        int s_size = s.length();
        for ( int i = 0 ; i < s_size ; i++ ) {
            // "abcabcabcabc"
            // "a"
            // "ab"
            // "abc"

            int substr_size = i + 1;

            if ( i == 0 ) {
                now_hash = hash(s.substring(0, substr_size));
            }
            else {
                // rolling hash

                // abc -> abc_ move left
                now_hash *= magic_num;
                now_hash = mod(now_hash, module_num);

                // abc_ + d -> abcd
                now_hash += s.charAt(i);
                now_hash = mod(now_hash, module_num);
            }

            if ( s_size % substr_size != 0 || s_size == substr_size ) continue;

            int chunk_num = s_size / substr_size;
            long now_assembled_hash = now_hash;
            for ( int j = 1 ; j < chunk_num ; j++ ) {
                // abc -> abc___ move left
                for ( int m = 0 ; m < substr_size ; m++ ) {
                    // abc -> abc_
                    now_assembled_hash *= magic_num;
                    now_assembled_hash = mod(now_assembled_hash, module_num);
                }

                // abc___ + abc -> abcabc
                now_assembled_hash += now_hash;
                now_assembled_hash = mod(now_assembled_hash, module_num);
            }

            if ( now_assembled_hash == target_hash ) return true;
        }

        return false;
    }

    private long hash (String str) {
        long result = 0;

        for ( int i = 0 ; i < str.length() ; i++ ) {
            // ab -> ab_ move left
            result *= magic_num;
            result = mod(result, module_num);

            // ab_ + c -> abc
            result += str.charAt(i);
            result = mod(result, module_num);
        }

        return result;
    }

    private long mod (long num, long mod) {
        return (num % mod + mod) % mod;
    }

    public boolean repeatedSubstringPattern (String s) {
        if ( s.isEmpty() ) return false;

        int s_size = s.length();
        for ( int i = 0 ; i < s_size ; i++ ) {
            // "abcabcabcabc"
            // "a"
            // "ab"
            // "abc"

            int substr_size = i + 1;
            if ( s_size % substr_size != 0 || s_size == substr_size ) continue;

            String substr = s.substring(0, substr_size);
            boolean found = true;
            for ( int j = 0 ; j < s_size ; j += substr_size ) {
                String now_substr = s.substring(j, j + substr_size);
                if ( ! now_substr.equals(substr) ) {
                    found = false;
                    break;
                }
            }

            if ( found ) return true;
        }

        return false;
    }
}
