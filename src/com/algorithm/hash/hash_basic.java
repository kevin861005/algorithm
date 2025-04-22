package com.algorithm.hash;

public class hash_basic {
    private static long hash (String str) {
        int magic_number = 31; // you can pick other prime number
        int module_number = 1000000007;
        long hashedbucket = 0;
        for ( int i = 0 ; i < str.length() ; i++ ) {
            /** step01: move ALL left each round **/
            hashedbucket *= magic_number; // leverage multiplication to show different char position
            hashedbucket = mod(hashedbucket, module_number);
            /** step02: add the newly left-most char **/
            hashedbucket += str.charAt(i); // leverage ASCII chart to convert char into int
            hashedbucket = mod(hashedbucket, module_number);
            /** stepall: use % to categorize & avoid overflow in every step **/
        }

        return hashedbucket;
    }

    private static long mod (long n, long m) {
        return (n % m + m) % m;
    }

    public static void main (String[] args) {
        String str = "mygod";
        long hashed_mode_result = hash(str);
        System.out.println("result: " + hashed_mode_result); // 104371024
    }
}
