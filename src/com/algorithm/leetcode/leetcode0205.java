package com.algorithm.leetcode;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/isomorphic-strings/">isomorphic-strings</a>
 */
public class leetcode0205 {
    // rule1: char in s mapping to one char in t
    // rule2: "a" -> "k", "b" -> "k" not allow
    public boolean isIsomorphic (String s, String t) {

        // 8 ms
        HashMap<Character, Character> map = new HashMap<>();
        HashMap<Character, Character> map_key_used = new HashMap<>();

        // 10 ms
//        TreeMap<Character, Character> map = new TreeMap<>();
//        TreeMap<Character, Character> map_key_used = new TreeMap<>();

        for ( int i = 0 ; i < s.length() ; i++ ) {
            char c_s = s.charAt(i);
            char c_t = t.charAt(i);

            Character c_t_in_map = map.get(c_s);
            if ( c_t_in_map == null ) {
                if ( map_key_used.containsKey(c_t) ) {
                    return false;
                }
                map.put(c_s, c_t);
                map_key_used.put(c_t, c_t);
            }
            else {
                if ( c_t_in_map != c_t ) {
                    return false;
                }
            }
        }

        return true;
    }

    static class Node {
        public Character char_in_s;
        public Character char_in_t;

        public Node (Character char_in_s, Character char_in_t) {
            this.char_in_s = char_in_s;
            this.char_in_t = char_in_t;
        }
    }

    // 25 ms
    public boolean isIsomorphic_withList (String s, String t) {

        List<Node> list = new ArrayList<>();
        List<Character> list_key_used = new ArrayList<>();

        for ( int i = 0 ; i < s.length() ; i++ ) {
            char c_s = s.charAt(i);
            char c_t = t.charAt(i);

            Node t_in_list = get_node_t(list, c_s);
            if ( t_in_list == null ) {
                if ( list_key_used.contains(c_t) ) {
                    return false;
                }

                Node node_to_add = new Node(c_s, c_t);
                list.add(node_to_add);
                list_key_used.add(c_t);
            }
            else {
                if ( t_in_list.char_in_t != c_t ) {
                    return false;
                }
            }
        }

        return true;
    }

    private Node get_node_t (List<Node> list, Character char_in_s) {
        for ( Node node : list ) {
            if ( node.char_in_s == char_in_s ) {
                return node;
            }
        }

        return null;
    }
}
