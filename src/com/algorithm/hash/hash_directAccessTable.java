package com.algorithm.hash;

/**
 * 實作 Hash 的 Direct Access Table
 * 最大特色就是 index = value
 */
public class hash_directAccessTable {
    private Integer[] nums;
    private Integer[] direct_access_table;

    public hash_directAccessTable (Integer[] nums) {
        this.nums = nums;
    }

    public void build_direct_access_table () {
        int max_element = get_max_element(this.nums);

        this.direct_access_table = new Integer[max_element + 1];

        for ( int i = 0 ; i < this.nums.length ; i++ ) {
            add(this.nums[i]);
        }
    }

    private int get_max_element (Integer[] nums) {
        int max = -1;

        for ( int i = 0 ; i < nums.length ; i++ ) {
            if ( max < nums[i] ) {
                max = nums[i];
            }
        }

        return max;
    }

    private void add (Integer num) {
        int index = num;
        int value = num;

        direct_access_table[index] = value;
    }

    // find by index
    public void direct_search (int target_num) {
        int index = target_num;

        if ( this.direct_access_table[index] == target_num ) {
            System.out.println("found(direct): " + target_num);
        }
    }

    public static void main (String[] args) {
        Integer[] nums = {78, 6, 80, 73, 27, 61, 35, 44, 29, 2};

        hash_directAccessTable hash_directAccessTable = new hash_directAccessTable(nums);
        hash_directAccessTable.build_direct_access_table();

        int target_num = 29;
        hash_directAccessTable.direct_search(target_num);

        System.out.println();
    }
}
