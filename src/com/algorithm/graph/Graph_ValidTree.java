package com.algorithm.graph;

import java.util.HashMap;
import java.util.Map;

public class Graph_ValidTree {

    // core data structure for union find
    Map<Integer, Integer> node2root = new HashMap<>();

    private boolean is_valid_tree (int num_of_nodes, int[][] edges) {

        if ( edges.length != (num_of_nodes - 1) ) return false; // means it's not a tree, if edges.length == num_of_nodes, means it's a cycle

        for ( int i = 0; i < edges.length; i++ ) {
            int[] edge = edges[i];

            int left = edge[0];
            int right = edge[1];

            add(left);
            add(right);

            if ( is_connected(left, right) ) return false;

            union(left, right);
        }

        return true;
    }

    private void union (Integer left, Integer right) {
        Integer root_left = find_root(left);
        Integer root_right = find_root(right);

        if ( root_left.equals(root_right) ) return;

        node2root.put(root_left, root_right);   // take right one's root as the common root
    }

    private Integer find_root (Integer node) {
        Integer root_now = node2root.get(node);
        if ( root_now.equals(node) ) return node;

        Integer root_final = find_root(root_now);
        node2root.put(node, root_final);

        return root_final;
    }

    private boolean is_connected (Integer left, Integer right) {
        Integer root_left = find_root(left);
        Integer root_right = find_root(right);

        if ( root_left.equals(root_right) ) return true;

        return false;
    }

    private void add (Integer node) {
        if ( node2root.containsKey(node) ) return;

        node2root.put(node, node);  // initialization: be it's own root
    }

    public static void main (String[] args) {

//        int[][] edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {0, 4}};    // ok case
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 3}};    // failed case

        int num_of_vertex = 5;
        Graph_ValidTree graph = new Graph_ValidTree();
        boolean is_valid_tree = graph.is_valid_tree(num_of_vertex, edges);
        System.out.println();
    }
}
