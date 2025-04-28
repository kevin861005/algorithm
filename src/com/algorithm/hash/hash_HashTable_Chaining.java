package com.algorithm.hash;


import java.util.LinkedList;
import java.util.List;

/**
 * 透過 Chaining 解決 collision 問題
 */
public class hash_HashTable_Chaining {

    static class Chaining {
        public List<Integer> list = new LinkedList<>();
    }

    private int space_limit = 10;

    private Integer[] nums;
    private Chaining[] hash_table;

    public hash_HashTable_Chaining (Integer[] nums) {
        this.nums = nums;
    }

    public void build_hash_table () {
        this.hash_table = new Chaining[space_limit];

        for ( int i = 0 ; i < nums.length ; i++ ) {
            add(nums[i]);
        }
    }

    private void add (int value) {
        int index = hash(value);

        if ( this.hash_table[index] == null ) {
            this.hash_table[index] = new Chaining();
        }

        Chaining chaining = this.hash_table[index];

        if ( ! chaining.list.contains(value) ) {
            chaining.list.add(value);
        }
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

        Chaining chaining = this.hash_table[index];
        if ( chaining == null ) {
            return;
        }

        if ( chaining.list.contains(target_num) ) {
            System.out.println("found(hash): " + target_num);
        }
    }

    private void remove (int target_num) {
        int index = hash(target_num);

        Chaining chaining = this.hash_table[index];
        if ( chaining == null ) {
            return;
        }

        if ( chaining.list.contains(target_num) ) {
            int index_in_list = chaining.list.indexOf(target_num);

            chaining.list.remove(index_in_list);
        }
    }

    public static void main (String[] args) {
        Integer[] nums = {
                78,
                6,
                80,
                73,
                27,
                61,
                35,
                44,
                29,
                2,
                108,
                106,
                100,
                103,
                117,
                111,
                115,
                124,
                129,
                339
        };

        hash_HashTable_Chaining hash_hashTable_chaining = new hash_HashTable_Chaining(nums);
        hash_hashTable_chaining.build_hash_table();

        int target_num = 29;
        hash_hashTable_chaining.hash_search(target_num);

        // remove
        target_num = 339;
        hash_hashTable_chaining.remove(target_num);
        hash_hashTable_chaining.hash_search(target_num);

        System.out.println();
    }
}
