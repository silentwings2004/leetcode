package LC1801_2100;

public class LC1960_MaximumProductoftheLengthofTwoPalindromicSubstrings {
    /**
     * You are given a 0-indexed string s and are tasked with finding two non-intersecting palindromic substrings of
     * odd length such that the product of their lengths is maximized.
     *
     * More formally, you want to choose four integers i, j, k, l such that 0 <= i <= j < k <= l < s.length and both
     * the substrings s[i...j] and s[k...l] are palindromes and have odd lengths. s[i...j] denotes a substring from
     * index i to index j inclusive.
     *
     * Return the maximum possible product of the lengths of the two non-intersecting palindromic substrings.
     *
     * A palindrome is a string that is the same forward and backward. A substring is a contiguous sequence of
     * characters in a string.
     *
     * Input: s = "ababbb"
     * Output: 9
     *
     * Constraints:
     *
     * 2 <= s.length <= 10^5
     * s consists of lowercase English letters.
     * @param s
     * @return
     */
    public long maxProduct(String s) {
        // corner case
        if (s == null || s.length() == 0) return 0;


    }
}
