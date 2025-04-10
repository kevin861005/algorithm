package com.algorithm.data_structure;

/**
 * Array 底層實作
 * 有提供 index
 */
public class Array_Impl {
    private Integer[] array;
    private Integer i_end;

    public Array_Impl (int size) {
        array = new Integer[size];
        i_end = -1;
    }

    public void add_by_index (int i_add, int value) {
        if ( i_end + 1 == array.length ) expand_space();
        if ( i_add > i_end + 1 || i_add < 0 ) return;

        // move all value one slot after
        for ( int i = i_end ; i >= i_add ; i-- ) {
            array[i + 1] = array[i];
            array[i] = null;
        }

        array[i_add] = value;
        i_end ++;
    }

    public void add_by_value (int value) {
        add_by_index(i_end + 1, value);
    }

    private void expand_space () {
        Integer[] array_new = new Integer[array.length * 2];
        for ( int i = 0 ; i < array.length ; i++ ) {
            array_new[i] = array[i];
        }
        this.array = array_new;
    }

    public Integer search_by_index (int index) {
        if ( index > i_end || index < 0 ) return null;

        return array[index];
    }

    public Integer search_by_value (int value) {
        for ( int i = 0 ; i < array.length ; i++ ) {
            if ( array[i] == value ) {
                return array[i];
            }
        }

        return null;
    }

    public void remove_by_index (int index) {
        if ( index > i_end || index < 0 ) return;

        array[index] = null;

        // move everything after one slot ahead
        for ( int i = index + 1 ; i <= i_end ; i++ ) {
            array[i - 1] = array[i];

            array[i] = null;
        }

        i_end --;
    }

    public void remove_by_value (int value) {
        // search
        for ( int i = 0 ; i <= i_end ; i++ ) {
            if ( array[i] == value ) {
                remove_by_index(i);

                return;
            }
        }
    }

    public static void main (String[] args) {

        // initialize
        Array_Impl myArray = new Array_Impl(5);

        // add O(1)
        myArray.add_by_value(1);
        myArray.add_by_value(2);
        myArray.add_by_value(3);
        myArray.add_by_value(4);
        myArray.add_by_value(5);

        // add by value O(1) + expand O(n)
        myArray.add_by_value(6);

        // add by index O(n)
        int i_add = 1;
        myArray.add_by_index(i_add, 50);

        // search by value O(n)
        int value001 = myArray.search_by_value(100);

        // search by index O(1)
        int value002 = myArray.search_by_index(4);

        // remove by value O(n)
        myArray.remove_by_value(6);

        // remove by index O(n)
        myArray.remove_by_index(4);
    }
}
