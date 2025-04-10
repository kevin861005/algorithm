package com.algorithm.data_structure;

/**
 * Linked List 底層實作
 * 本身沒有提供 index
 */
public class List_Impl {

    static class Node {
        Integer value;
        Node next;

        public Node (int value) {
            this.value = value;
        }
    }

    private Node start;
    private Node end;

    public List_Impl () {}

    public void add (int value) {
        if ( start == null ) {
            start = new Node(value);

            end = start;
        }
        else {
            end.next = new Node(value);

            end = end.next;
        }
    }

    public Integer search (int value) {
        if ( start == null ) return null;

        Node node = start;
        while ( true ) {
            if ( node == null ) break;  // 代表跑到最後一個

            if ( node.value == value ) {
                return node.value;
            }

            node = node.next;
        }

        return null;
    }

    public void remove (int value) {
        Node node = start;
        Node node_target = null;
        Node node_prev = null;

        while ( true ) {
            if ( node == null ) break;

            if ( node.value == value ) {
                node_target = node;

                break;
            }

            node_prev = node;
            node = node.next;
        }

        if ( node_target == null ) return;

        if ( node_target == start ) {
            start = start.next;
        }
        else if ( node_target == end ) {
            node_prev.next = null;

            end = node_prev;
        }
        else {
            node_prev.next = node_target.next;
        }
    }

    public static void main (String[] args) {

        // initialize
        List_Impl myList = new List_Impl();

        // add O(1) - start
        myList.add(9);

        // add O(1)
        myList.add(11);
        myList.add(2);
        myList.add(98);
        myList.add(35);

        // search O(n)
        int value = myList.search(98);

        // remove O(n)
        myList.remove(2);
    }
}
