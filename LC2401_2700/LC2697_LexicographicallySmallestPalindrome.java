package LC2401_2700;

public class LC2697_LexicographicallySmallestPalindrome {
    /**
     * You are given a string s consisting of lowercase English letters, and you are allowed to perform operations on
     * it. In one operation, you can replace a character in s with another lowercase English letter.
     *
     * Your task is to make s a palindrome with the minimum number of operations possible. If there are multiple
     * palindromes that can be made using the minimum number of operations, make the lexicographically smallest one.
     *
     * A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and
     * b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b.
     *
     * Return the resulting palindrome string.
     *
     * Input: s = "egcfe"
     * Output: "efcfe"
     *
     * Input: s = "abcd"
     * Output: "abba"
     *
     * Input: s = "seven"
     * Output: "neven"
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s consists of only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String makeSmallestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            if (chars[i] == chars[j]) continue;
            if (chars[i] < chars[j]) chars[j] = chars[i];
            else chars[i] = chars[j];
        }
        return String.valueOf(chars);
    }
}