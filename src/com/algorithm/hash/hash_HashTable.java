package com.algorithm.hash;


/**
 * 解決 directAccessTable 使用過量空間問題
 * 尚未解決 collision 問題
 */
public class hash_HashTable {

    private int space_limit = 10;

    private Integer[] nums;
    private Integer[] hash_table;

    public hash_HashTable (Integer[] nums) {
        this.nums = nums;
    }

    public void build_hash_table () {
        this.hash_table = new Integer[space_limit];

        for ( int i = 0 ; i < nums.length ; i++ ) {
            add(nums[i]);
        }
    }

    private void add (int value) {
        int index = hash(value);
        this.hash_table[index] = value;
    }

    private int hash (int num) {
        return mod(num, space_limit);
    }

    private int mod (int n, int m) {
        return (n % m + m) % m;
    }

    // find by index
    private void hash_search (int target_num) {
        int index = hash(target_num);
        if ( this.hash_table[index] == target_num ) {
            System.out.println("found(hash): " + target_num);
        }
    }

    public static void main (String[] args) {
        Integer[] nums = {78, 6, 80, 73, 27, 61, 35, 44, 29, 2};

        hash_HashTable hash_hashTable = new hash_HashTable(nums);
        hash_hashTable.build_hash_table();

        int target_num = 29;
        hash_hashTable.hash_search(target_num);

        System.out.println();
    }
}
