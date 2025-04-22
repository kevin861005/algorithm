package com.algorithm.hash;

public class hash_multiplication {
    private static long hash_multiplication (Long input_value) {
        long range_limit = 1000000007;
        double random_a = 0.618033;
        double fraction = input_value * random_a % 1;
        return (long) (range_limit * (fraction));
    }

    private static long mod (long n, long m) {
        return (n % m + m) % m;
    }

    public static void main (String[] args) {
        Long input_value = 1234567890L;
        long hashed_output = hash_multiplication(input_value);
        System.out.println("result(multiply): " + hashed_output);    // 760370021
    }
}
