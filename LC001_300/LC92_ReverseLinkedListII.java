package LC001_300;
import java.util.*;
public class LC92_ReverseLinkedListII {
    /**
     * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes
     * of the list from position left to position right, and return the reversed list.
     *
     * Input: head = [1,2,3,4,5], left = 2, right = 4
     * Output: [1,4,3,2,5]
     *
     * Constraints:
     *
     * The number of nodes in the list is n.
     * 1 <= n <= 500
     * -500 <= Node.val <= 500
     * 1 <= left <= right <= n
     * @param head
     * @param left
     * @param right
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        int m = left, n = right;
        ListNode a = dummy;
        for (int i = 0; i < m - 1; i++) a = a.next;
        ListNode b = a.next, c = b.next;
        for (int i = 0; i < n - m; i++) {
            ListNode t = c.next;
            c.next = b;
            b = c;
            c = t;
        }
        a.next.next = c;
        a.next = b;
        return dummy.next;
    }

    // S2
    // time = O(n), space = O(1)
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode cur = dummy;
        for (int i = 0; i < right; i++) {
            cur = cur.next;
        }
        ListNode c = cur.next;
        cur.next = null;

        cur = dummy;
        for (int i = 0; i < left - 1; i++) {
            cur = cur.next;
        }
        ListNode b = cur.next;
        cur.next = null;

        b = reverseLinkedList(b);

        cur = dummy;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = b;
        while (cur.next != null) cur = cur.next;
        cur.next = c;

        return dummy.next;
    }

    private ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode cur = head, prev = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
/**
 * 前面加一个dummy node, 每次都从dummy出发
 * 把原先的链表分割成3部分
 * one-pass
 * 记录两个节点
 * 处理逆序区也要有左右节点指针
 * 同时维护三个指针
 */
