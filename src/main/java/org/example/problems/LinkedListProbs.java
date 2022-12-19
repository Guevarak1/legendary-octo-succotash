package org.example.problems;

import org.example.datastructures.ListNode;

import java.util.HashSet;

public class LinkedListProbs {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))));
        ListNode head2 = new ListNode(4, new ListNode(5, new ListNode(6)));
//        ListNode midNode = new ListNode(99, null);
//        insertToMiddle(head, midNode);
//        traverse(head);
//        mergeTwoLists(head, head2);
//        reverseList(head);
        middleNode(head);
    }

    //**********************
    //EASIES
    //**********************

    //1 -> 2 - > 4 merge 1 -> 3 -> 4 =
    //1 -> 1 -> 2 -> 3 -> 4 -> 4
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //create new pointer for resulting linked list
        ListNode prehead = new ListNode(-1);
        ListNode cur = prehead;

        //while able to iterate through at least 1 lined list
        while (l1 != null && l2 != null) {
            //set curs.next to the lesser node and continue with lesser node
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            //continue with cur node
            cur = cur.next;
        }

        //by the end of the list, we will have leftover vals.
        // 1 linked list would have more to iterate through so we just set the cur.next to the greater values list
        cur.next = l1 == null ? l2 : l1;
        return prehead.next;
    }

    public static boolean hasCycle(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if (slowPointer == fastPointer) return true;
        }
        return false;
    }

    public static boolean hasCycleSet(ListNode head) {
        HashSet<ListNode> nodes = new HashSet<>();
        while (head != null) {
            if (nodes.contains(head)) return true;
            else {
                nodes.add(head);
                head = head.next;
            }
        }
        return false;
    }

    //https://www.youtube.com/watch?v=G0_I-ZF0S38&ab_channel=NeetCode
    //Space O(1) Time O(n)
    //Two pointer iterative solution.  4 steps.
    //previous is the reverse linked lists new head.
    public static ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode next;
        ListNode prev = null;
        while (curr != null) {
            //1. next should save curr.next before moving forward.
            next = curr.next;
            //2. current.next should point back to previous. first case is null
            curr.next = prev;
            //3. previous can be shifted to current
            prev = curr;
            //4. current can be shifted to next
            curr = next;
        }
        return prev;
    }

    public static ListNode middleNode(ListNode head) {
        ListNode curr = head;
        int counter = 0;
        while (curr != null) {
            counter++;
            curr = curr.next;
        }

        curr = head;
        for (int i = 0; i < counter / 2; i++) {
            curr = curr.next;
        }

        return curr;
    }

    //**********************
    //MEDIUMS
    //**********************

}
