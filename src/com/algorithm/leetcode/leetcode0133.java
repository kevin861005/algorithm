package com.algorithm.leetcode;

import java.util.*;

/**
 * Definition for a Node.
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 *     public Node() {
 *         val = 0;
 *         neighbors = new ArrayList<Node>();
 *     }
 *     public Node(int _val) {
 *         val = _val;
 *         neighbors = new ArrayList<Node>();
 *     }
 *     public Node(int _val, ArrayList<Node> _neighbors) {
 *         val = _val;
 *         neighbors = _neighbors;
 *     }
 * }
 * <br/>
 * <br/>
 * <a href="https://leetcode.com/problems/clone-graph/description/">clone-graph</a>
 */
public class leetcode0133 {

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    Map<Integer, Node> map = new HashMap<>();

    public Node cloneGraph (Node node) {
        if ( node == null ) return null;

        // DFS
        Set<Integer> visited = new HashSet<>();
        cloneGraphRecursion(node, visited);

        return get_cloned_node(node);
    }

    private void cloneGraphRecursion (Node node, Set<Integer> visited) {
        if ( visited.contains(node.val) ) return;
        visited.add(node.val);

        Node node_src_cloned = get_cloned_node(node);

        for ( int i = 0 ; i < node.neighbors.size() ; i++ ) {
            Node node_next = node.neighbors.get(i);

            Node node_dst_cloned = get_cloned_node(node_next);
            node_src_cloned.neighbors.add(node_dst_cloned);

            cloneGraphRecursion(node_next, visited);
        }
    }

    private Node get_cloned_node (Node node) {
        if ( map.containsKey(node.val) ) return map.get(node.val);

        map.put(node.val, new Node(node.val));
        return map.get(node.val);
    }
}
