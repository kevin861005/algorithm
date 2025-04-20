package com.algorithm.binary_heap_tree;

/**
 * 利用 BHT - Max Heap
 * 來實作 BHT_HeapSort
 * 透過 Max Heap 的 remove，來取得一個由小 -> 大 的 Array
 */
public class BHT_HeapSort {

    // BigO : n * log(n)
    public static void heap_sort_ascending (int[] nums) {
        // convert to BHT
        BHT_MaxHeap bht_max_heap = new BHT_MaxHeap(nums);
        bht_max_heap.build_heap();

        // remove n times
        int i = nums.length - 1;
        while ( true ) {
            if ( i < 0 ) break;

            bht_max_heap.remove_from_top();

            i --;
        }
    }

    public static void main (String[] args) {
        int[] nums = {66, 78, 27, 35, 6, 2, 44, 58, 29, 76};
        BHT_HeapSort.heap_sort_ascending(nums);
        System.out.println();
    }
}
