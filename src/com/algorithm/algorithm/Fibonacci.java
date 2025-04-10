package com.algorithm.algorithm;

/**
 * 費氏數列（遞迴）
 * 0 1 1 2 3 5 8 13 ...
 * 除了第一個跟第二個數字外，後面數字都是前兩個數字的相加
 */
public class Fibonacci {

    public static Integer fibonacci (int n) {
        if ( n == 0 ) return 0;

        if ( n == 1 ) return 1;

        return fibonacci(n - 2) + fibonacci( n - 1);
    }

    public static void main (String[] args) {
        for ( int i = 0 ; i < 20 ; i++ ) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}
