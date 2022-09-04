package LC301_600;
public class LC357_CountNumberswithUniqueDigits {
    /**
     * Given an integer n, return the count of all numbers with unique digits, x, where 0 <= x < 10^n.
     *
     * Input: n = 2
     * Output: 91
     *
     * Input: n = 0
     * Output: 1
     *
     * Constraints:
     *
     * 0 <= n <= 8
     * @param n
     * @return
     */
    // time = O(n), space = O(1)
    public int countNumbersWithUniqueDigits(int n) {
        int res = 1; // 0
        for (int len = 1; len <= n; len++) {
            res += A(10, len) - A(9, len - 1);
        }
        return res;
    }

    private int A(int m, int n) {
        int res = 1;
        for (int i = 0; i < n; i++) {
            res *= m - i;
        }
        return res;
    }
}
/**
 * xxxxxxxx => 0 ~ 9 => A(10, 8) - A(9, 7) 不能有leading zero
 * xxxx => A(10, len) - A(9, len - 1)
 * xx
 * x => 允许有leading 0 => 0, 1~9 单独处理下 => A(10, 1) - A(9,0) = 10 - 1 = 9
 * 0 => 1
 */
