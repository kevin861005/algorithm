package com.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/group-anagrams/">group-anagrams</a>
 */
public class leetcode0049 {

    long[] c_order = new long[26];

    public List<List<String>> groupAnagrams (String[] strs) {

        for (int i = 0 ; i < c_order.length ; i++ ) {
            // i == 0 : 'a'
            // i == 1 : 'b'
            // i == 25 : 'z'

            long order_val = 1L;

            for ( int j = 0 ; j < i ; j++ ) {
                order_val *= 31;
                order_val = mod(order_val, 1000000007L);
            }

            c_order[i] = order_val;
        }

        Map<Long, List<String>> map = new HashMap<>();
        for ( String str : strs ) {
            long hash = hash(str);

            List<String> list = map.get(hash);
            if ( list == null ) {
                list = new ArrayList<>();
                map.put(hash, list);
            }

            list.add(str);
        }

        // convert to answer
        List<List<String>> answer = new ArrayList<>();
        for ( List<String> list : map.values() ) {
            answer.add(list);
        }

        return answer;
    }

    private Long hash (String str) {
        // hash - character: ASCII 'a' -> 97, 'b' -> 98
        // 'abb' = 97 + 98 + 98 = 293
        // 'aac' = 97 + 97 + 99 = 293
        // hash - character level:
        // 'a' : 31^0
        // 'b' : 31^1
        // 'z' : 31^25

        long hash = 0L;
        for (int i = 0 ; i < str.length() ; i++ ) {
            char c = str.charAt(i);

            int order = c - 'a';

            long hash_to_add = c;
            hash_to_add *= c_order[order];

            hash += hash_to_add;
        }

        return hash;
    }

    private Long mod (long n, long m) {
        return (n % m + m) % m;
    }
}
