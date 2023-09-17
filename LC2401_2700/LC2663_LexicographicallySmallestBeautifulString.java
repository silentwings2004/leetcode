package LC2401_2700;

public class LC2663_LexicographicallySmallestBeautifulString {
    /**
     * A string is beautiful if:
     *
     * It consists of the first k letters of the English lowercase alphabet.
     * It does not contain any substring of length 2 or more which is a palindrome.
     * You are given a beautiful string s of length n and a positive integer k.
     *
     * Return the lexicographically smallest string of length n, which is larger than s and is beautiful. If there is
     * no such string, return an empty string.
     *
     * A string a is lexicographically larger than a string b (of the same length) if in the first position where a and
     * b differ, a has a character strictly larger than the corresponding character in b.
     *
     * For example, "abcd" is lexicographically larger than "abcc" because the first position they differ is at the
     * fourth character, and d is greater than c.
     *
     * Input: s = "abcz", k = 26
     * Output: "abda"
     *
     * Input: s = "dc", k = 4
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= n == s.length <= 10^5
     * 4 <= k <= 26
     * s is a beautiful string.
     * @param s
     * @param k
     * @return
     */
    // time = O(26 * n), space = O(n)
    public String smallestBeautifulString(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length, t = -1;
        for (int i = n - 1; i >= 0; i--) {
            int u = chars[i] - 'a';
            if (u > k - 1) continue;
            for (int j = u + 1; j < k; j++) {
                if (i - 1 >= 0 && chars[i - 1] - 'a' == j) continue;
                if (i - 2 >= 0 && chars[i - 2] - 'a' == j) continue;
                chars[i] = (char)(j + 'a');
                t = i;
                break;
            }
            if (t != -1) break;
        }
        if (t == -1) return "";
        for (int i = t + 1; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                if (i - 1 >= 0 && chars[i - 1] - 'a' == j) continue;
                if (i - 2 >= 0 && chars[i - 2] - 'a' == j) continue;
                chars[i] = (char)(j + 'a');
                break;
            }
        }
        return String.valueOf(chars);
    }
}