package LC901_1200;
import java.util.*;
public class LC1143_LongestCommonSubsequence {
    /**
     * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common
     * subsequence, return 0.
     *
     * A subsequence of a string is a new string generated from the original string with some characters (can be none)
     * deleted without changing the relative order of the remaining characters.
     *
     * For example, "ace" is a subsequence of "abcde".
     *
     * Input: text1 = "abcde", text2 = "ace"
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= text1.length, text2.length <= 1000
     * text1 and text2 consist of only lowercase English characters.
     * @param text1
     * @param text2
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] f = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
                }
            }
        }
        return f[m][n];
    }
}