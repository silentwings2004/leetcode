package LC001_300;
import java.util.*;
public class LC91_DecodeWays {
    /**
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given a non-empty string containing only digits, determine the total number of ways to decode it.
     *
     * The answer is guaranteed to fit in a 32-bit integer.
     *
     * Input: s = "226"
     * Output: 3
     *
     * @param s
     * @return
     */
    // S1: DP
    // time = O(n), space = O(n)
    public int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;

        for (int i = 1; i <= n; i++) {
            int u = s.charAt(i - 1) - '0';
            if (u >= 1 && u <= 9) f[i] += f[i - 1];
            if (i > 1) {
                u = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
                if (u >= 10 && u <= 26) f[i] += f[i - 2];
            }
        }
        return f[n];
    }

    // S2: DP2 + rolling array (最优解！！！）
    // time = O(n), space = O(1)
    public int numDecodings2(String s) {
        // corner case
        if (s == null || s.length() == 0) return 0;

        int len = s.length();
        int older = 0, old = 1, now = 0; // older -> chars[i - 2], old -> chars[i - 1], now -> chars[i]

        for (int i = 1; i <= len; i++) {
            // case 1: decode the last digit only
            char c = s.charAt(i - 1);
            now = 0;
            if (c >= '1' && c <= '9') now += old;

            // case 2: decode the last two digits
            if (i > 1) {
                int val = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
                if (val >= 10 && val <= 26) now += older;
            }
            older = old;
            old = now;
        }
        return now;
    }
}
/**
 * dp
 * 状态表示：f[i]
 * 1. 集合：所有由前i个字符可以解码回去的字符串的集合
 * 2. 属性：个数
 * 状态计算：f[i]
 * 1. 一位数字：f[i] = f[i-1] (1~9)
 * 2. 两位数字: f[i-1] = f[i-2] (10~26)
 *
 * f(i):
 * 集合：所有由前i个字符可以解码回去的字符串的集合
 * 属性：个数
 * 状态计算：
 * 1. 一位数字: f(i-1) (1~9)
 * 2. 两位数字: f(i-2) (10~26)
 */