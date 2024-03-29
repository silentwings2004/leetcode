package LC601_900;
import java.util.*;
public class LC821_ShortestDistancetoaCharacter {
    /**
     * Given a string s and a character c that occurs in s, return an array of integers answer where
     * answer.length == s.length and answer[i] is the shortest distance from s[i] to the character c in s.
     *
     * Input: s = "loveleetcode", c = "e"
     * Output: [3,2,1,0,1,0,0,1,2,2,1,0]
     *
     * Input: s = "aaab", c = "b"
     * Output: [3,2,1,0]
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^4
     * s[i] and c are lowercase English letters.
     * c occurs at least once in s.
     *
     * @param s
     * @param c
     * @return
     */
    // S1: Min Array
    // time = O(n), space = O(1)
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] res = new int[n];
        Arrays.fill(res, Integer.MAX_VALUE);
        for (int i = 0, j = -1; i < n; i++) {
            if (s.charAt(i) == c) j = i;
            if (j != -1) res[i] = i - j;
        }

        for (int i = n - 1, j = -1; i >= 0; i--) {
            if (s.charAt(i) == c) j = i;
            if (j != -1) res[i] = Math.min(res[i], j - i);
        }
        return res;
    }

    // S2: TreeSet
    // time = O(nlogn), space = O(n)
    public int[] shortestToChar2(String s, char c) {
        // corner case
        if (s == null || s.length() == 0) return new int[0];

        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < s.length(); i++) { // O(n)
            if (s.charAt(i) == c) treeSet.add(i); // O(logn)
        }

        int[] res = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            Integer fk = treeSet.floor(i);
            Integer ck = treeSet.ceiling(i);
            if (fk == null) res[i] = ck - i;
            else if (ck == null) res[i] = i - fk;
            else res[i]= Math.min(i - fk, ck - i);
        }
        return res;
    }
}

/**
 * When going left to right, we'll remember the index prev of the last character C we've seen. Then the answer is
 * i - prev.
 *
 * When going right to left, we'll remember the index prev of the last character C we've seen. Then the answer is
 * prev - i.
 *
 * We take the minimum of these two answers to create our final answer.
 */
