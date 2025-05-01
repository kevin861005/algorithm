package com.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a Node.
 * class Node {
 *     int val;
 *     Node next;
 *     Node random;
 *     public Node(int val) {
 *         this.val = val;
 *         this.next = null;
 *         this.random = null;
 *     }
 * }
 * <br/>
 * <br/>
 * <a href="https://leetcode.com/problems/copy-list-with-random-pointer/description/">copy-list-with-random-pointer</a>
 */
public class leetcode0138 {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {

        Map<Node, Node> map = new HashMap<>();

        // prepare new Node instances
        Node node_old = head;
        while ( true ) {
            if ( node_old == null ) break;

            Node node_new = new Node(node_old.val);
            map.put(node_old, node_new);

            node_old = node_old.next;
        }

        // link our new Node instances - both linear and random
        Node node_dummy = new Node(-1);
        Node node_new = node_dummy;
        node_old = head;

        while (true) {
            if ( node_old == null ) break;

            node_new.next = map.get(node_old);
            node_new.next.random = map.get(node_old.random);

            node_new = node_new.next;
            node_old = node_old.next;
        }

        return node_dummy.next;
    }

    // 輸出鍊結串列（含 random）
    private static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            String randomVal = (curr.random != null) ? String.valueOf(curr.random.val) : "null";
            System.out.println("Node@" + System.identityHashCode(curr) +
                               " val=" + curr.val + ", random=" + randomVal +
                               " (random@=" + (curr.random == null ? "null" : System.identityHashCode(curr.random)) + ")");
            curr = curr.next;
        }
    }

    public static void main (String[] args) {
        leetcode0138 solver = new leetcode0138();

        // 建立測試節點 A->B->C
        Node A = new Node(1);
        Node B = new Node(2);
        Node C = new Node(3);

        A.next = B;
        B.next = C;

        A.random = C; // A 的 random 指向 C
        B.random = A; // B 的 random 指向 A
        C.random = B; // C 的 random 指向 B

        System.out.println("原始鍊結串列:");
        printList(A);

        Node copied = solver.copyRandomList(A);

        System.out.println("\n拷貝後鍊結串列:");
        printList(copied);

        System.out.println("\n 節點是否為不同實體:");
        System.out.println("A == copied ? " + (A == copied));                 // false
        System.out.println("A.next == copied.next ? " + (A.next == copied.next)); // false
        System.out.println("A.random == copied.random ? " + (A.random == copied.random)); // false

        System.out.println();
    }
}
