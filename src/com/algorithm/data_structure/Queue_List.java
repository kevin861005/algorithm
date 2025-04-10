package com.algorithm.data_structure;

/**
 * Queue 實作，底層使用 List
 */
public class Queue_List {

    static class Node {
        Integer value;

        Node next;

        public Node (Integer value) {
            this.value = value;
        }
    }

    Integer[] nums;
    Node node_begin = null;
    Node node_end = null;

    public Queue_List (Integer[] nums) {
        this.nums = nums;
    }

    public void build_queue () {
        for ( int i = 0 ; i < nums.length ; i++ ) {
            offer(nums[i]);
        }
    }

    public void offer (Integer value) {
        if ( node_begin == null ) {
            node_begin = new Node(value);
            node_end = node_begin;
        }
        else {
            Node node_new = new Node(value);

            node_end.next = node_new;
            this.node_end = node_new;
        }
    }

    public Integer poll () {
        if ( node_begin == null ) {
            return null;
        }

        Node node = node_begin;
        node_begin = node_begin.next;

        if ( node_begin == null ) {
            node_end = null;
        }

        return node.value;
    }

    public static void main (String[] args) {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};

        Queue_List queue_list = new Queue_List(nums);
        queue_list.build_queue();

        // full - extend the storage size
        queue_list.offer(6);

        Integer num = queue_list.poll();
        num = queue_list.poll();
        num = queue_list.poll();
        num = queue_list.poll();
        num = queue_list.poll();
        num = queue_list.poll();

        // empty
        num = queue_list.poll();

        System.out.println();
    }
}
