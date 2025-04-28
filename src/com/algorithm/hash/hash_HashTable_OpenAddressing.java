package com.algorithm.hash;

public class hash_HashTable_OpenAddressing {
    static Integer tombstone = Integer.MIN_VALUE;
    private Integer space_limit = 10;
    private Integer space_used = 0;
    private Integer[] nums;
    private Integer[] hash_table;

    public hash_HashTable_OpenAddressing (Integer[] nums) {
        this.nums = nums;
    }

    public void build_hash_table () {
        this.hash_table = new Integer[space_limit];

        for ( int i = 0 ; i < nums.length ; i++ ) {
            add(nums[i]);
        }
    }

    private void add (Integer value) {
        double ration = (double) this.space_used / (double) this.space_limit;
        if ( ration >= 0.7 ) {
            expand_and_rebuild();
        }

        add_helper(value);
    }

    private void add_helper (Integer value) {
        int index = hash(value);

        int count = 0;
        while (true) {
            if ( count == this.space_limit ) break;

            if ( this.hash_table[index] == null ) {
                this.hash_table[index] = value;
                this.space_used++;
                break;
            }

            index++;
            index %= this.space_limit;
            count++;
        }
    }

    private void expand_and_rebuild () {
        Integer[] hash_table_temp = new Integer[this.space_limit];
        for ( int i = 0 ; i < this.space_limit ; i++ ) {
            hash_table_temp[i] = this.hash_table[i];
        }

        space_limit *= 2;
        this.hash_table = new Integer[space_limit];
        this.space_used = 0;

        for ( int i = 0 ; i < hash_table_temp.length ; i++ ) {
            Integer value = hash_table_temp[i];

            if ( value == null ) continue;
            if ( value == tombstone ) continue;

            add_helper(value);
        }
    }

    // search by index
    private void hash_search (int target_num) {
        int index = hash(target_num);

        int count = 0;
        while (true) {
            if ( count == this.space_limit ) break;

            if ( this.hash_table[index] == null ) {
                break;
            }

            if ( this.hash_table[index] == target_num ) {
                System.out.println("found(hash): " + target_num);
            }

            index += 1;
            index %= this.space_limit;
            count++;
        }
    }

    private void remove (int target_num) {
        int index = hash(target_num);

        int count = 0;
        while (true) {
            if ( count == this.space_limit ) break;

            if ( this.hash_table[index] == null ) {
                break;
            }

            if ( this.hash_table[index] == target_num ) {
                this.hash_table[index] = tombstone;
                break;
            }

            index += 1;
            index %= this.space_limit;
            count++;
        }
    }

    private int hash (int num) {
        return mod(num, space_limit);
    }

    private int mod (int n, int m) {
        return (n % m + m) % m;
    }

    public static void main (String[] args) {
        Integer[] nums = {70, 61, 81, 6, 26, 47, 29};

        hash_HashTable_OpenAddressing hash_hashTable_openAddressing = new hash_HashTable_OpenAddressing(nums);
        hash_hashTable_openAddressing.build_hash_table();

        int target_num = 47;
        hash_hashTable_openAddressing.hash_search(target_num);

        target_num = 26;
        hash_hashTable_openAddressing.remove(target_num);

        target_num = 47;
        hash_hashTable_openAddressing.hash_search(target_num);

        hash_hashTable_openAddressing.add(55);

        System.out.println();
    }
}
