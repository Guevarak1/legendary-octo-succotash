package org.example.datastructures;

public class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static void traverse(ListNode l1) {
        while (l1 != null) {
            System.out.println(l1.val);
            l1 = l1.next;
        }
    }

    public static void addToTail(ListNode list, ListNode node) {
        ListNode current = list;
        while (current.next != null) {
            current = current.next;
        }
        current.next = node;
    }

    public static void insertToMiddle(ListNode head, ListNode node) {
        int size = 0;
        ListNode curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }

        curr = head;

        //if size is even, count up to size/2 if odd, need to go to the next index then divide by 2
        int count = (size % 2 == 0) ? (size / 2) : (size + 1) / 2;
        for (int i = 1; i < count; i++) {
            curr = curr.next;
        }
        //curr is now in the middle

        //take the second half of the list and set the next to the inserted node.next
        node.next = curr.next;
        //take current.next node and set it in the middle
        curr.next = node;
    }
}
