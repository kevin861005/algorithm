package com.algorithm.data_structure;

/**
 * Queue 實作，底層使用 Array
 */
public class Queue_Array {
    Integer[] array;
    Integer[] queue;
    Integer i_begin = null;
    Integer i_end = null;

    public Queue_Array (Integer[] array) {
        this.array = array;
    }

    public void build_queue () {
        this.queue = new Integer[this.array.length];

        for ( int i = 0 ; i < array.length ; i++ ) {
            offer(array[i]);
        }
    }

    private void offer (Integer value) {
        if ( size() == queue.length ) {
            expand_space();
        }

        if ( size() == 0 ) {
            i_begin = 0;
            i_end = 0;
        }
        else {
            i_end ++;
            i_end = i_end % queue.length;
        }

        queue[i_end] = value;
    }

    private void expand_space () {
        Integer[] queue_new = new Integer[queue.length * 2];

        int i = 0;
        while ( true ) {
            if ( size() == 0 ) break;

            Integer value = poll();
            queue_new[i] = value;

            i ++;
        }

        this.i_begin = 0;
        this.i_end = i - 1;
        this.queue = queue_new;
    }

    private int size() {
        if ( i_begin == null && i_end == null ) {
            return 0;
        }
        else if ( i_end >= i_begin ) {
            return (i_end - i_begin) + 1;
        }
        else if ( i_end < i_begin ) {
            return (queue.length - i_begin) + i_end + 1;
        }

        return -1;
    }

    public Integer poll () {
        if ( size() == 0 ) {
            return null;
        }

        Integer value = queue[i_begin];
        queue[i_begin] = null;

        if ( size() == 1 ) {
            i_begin = null;
            i_end = null;
        }
        else {  // circular array
            i_begin ++;
            i_begin = i_begin % queue.length;
        }

        return value;
    }

    public static void main (String[] args) {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};

        Queue_Array queue_array = new Queue_Array(nums);
        queue_array.build_queue();

        // full - extend the storage size
        queue_array.offer(6);

        Integer num = queue_array.poll();
        num = queue_array.poll();
        num = queue_array.poll();
        num = queue_array.poll();
        num = queue_array.poll();
        num = queue_array.poll();

        // empty
        num = queue_array.poll();

        // circular queue - i_end test
        queue_array.offer(11);
        queue_array.offer(12);
        queue_array.offer(13);
        queue_array.offer(14);
        queue_array.offer(15);

        num = queue_array.poll();
        num = queue_array.poll();
        num = queue_array.poll();
        num = queue_array.poll();

        for ( int i = 0 ; i < 80 ; i++ ) {
            queue_array.offer(30 + i);
        }


        System.out.println();
    }
}
