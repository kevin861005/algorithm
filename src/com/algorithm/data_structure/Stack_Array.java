package com.algorithm.data_structure;

/**
 * Stack 實作，底層使用 Array
 */
public class Stack_Array {
    Integer[] array;
    Integer[] stack;
    Integer i_top = null;  // 實作 LIFO 最主要觀念

    public Stack_Array (Integer[] array) {
        this.array = array;
    }

    public void build_stack () {
        this.stack = new Integer[this.array.length];

        for ( int i = 0 ; i < this.array.length ; i++ ) {
            push(array[i]);
        }
    }

    private void push (Integer value) {
        if ( size() == stack.length ) {
            expand_space();
        }

        if ( size() == 0 ) {
            i_top = 0;
        }
        else {
            i_top ++;
        }

        stack[i_top] = value;
    }

    private void expand_space () {
        Integer[] stack_new = new Integer[stack.length * 2];

        for ( int i = 0 ; i < stack.length ; i++ ) {
            stack_new[i] = stack[i];
        }

        this.stack = stack_new;
    }

    private int size() {
        if ( i_top == null ) return 0;

        return i_top + 1;
    }

    public Integer pop () {
        if ( size() == 0 ) {
            return null;
        }

        Integer value = stack[i_top];
        stack[i_top] = null;

        if ( size() == 1 ) {
            i_top = null;
        }
        else {
            i_top --;
        }

        return value;
    }

    public static void main (String[] args) {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};

        Stack_Array stack_array = new Stack_Array(nums);
        stack_array.build_stack();

        // full - extend the storage size
        stack_array.push(6);

        Integer num1 = stack_array.pop();
        Integer num2 = stack_array.pop();
        Integer num3 = stack_array.pop();
        Integer num4 = stack_array.pop();
        Integer num5 = stack_array.pop();
        Integer num6 = stack_array.pop();

        // empty
        Integer empty = stack_array.pop();

        System.out.println();

    }
}
