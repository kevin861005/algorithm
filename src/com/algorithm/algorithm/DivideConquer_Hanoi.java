package com.algorithm.algorithm;

import java.util.Stack;

/**
 * 分治法實作
 * 利用河內塔概念
 */
public class DivideConquer_Hanoi {
    static Stack<Integer> pillar_left = new Stack<>();
    static Stack<Integer> pillar_middle = new Stack<>();
    static Stack<Integer> pillar_right = new Stack<>();

    public static void main (String[] args) {
        pillar_left.add(5);
        pillar_left.add(4);
        pillar_left.add(3);
        pillar_left.add(2);
        pillar_left.add(1);

        int layer = pillar_left.size();

        hanoi(layer, pillar_left, pillar_middle, pillar_right);

        System.out.println();
    }

    private static void hanoi (int layer, Stack<Integer> pillar_from, Stack<Integer> pillar_middle, Stack<Integer> pillar_to) {
        if ( layer == 0 ) return;

        // base case: when layer == 1, it's our base case

        // step1: move the above plate to the pillar_middle, to clear the room for the plate below
        hanoi(layer - 1, pillar_from, pillar_to, pillar_middle);

        // step2: main target
        int plate = pillar_from.pop();
        pillar_to.push(plate);

        // step3: move the original above plate back
        hanoi(layer - 1, pillar_middle, pillar_from, pillar_to);

    }
}
