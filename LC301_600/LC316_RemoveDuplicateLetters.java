package LC301_600;
import java.util.*;
public class LC316_RemoveDuplicateLetters {
    /**
     * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure
     * your result is the smallest in lexicographical order among all possible results.
     *
     * Input: s = "bcabc"
     * Output: "abc"
     *
     * Constraints:
     *
     * 1 <= s.length <= 104
     * s consists of lowercase English letters.
     *
     * Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String removeDuplicateLetters(String s) {
        // corner case
        if (s == null || s.length() == 0) return "";

        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        Stack<Character> stack = new Stack<>();
        HashSet<Character> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) freq[c - 'a']--;
            else {
                // freq[stack.peek() - 'a'] > 0 表示栈顶元素后续还有，所以可以放心退栈！！！
                while (!stack.isEmpty() && stack.peek() > c && freq[stack.peek() - 'a'] > 0) {
                    set.remove(stack.pop());
                }
                stack.push(c);
                freq[c - 'a']--;
                set.add(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());
        return sb.reverse().toString();
    }

    // S2
    // time = O(n), space = O(n)
    final int N = 10010;
    public String removeDuplicateLetters2(String s) {
        char[] stk = new char[N];
        int tt = 0;
        int[] pos = new int[26];
        boolean[] ins = new boolean[26];
        int n = s.length();
        for (int i = 0; i < n; i++) pos[s.charAt(i) - 'a'] = i;

        for (int i = 0; i < n; i++) {
            if (ins[s.charAt(i) - 'a']) continue;
            while (tt > 0 && stk[tt] > s.charAt(i) && pos[stk[tt] - 'a'] > i) ins[stk[tt--] - 'a'] = false;
            stk[++tt] = s.charAt(i);
            ins[stk[tt] - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tt; i++) sb.append(stk[i]);
        return sb.toString();
    }
}
/**
 * ref: LC402, same as LC1081
 * b
 * bc
 * bca => ba => a
 * ab
 * abc
 * 规律：a出现了，b和c都得让位
 * 递增的话，不会轻易删除一个字符
 * Tj > Si 且Tj能被删除，则删掉Tj
 */