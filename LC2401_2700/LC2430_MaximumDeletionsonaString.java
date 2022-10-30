package LC2401_2700;

public class LC2430_MaximumDeletionsonaString {
    /**
     * You are given a string s consisting of only lowercase English letters. In one operation, you can:
     *
     * Delete the entire string s, or
     * Delete the first i letters of s if the first i letters of s are equal to the following i letters in s, for any i
     * in the range 1 <= i <= s.length / 2.
     * For example, if s = "ababc", then in one operation, you could delete the first two letters of s to get "abc",
     * since the first two letters of s and the following two letters of s are both equal to "ab".
     *
     * Return the maximum number of operations needed to delete all of s.
     *
     * Input: s = "abcabcdabc"
     * Output: 2
     *
     * Input: s = "aaabaab"
     * Output: 4
     *
     * Input: s = "aaaaa"
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= s.length <= 4000
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n^2), space = O(n)
    final int N = 4010, P = 131;
    long[] h, p;
    public int deleteString(String s) {
        int n = s.length();
        h = new long[N];
        p = new long[N];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + s.charAt(i - 1);
        }

        int[] f = new int[n + 2];
        for (int i = n; i > 0; i--) {
            f[i] = 1;
            for (int j = 1; j <= (n - i + 1) / 2; j++) {
                if (get(i, i + j - 1) == get(i + j, i + j * 2 - 1)) {
                    f[i] = Math.max(f[i], f[i + j] + 1);
                }
            }
        }
        return f[1];
    }

    private long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}
/**
 * 方案数指数级别 -> dp / greedy
 * O(n^2)
 * dp:
 * 1. 状态表示：f(i)
 * (1) 集合：表示将[i,n]这个子串删完的所有操作方式的集合
 * (2) 属性：max
 * 2. 状态计算：=> f(1)
 * 按第一段的操作方法来分类: 删除第一段的长度来分类
 * (n - i + 1) / 2
 * 如果某种方案不存在，则表明这个子集是空的
 * 第j类：后面[i+j,n]取最大值 = f(i+j)
 * => f(i+j)+1
 * 需要快速判断前j个字母是否等于后j个字母 => 字符串哈希 O(n) 时间预处理
 */