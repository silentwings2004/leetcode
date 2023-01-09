package LC2401_2700;

public class LC2487_RemoveNodesFromLinkedList {
    /**
     * You are given the head of a linked list.
     *
     * Remove every node which has a node with a strictly greater value anywhere to the right side of it.
     *
     * Return the head of the modified linked list.
     *
     * Input: head = [5,2,13,3,8]
     * Output: [13,8]
     *
     * Input: head = [1,1,1,1]
     * Output: [1,1,1,1]
     *
     * Constraints:
     *
     * The number of the nodes in the given list is in the range [1, 10^5].
     * 1 <= Node.val <= 10^5
     * @param head
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 100010;
    public ListNode removeNodes(ListNode head) {
        int[] q = new int[N];
        int tt = 0;

        ListNode cur = head;
        for (ListNode p = cur; p != null; p = p.next) {
            while (tt > 0 && q[tt] < p.val) tt--;
            q[++tt] = p.val;
        }

        ListNode dummy = new ListNode(0);
        cur = dummy;
        int hh = 1;
        for (ListNode p = head; p != null && hh <= tt; p = p.next) {
            if (p.val != q[hh]) continue;
            else {
                cur.next = p;
                cur = cur.next;
                hh++;
            }
        }
        cur.next = null;
        return dummy.next;
    }
}
