package LC2101_2400;

public class LC2375_ConstructSmallestNumberFromDIString {
    /**
     * You are given a 0-indexed string pattern of length n consisting of the characters 'I' meaning increasing and 'D'
     * meaning decreasing.
     *
     * A 0-indexed string num of length n + 1 is created using the following conditions:
     *
     * num consists of the digits '1' to '9', where each digit is used at most once.
     * If pattern[i] == 'I', then num[i] < num[i + 1].
     * If pattern[i] == 'D', then num[i] > num[i + 1].
     * Return the lexicographically smallest possible string num that meets the conditions.
     *
     * Input: pattern = "IIIDIDDD"
     * Output: "123549876"
     *
     * Input: pattern = "DDD"
     * Output: "4321"
     *
     * Constraints:
     *
     * 1 <= pattern.length <= 8
     * pattern consists of only the letters 'I' and 'D'.
     * @param pattern
     * @return
     */
    // time = O(n), space = O(n)
    public String smallestNumber(String pattern) {
        int n = pattern.length();
        char[] chars = new char[n + 1];
        for (int i = 0; i <= n; i++) chars[i] = (char)(i + 1 + '0');


        for (int i = 0; i < n; i++) {
            int j = i;
            char c = pattern.charAt(i);
            while (j < n && pattern.charAt(j) == c) j++;
            int k = j - i;
            if (c == 'D') helper(chars, i, k + 1);
            i = j - 1;
        }
        return String.valueOf(chars);
    }

    private void helper(char[] chars, int x, int k) {
        int i = x, j = x + k - 1;
        while (i < j) {
            char t = chars[i];
            chars[i++] = chars[j];
            chars[j--] = t;
        }
    }
}
/**
 * same as LC484
 * LC942的follow-up
 */
