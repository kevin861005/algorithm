package com.algorithm.binary_search_tree;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class BST_23Tree_List {

    public static class Node_23Tree {
        Node_23Tree parent;
        LinkedList<Integer> keys = new LinkedList<>();  // nodes_max = 2
        LinkedList<Node_23Tree> children = new LinkedList<>();  // children_max = 3
    }

    private int[] nums;
    private Node_23Tree root;

    public BST_23Tree_List (int[] nums) {
        this.nums = nums;
    }

    public void build_tree () {
        for ( int i = 0 ; i < nums.length ; i++ ) {
            add(nums[i]);
        }
    }

    private void add (int num) {
        if ( root == null ) {
            root = new Node_23Tree();
            root.keys.add(num);
        }
        else {
            add_helper(root, num);
        }
    }

    private void add_helper (Node_23Tree node, int num_to_add) {
        if ( node.children.isEmpty() ) {
            // this is leaf, so good to add
            node.keys.add(num_to_add);
            Collections.sort(node.keys);

            // if the key is too many in a node, break and merge to parent
            if ( node.keys.size() == 3 ) {
                break_to_merge(node);
            }

            return;
        }

        if ( node.keys.size() == 1 ) {
            int key = node.keys.getFirst();
            // decide direction in node's children
            if ( num_to_add < key ) {
                add_helper(node.children.getFirst(), num_to_add);
            }
            else if ( num_to_add > key ) {
                add_helper(node.children.getLast(), num_to_add);
            }
            else {  // num_to_add == key
                // ignore same value
            }
        }
        else if ( node.keys.size() == 2 ) {
            int key_left = node.keys.getFirst();
            int key_right = node.keys.getLast();

            if ( num_to_add < key_left ) {
                add_helper(node.children.getFirst(), num_to_add);
            }
            else if ( num_to_add > key_left && num_to_add < key_right ) {
                add_helper(node.children.get(1), num_to_add);
            }
            else if ( num_to_add > key_right ) {
                add_helper(node.children.getLast(), num_to_add);
            }
        }

        return;
    }

    private void break_to_merge (Node_23Tree node) {
        // break: a [3 - key node] to [1 - key node + 2 children node]
        break_nodes(node);

        // merge with parent
        if ( node.parent != null ) {
            Node_23Tree node_parent_after_merge = merge_to_parent(node);

            if ( node_parent_after_merge.keys.size() == 3 ) {
                break_to_merge(node_parent_after_merge);
            }
        }
    }

    private Node_23Tree merge_to_parent (Node_23Tree node) {
        if ( node.parent == null ) return node;

        Node_23Tree node_parent = node.parent;
        // remove this child
        remove_child(node_parent, node);

        // merge to parent
        merge_to_parent_helper(node_parent, node);

        return node_parent;
    }

    private void merge_to_parent_helper (Node_23Tree node_parent, Node_23Tree node_to_merge) {
        // when we come here, the key size of node_to_merge will always be 1
        int key_to_merge = node_to_merge.keys.get(0);

        // determine position in node_parent's children list
        String position = null;
        if ( node_parent.keys.size() == 1 ) {
            int key = node_parent.keys.get(0);

            if ( key_to_merge < key ) {
                position = "left";
            }
            else if ( key_to_merge > key ) {
                position = "right";
            }
            else if ( key_to_merge == key ) {
                // ignore same value
            }
        }
        else if ( node_parent.keys.size() == 2 ) {
            int key_left = node_parent.keys.getFirst();
            int key_right = node_parent.keys.getLast();

            if ( key_to_merge < key_left ) {
                position = "left";
            }
            else if ( key_to_merge > key_left && key_to_merge < key_right ) {
                position = "middle";
            }
            else if ( key_to_merge > key_right ) {
                position = "right";
            }
        }

        // merge key
        node_parent.keys.add(key_to_merge);
        Collections.sort(node_parent.keys);

        // merge child
        // when we come here, the children size will always be 2
        node_to_merge.children.getFirst().parent = node_parent;
        node_to_merge.children.getLast().parent = node_parent;

        if ( "left".equals(position) ) {
            node_parent.children.addFirst(node_to_merge.children.getLast());
            node_parent.children.addFirst(node_to_merge.children.getFirst());
        }
        else if ( "middle".equals(position) ) {
            node_parent.children.add(1, node_to_merge.children.getLast());
            node_parent.children.add(1, node_to_merge.children.getFirst());
        }
        else if ( "right".equals(position) ) {
            node_parent.children.addLast(node_to_merge.children.getFirst());
            node_parent.children.addLast(node_to_merge.children.getLast());
        }
    }

    private void remove_child (Node_23Tree node_parent, Node_23Tree node) {
        for ( int i = 0 ; i < node_parent.children.size() ; i++ ) {
            Node_23Tree child = node_parent.children.get(i);

            if ( child == node ) {
                node_parent.children.remove(i);
                break;
            }
        }
    }

    private void break_nodes (Node_23Tree node) {
        Node_23Tree node_left = new Node_23Tree();
        Node_23Tree node_right = new Node_23Tree();

        node_left.parent = node;
        node_right.parent = node;

        node_left.keys.add(node.keys.getFirst());
        node_right.keys.add(node.keys.getLast());

        for ( int i = 0 ; i < node.children.size() ; i++ ) {
            Node_23Tree child_now = node.children.get(i);

            int child_now_key_max = child_now.keys.getLast();
            int key_left = node_left.keys.get(0);
            int key_right = node_right.keys.get(0);

            if ( child_now_key_max < key_left ) {
                child_now.parent = node_left;
                node_left.children.add(child_now);
            }
            else if ( child_now_key_max > key_left && child_now_key_max < key_right ) {
                if ( node_left.children.size() < 2 ) {
                    child_now.parent = node_left;
                    node_left.children.add(child_now);
                }
                else {
                    child_now.parent = node_right;
                    node_right.children.add(child_now);
                }
            }
            else if ( child_now_key_max > key_right ) {
                child_now.parent = node_right;
                node_right.children.add(child_now);
            }
        }

        node.children.clear();
        node.children.add(node_left);
        node.children.add(node_right);

        node.keys.removeFirst();
        node.keys.removeLast();
    }

    private Integer search (int target) {
        if ( root == null ) return null;

        return search_helper(root, target);
    }

    private Integer search_helper (Node_23Tree node, int target) {
        if ( node.keys.size() == 1 ) {
            int key = node.keys.get(0);

            if ( target == key ) {
                return key;
            }

            if ( node.children.isEmpty() ) {
                return null;
            }

            if ( target < key ) {
                return search_helper(node.children.getFirst(), target);
            }
            else if ( target > key ) {
                return search_helper(node.children.getLast(), target);
            }
        }
        else if ( node.keys.size() == 2 ) {
            int key_left = node.keys.getFirst();
            int key_right = node.keys.getLast();

            if ( target == key_left ) {
                return key_left;
            }
            else if ( target == key_right ) {
                return key_right;
            }

            if ( node.children.isEmpty() ) {
                return null;
            }

            if ( target < key_left ) {
                return search_helper(node.children.getFirst(), target);
            }
            else if ( target > key_left && target < key_right ) {
                return search_helper(node.children.get(1), target);
            }
            else if ( target > key_right ) {
                return search_helper(node.children.getLast(), target);
            }
        }

        return null;
    }

    public static void main (String[] args) {
        int[] nums = new int[]{39, 28, 17, 13, 11, 3, 4};

        BST_23Tree_List tree23 = new BST_23Tree_List(nums);
        tree23.build_tree();

        Integer target_num = null;
        target_num = tree23.search(17);
        target_num = tree23.search(100);

        System.out.println();
    }
}
