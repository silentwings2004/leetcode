package LC001_300;
import java.util.*;
public class LC8_StringtoInteger {
    /**
     * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
     * Note:
     *
     * Only the space character ' ' is considered a whitespace character.
     * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
     *
     * Input: s = "-91283472332"
     * Output: -2147483648
     *
     * Constraints:
     *
     * 0 <= s.length <= 200
     * s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) return 0;

        char first = s.charAt(0);
        int sign = 1, start = 0;
        long res = 0;

        if (first == '+') start++;
        else if (first == '-') {
            start++;
            sign = -1;
        }

        int n = s.length();
        for (int i = start; i < n; i++) {
            if (!Character.isDigit(s.charAt(i))) return (int) res * sign;
            res = res * 10 + s.charAt(i) - '0';
            if (res * sign > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (res * sign < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }
        return (int) res * sign;
    }

    // S2:
    // time = O(n), space = O(1)
    public int myAtoi2(String s) {
        if (s == null) return 0;
        s = s.trim();
        int n = s.length();
        if (n == 0) return 0;

        int sign = 1, k = 0;
        if (s.charAt(0) == '+') k++;
        else if (s.charAt(0) == '-') {
            k++;
            sign = -1;
        }

        int res = 0;
        while (k < n && Character.isDigit(s.charAt(k))) {
            int x = s.charAt(k) - '0';
            // res = res * 10 + x > INT_MAX
            if (sign > 0 && res > (Integer.MAX_VALUE - x) / 10) return Integer.MAX_VALUE;
            // -res * 10 - x < INT_MIN
            if (sign < 0 && -res < (Integer.MIN_VALUE + x) / 10) return Integer.MIN_VALUE;
            if (-res * 10 - x == Integer.MIN_VALUE) return Integer.MIN_VALUE;
            res = res * 10 + x;
            k++;
        }
        return res * sign;
    }
}
