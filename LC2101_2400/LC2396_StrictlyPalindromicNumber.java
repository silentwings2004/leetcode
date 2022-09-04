package LC2101_2400;

public class LC2396_StrictlyPalindromicNumber {
    /**
     * An integer n is strictly palindromic if, for every base b between 2 and n - 2 (inclusive), the string
     * representation of the integer n in base b is palindromic.
     *
     * Given an integer n, return true if n is strictly palindromic and false otherwise.
     *
     * A string is palindromic if it reads the same forward and backward.
     *
     * Input: n = 9
     * Output: false
     *
     * Input: n = 4
     * Output: false
     *
     * Constraints:
     *
     * 4 <= n <= 10^5
     * @param n
     * @return
     */
    // time = O(n), space = O(n)
    public boolean isStrictlyPalindromic(int n) {
        for (int i = 2; i <= n - 2; i++) {
            String s = convert(n, i);
            if (!isPalin(s)) return false;
        }
        return true;
    }

    private String convert(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % k);
            n /= k;
        }
        return sb.reverse().toString();
    }

    private boolean isPalin(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else return false;
        }
        return true;
    }
}
