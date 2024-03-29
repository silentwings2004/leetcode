package LC001_300;
import java.util.*;
public class LC97_InterleavingString {
    /**
     * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
     *
     * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such
     * that:
     *
     * s = s1 + s2 + ... + sn
     * t = t1 + t2 + ... + tm
     * |n - m| <= 1
     * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
     * Note: a + b is the concatenation of strings a and b.
     *
     * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * Output: true
     *
     * Constraints:
     *
     * 0 <= s1.length, s2.length <= 100
     * 0 <= s3.length <= 200
     * s1, s2, and s3 consist of lowercase English letters.
     *
     *
     * Follow up: Could you solve it using only O(s2.length) additional memory space?
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    // S1
    // time = O(m * n), space = O(m * n)
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length();
        if (s3.length() != n + m) return false;

        boolean[][] f = new boolean[n + 1][m + 1];
        s1 = "#" + s1;
        s2 = "#" + s2;
        s3 = "#" + s3;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j == 0) f[i][j] = true;
                else {
                    if (i > 0 && s1.charAt(i) == s3.charAt(i + j)) f[i][j] = f[i - 1][j];
                    if (j > 0 && s2.charAt(j) == s3.charAt(i + j)) f[i][j] |= f[i][j - 1];
                }
            }
        }
        return f[n][m];
    }

    // S1.2
    // time = O(m * n), space = O(m * n)
    public boolean isInterleave2(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) return false;

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0]= true;

        for (int i = 1; i <= m; i++) dp[i][0] = (s1.charAt(i - 1) == s3.charAt(i - 1)) && dp[i - 1][0];
        for (int j = 1; j <= n; j++) dp[0][j] = (s2.charAt(j - 1) == s3.charAt(j - 1)) && dp[0][j - 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j] || (s2.charAt(j - 1) == s3.charAt(i + j - 1)) && dp[i][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[m][n];
    }
}
/**
 * dp
 * 状态表示：
 * 1.集合：所有由s1[1~i]和s2[1~j]交错形成s3[1~i+j]的方案
 * 2.属性：集合是否非空
 * 状态计算：
 * f(i-1,j)
 * f(i,j-1)
 *
 * dp[i][j]: whether s3[0:i+j] is formed by the interleaving of s1[0:i] and s2[0:j]
 *
 * x x x i
 * y y y y y y y j
 * z z z z z z z z z z z z
 *
 * dp[i][j]
 *
 * dp[i-1][j]
 * dp[i][j-1]
 *
 */
