package com.algorithm.leetcode;

import java.util.List;

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
 * <a href="https://leetcode.com/problems/add-two-numbers/description/">add-two-numbers</a>
 */
public class leetcode0002 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers (ListNode l1, ListNode l2) {
        String s1 = add_helper(l1);
        String s2 = add_helper(l2);

        ListNode answer = new ListNode(-1);
        ListNode temp = answer;

        int carry = 0;
        int i_s1 = s1.length() - 1;
        int i_s2 = s2.length() - 1;

        while ( true ) {
            if ( i_s1 < 0 && i_s2 < 0 ) break;

            int sum = -1;
            if ( i_s1 >= 0 && i_s2 >= 0 ) {
                char c1 = s1.charAt(i_s1);
                char c2 = s2.charAt(i_s2);

                sum = (c1 - '0') + (c2 - '0') + carry;
            }
            else if ( i_s1 >= 0 ) {
                char c1 = s1.charAt(i_s1);

                sum = (c1 - '0') + carry;
            }
            else if ( i_s2 >= 0 ) {
                char c2 = s2.charAt(i_s2);

                sum = (c2 - '0') + carry;
            }

            if ( sum >= 10 ) {
                carry = 1;
            }
            else {
                carry = 0;
            }

            int digit = sum % 10;

            temp.next = new ListNode(digit);
            temp = temp.next;

            i_s1 --;
            i_s2 --;
        }

        if ( carry == 1 ) {
            temp.next = new ListNode(1);
            temp = temp.next;
        }

        return answer.next;
    }

    private String add_helper (ListNode listNode) {
        if ( listNode == null ) return "";

        String previous = add_helper(listNode.next);

        return previous + listNode.val;
    }

    // 工具方法：建立 ListNode 由陣列
    private static ListNode createList(int[] nums) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (int num : nums) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }
        return dummy.next;
    }

    // 工具方法：輸出 ListNode 內容
    private static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) System.out.print(" -> ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        leetcode0002 solver = new leetcode0002();

        // 測試資料：342 + 465 = 807
        // ListNode 格式：2 -> 4 -> 3 表示 342
        ListNode l1 = createList(new int[]{2, 4, 3});
        ListNode l2 = createList(new int[]{5, 6, 4});

        ListNode result = solver.addTwoNumbers(l1, l2);

        System.out.print("輸出結果: ");
        printList(result); // 預期輸出：7 -> 0 -> 8
    }
}
