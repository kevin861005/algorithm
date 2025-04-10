package com.algorithm.data_structure;

/**
 * Stack 實作，底層使用 List
 */
public class Stack_List {

    static class Node {
        Integer value;

        Node next;

        public Node (Integer value) {
            this.value = value;
        }
    }

    Integer[] nums;
    Node node_top;

    public Stack_List (Integer[] nums) {
        this.nums = nums;
    }

    public void build_stack () {
        for ( int i = 0 ; i < this.nums.length ; i++ ) {
            push(nums[i]);
        }
    }

    public void push (Integer value) {
        if ( node_top == null ) {
            node_top = new Node(value);
        }
        else {
            // 越後面放進來的資料在越上面
            Node node_new = new Node(value);
            node_new.next = node_top;
            node_top = node_new;
        }
    }

    public Integer pop () {
        if ( node_top == null ) {
            return null;
        }

        // 取完資料要將下面的往上遞補
        Node node = node_top;
        node_top = node_top.next;

        return node.value;
    }

    public static void main (String[] args) {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};

        Stack_List stack_list = new Stack_List(nums);
        stack_list.build_stack();

        // full - extend the storage size
        stack_list.push(6);

        Integer num1 = stack_list.pop();
        Integer num2 = stack_list.pop();
        Integer num3 = stack_list.pop();
        Integer num4 = stack_list.pop();
        Integer num5 = stack_list.pop();
        Integer num6 = stack_list.pop();

        // empty
        Integer empty = stack_list.pop();

        // push + pop
        stack_list.push(11);
        stack_list.push(12);
        stack_list.push(13);

        Integer num7 = stack_list.pop();
        Integer num8 = stack_list.pop();

        stack_list.push(14);

        Integer num9 = stack_list.pop();

        System.out.println();

    }
}
