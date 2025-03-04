package LC2101_2400;
import java.util.*;
public class LC2181_MergeNodesinBetweenZeros {
    /**
     * You are given the head of a linked list, which contains a series of integers separated by 0's. The beginning and
     * end of the linked list will have Node.val == 0.
     *
     * For every two consecutive 0's, merge all the nodes lying in between them into a single node whose value is the
     * sum of all the merged nodes. The modified list should not contain any 0's.
     *
     * Return the head of the modified linked list.
     *
     * Input: head = [0,3,1,0,4,5,2,0]
     * Output: [4,11]
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [3, 2 * 10^5].
     * 0 <= Node.val <= 1000
     * There are no two consecutive nodes with Node.val == 0.
     * The beginning and end of the linked list have Node.val == 0.
     * @param head
     * @return
     */
    // S1: dfs (optimal solution)
    // time = O(n), space = O(1)
    public ListNode mergeNodes(ListNode head) {
        // corner case
        if (head == null || head.next == null) return null;

        ListNode node = new ListNode(0);
        ListNode cur = head.next;

        while (cur.val > 0) {
            node.val += cur.val;
            cur = cur.next;
        }
        node.next = mergeNodes(cur);
        return node;
    }

    // S2: iteration
    // time = O(n), space = O(n)
    public ListNode mergeNodes2(ListNode head) {
        ListNode dummy = new ListNode(0), cur = dummy;
        int v = -1;
        for (ListNode p = head; p != null; p = p.next) {
            if (p.val == 0) {
                if (v != -1) {
                    cur = cur.next = new ListNode(v);
                }
                v = 0;
            } else v += p.val;
        }
        return dummy.next;
    }
}