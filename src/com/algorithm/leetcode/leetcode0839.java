package com.algorithm.leetcode;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/similar-string-groups/description/">similar-string-groups</a>
 */
public class leetcode0839 {
    Map<String, String> node2root = new HashMap<>();

    void add (String str) {
        if ( node2root.containsKey(str) )
            return;
        node2root.put(str, str);
    }

    void union (String str1, String str2) {

        String root1 = find_root(str1);
        String root2 = find_root(str2);

        if ( root1.equals(root2) )
            return;

        node2root.put(root1, root2);
    }

    String find_root (String str) {
        String root = node2root.get(str);
        if ( root.equals(str) )
            return str;

        String root_final = find_root(root);
        node2root.put(str, root_final);

        return root_final;
    }

    public int numSimilarGroups (String[] strs) {

        for ( int i = 0 ; i < strs.length ; i++ ) {
            for ( int j = 0 ; j < strs.length ; j++ ) {
                String one = strs[i];
                String two = strs[j];

                add(one);
                add(two);

                if ( is_similar(one, two) ) {
                    union(one, two);
                }
            }
        }


        Set<String> roots = new HashSet<>();
        for ( int i = 0 ; i < strs.length ; i++ ) {
            String str = strs[i];
            String root = find_root(str);
            roots.add(root);
        }

        return roots.size();
    }

    // all str have the same length
    // all str have the same characters
    // they are only different in positions
    boolean is_similar (String a, String b) {
        int length = a.length();
        int mismatch_count = 0;
        for ( int i = 0 ; i < length ; i++ ) {
            char c1 = a.charAt(i);
            char c2 = b.charAt(i);
            if ( c1 != c2 ) {
                mismatch_count++;
            }
        }

        if ( mismatch_count > 2 )
            return false;
        return true;
    }
}
