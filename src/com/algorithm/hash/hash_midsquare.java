package com.algorithm.hash;

public class hash_midsquare {
    private static long hash_midsquare (Long input_value) {
        // ___XXX___ // pick 4th~6th numbers (from right to left)
        Long input_value_squared = input_value * input_value;
        Long hashed_output = input_value_squared % 1000000; // 去頭
        hashed_output = hashed_output / 1000; // 去尾

        return hashed_output;
    }

    private static long mod (long n, long m) {
        return (n % m + m) % m;
    }

    public static void main (String[] args) {
        Long input_value = 1234567890L;
        long hashed_output = hash_midsquare(input_value);
        System.out.println("result(mid_square): " + hashed_output);  // 52 (out of 15241578750190"52"100)
    }
}
