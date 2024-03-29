package LC301_600;
import java.util.*;
public class LC564_FindtheClosestPalindrome {
    /**
     * Given a string n representing an integer, return the closest integer (not including itself), which is a
     * palindrome. If there is a tie, return the smaller one.
     *
     * The closest is defined as the absolute difference minimized between two integers.
     *
     * Input: n = "123"
     * Output: "121"
     *
     * Constraints:
     *
     * 1 <= n.length <= 18
     * n consists of only digits.
     * n does not have leading zeros.
     * n is representing an integer in the range [1, 10^18 - 1].
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public String nearestPalindromic(String n) {
        int m = n.length();
        long cur = Long.parseLong(n);
        HashSet<Long> set = new HashSet<>();
        set.add((long) Math.pow(10, (m - 1)) - 1);
        set.add((long) Math.pow(10, m) + 1);
        long t = Long.parseLong(n.substring(0, (m + 1) / 2));
        for (long i = t - 1; i <= t + 1; i++) {
            long temp = helper(i, m % 2 == 0);
            if (temp != cur) set.add(temp);
        }

        long ans = -1;
        for (long i : set) {
            if (ans == -1) ans = i;
            else if (Math.abs(i - cur) < Math.abs(ans - cur)) ans = i;
            else if (Math.abs(i - cur) == Math.abs(ans - cur) && i < ans) ans = i;
        }
        return String.valueOf(ans);
    }

    private long helper(long k, boolean isEven) {
        StringBuilder sb = new StringBuilder();
        sb.append(k);
        int n = sb.length(), idx = isEven ? n - 1 : n - 2;
        while (idx >= 0) sb.append(sb.charAt(idx--));
        return Long.parseLong(sb.toString());
    }

    // S2
    // time = O(logn), space = O(1)
    public String nearestPalindromic2(String n) {
        int len = n.length();
        TreeSet<Long> set = new TreeSet<>();
        set.add((long) Math.pow(10, len - 1) - 1);
        set.add((long) Math.pow(10, len) + 1);
        long m = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long i = m - 1; i <= m + 1; i++) {
            String a = String.valueOf(i), b = new StringBuilder(a).reverse().toString();
            if (len % 2 == 1) set.add(Long.parseLong(a + b.substring(1)));
            else set.add(Long.parseLong(a + b));
        }
        long k = Long.parseLong(n), res = (long) 2e18;
        if (set.contains(k)) set.remove(k);
        for (long x : set) {
            if (Math.abs(x - k) < Math.abs(res - k)) res = x;
        }
        return String.valueOf(res);
    }
}
/**
 * 只要枚举前一半数即可
 * 考虑前一半m-1,m,m+1三种情况 + 特判2种情况：10^(len-1) 和 10^len + 1m
 */