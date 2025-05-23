package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: LongestCommonSubsequence
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 1143. Longest Common Subsequence
 */
public class LC1143_LongestCommonSubsequence {
    /**
     * Given two strings text1 and text2, return the length of their longest common subsequence.
     *
     * A subsequence of a string is a new string generated from the original string with some characters(can be none)
     * deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde"
     * while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.
     *
     *
     *
     * If there is no common subsequence, return 0.
     *
     *
     *
     * Example 1:
     *
     * Input: text1 = "abcde", text2 = "ace"
     * Output: 3
     * Explanation: The longest common subsequence is "ace" and its length is 3.
     * Example 2:
     *
     * Input: text1 = "abc", text2 = "abc"
     * Output: 3
     * Explanation: The longest common subsequence is "abc" and its length is 3.
     * Example 3:
     *
     * Input: text1 = "abc", text2 = "def"
     * Output: 0
     * Explanation: There is no such common subsequence, so the result is 0.
     *
     *
     * Constraints:
     *
     * 1 <= text1.length <= 1000
     * 1 <= text2.length <= 1000
     * The input strings consist of lowercase English characters only.
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        // corner case
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) return 0;

        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
