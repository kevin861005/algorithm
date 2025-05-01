package com.algorithm.leetcode;

import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * <br/>
 * <br/>
 * <a href="https://leetcode.com/problems/palindrome-linked-list/description/">palindrome-linked-list</a>
 */
public class leetcode0234 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // solution 1
//    public boolean isPalindrome (ListNode head) {
//        Stack<ListNode> stack = new Stack<>();
//        ListNode temp = head;
//        while ( temp != null ) {
//            stack.push(temp);
//            temp = temp.next;
//        }
//
//        temp = head;
//        while ( ! stack.isEmpty() ) {
//            if ( temp.val != stack.pop().val ) {
//                return false;
//            }
//
//            temp = temp.next;
//        }
//
//        return true;
//    }

    // solution 2
    ListNode pre = null;
    boolean is_p = true;
    public boolean isPalindrome (ListNode head) {
        pre = head;

        ListNode temp = head;

        isPalindrome_helper(temp);

        return is_p;
    }

    private void isPalindrome_helper (ListNode node) {
        if ( node == null ) return;

        isPalindrome_helper(node.next);

        if ( pre.val != node.val ) {
            is_p = false;
        }

        pre = pre.next;
    }
}
