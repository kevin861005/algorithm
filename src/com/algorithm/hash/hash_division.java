package com.algorithm.hash;

public class hash_division {
    private static long hash_division (Long input_value) {
        int modulo_number = 1000000007;
        return mod(input_value, modulo_number);
    }

    private static long mod (long n, long m) {
        return (n % m + m) % m;
    }

    public static void main (String[] args) {
        Long input_value = 1234567890L;
        long hashed_output = hash_division(input_value);
        System.out.println("result(division): " + hashed_output);    // 104371024
    }
}
