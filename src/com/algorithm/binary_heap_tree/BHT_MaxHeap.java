package com.algorithm.binary_heap_tree;

/**
 * 實作 BHT - Max Heap
 */
public class BHT_MaxHeap {

    private int[] binary_heap_tree;

    private int i_array_end;

    public BHT_MaxHeap(int[] nums) {
        this.binary_heap_tree = nums;
        this.i_array_end = nums.length - 1;
    }

    // BigO = n
    public void build_heap () {
        // BFS - from bottom to top
        int i = this.i_array_end;
        while ( true ) {
            if ( i < 0 ) break;

            shift_down(i);

            i --;
        }
    }

    // BigO = log(n)
    private void shift_down (int i) {
        if ( i > this.i_array_end ) return;

        // pick the bigger child node - binary tree concept, but only pick one node to go
        Integer i_bigger = get_i_bigger_node(i);

        if ( i_bigger == null ) return;

        if ( this.binary_heap_tree[i] < this.binary_heap_tree[i_bigger] ) {
            swap(i, i_bigger);

            shift_down(i_bigger);
        }
    }

    private void swap (int i, int j) {
        int temp = this.binary_heap_tree[i];
        this.binary_heap_tree[i] = this.binary_heap_tree[j];
        this.binary_heap_tree[j] = temp;
    }

    private Integer get_i_bigger_node (int i) {
        Integer i_bigger = null;

        int i_left = (i + 1) * 2 - 1;
        int i_right = (i + 1) * 2 - 1 + 1;

        if ( i_left <= this.i_array_end && i_right <= this.i_array_end ) {
            if ( this.binary_heap_tree[i_left] >= this.binary_heap_tree[i_right] ) {
                i_bigger = i_left;
            }
            else {
                i_bigger = i_right;
            }
        }
        else if ( i_left <= this.i_array_end ) {
            i_bigger = i_left;
        }
        else if ( i_right <= this.i_array_end ) {
            i_bigger = i_right;
        }

        return i_bigger;
    }

    // BigO = log(n)
    public void remove_from_top () {
        // swap top node with last node
        swap(0, this.i_array_end);

        // pretend the last node is removed from the tree
        this.i_array_end --;

        // shift down the current top node
        shift_down(0);
    }

    // BigO = log(n)
    public void add_to_bottom (int num_to_add) {
        // extend inner storage if necessary
        if ( this.i_array_end + 1 > this.binary_heap_tree.length - 1 ) {
            extend_storage();
        }

        // add to the end of the tree
        i_array_end ++;
        this.binary_heap_tree[i_array_end] = num_to_add;

        // shift up
        shift_up(i_array_end);
    }

    private void extend_storage () {
        int[] new_binary_heap_tree = new int[this.binary_heap_tree.length * 2];
        for ( int i = 0 ; i < this.binary_heap_tree.length ; i ++ ) {
            new_binary_heap_tree[i] = this.binary_heap_tree[i];
        }

        this.binary_heap_tree = new_binary_heap_tree;
    }

    // BigO = log(n)
    private void shift_up (int i) {
        if ( i <= 0 ) return;

        int i_plus_one = i + 1;
        int i_parent = i_plus_one / 2 - 1;

        if ( this.binary_heap_tree[i] > this.binary_heap_tree[i_parent] ) {
            swap(i, i_parent);

            shift_up(i_parent);
        }
    }

    public static void main (String[] args) {
        int[] nums = {66, 78, 27, 35, 6, 2, 44, 58, 29, 76};

        BHT_MaxHeap bht = new BHT_MaxHeap(nums);
        bht.build_heap();
        System.out.println();

        bht.remove_from_top();
        System.out.println();

        bht.add_to_bottom(100);
        bht.add_to_bottom(120);
        System.out.println();
    }
}
