package LC601_900;
import java.util.*;
public class LC791_CustomSortString {
    /**
     * order and str are strings composed of lowercase letters. In order, no letter occurs more than once.
     *
     * order was sorted in some custom order previously. We want to permute the characters of str so that they match
     * the order that order was sorted. More specifically, if x occurs before y in order, then x should occur before y
     * in the returned string.
     *
     * Return any permutation of str (as a string) that satisfies this property.
     *
     * Example:
     * Input:
     * order = "cba"
     * str = "abcd"
     * Output: "cbad"
     *
     * Note:
     *
     * order has length at most 26, and no character is repeated in order.
     * str has length at most 200.
     * order and str consist of lowercase letters only.
     * @param order
     * @param str
     * @return
     */
    // S1: PQ
    // time = O(nlogn), space = O(n)
    public String customSortString(String order, String str) {
        PriorityQueue<Character> pq = new PriorityQueue<>((o1, o2) -> order.indexOf(o1 + "") - order.indexOf(o2 + ""));
        for (char c : str.toCharArray()) pq.offer(c);
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) sb.append(pq.poll());
        return sb.toString();
    }

    // S2: count
    // time = O(m + n), space = O(n)
    public String customSortString2(String order, String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;

        StringBuilder sb = new StringBuilder();
        for (char c : order.toCharArray()) {
            if (cnt[c - 'a'] > 0) {
                while (cnt[c - 'a'] > 0) {
                    sb.append(c);
                    cnt[c - 'a']--;
                }
            }
        }

        for (char c : s.toCharArray()) {
            if (cnt[c - 'a'] > 0) sb.append(c);
        }
        return sb.toString();
    }
}
