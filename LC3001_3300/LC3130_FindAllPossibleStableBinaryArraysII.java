package LC3001_3300;

public class LC3130_FindAllPossibleStableBinaryArraysII {
    /**
     * You are given 3 positive integers zero, one, and limit.
     *
     * A binary array arr is called stable if:
     *
     * The number of occurrences of 0 in arr is exactly zero.
     * The number of occurrences of 1 in arr is exactly one.
     * Each subarray of arr with a size greater than limit must contain both 0 and 1.
     * Return the total number of stable binary arrays.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: zero = 1, one = 1, limit = 2
     * Output: 2
     *
     * Input: zero = 1, one = 2, limit = 1
     * Output: 1
     *
     * Input: zero = 3, one = 3, limit = 2
     * Output: 14
     *
     * Constraints:
     *
     * 1 <= zero, one, limit <= 1000
     * @param zero
     * @param one
     * @param limit
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int numberOfStableArrays(int zero, int one, int limit) {
        long mod = (long)(1e9 + 7);
        long[][][] f = new long[zero + 1][one + 1][2];
        for (int i = 0; i <= Math.min(zero, limit); i++) f[i][0][0] = 1;
        for (int i = 0; i <= Math.min(one, limit); i++) f[0][i][1] = 1;

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                f[i][j][0] = (f[i - 1][j][0] + f[i - 1][j][1]) % mod;
                if (i > limit) f[i][j][0] = (f[i][j][0] - f[i - limit - 1][j][1] + mod) % mod;
                f[i][j][1] = (f[i][j - 1][0] + f[i][j - 1][1]) % mod;
                if (j > limit) f[i][j][1] = (f[i][j][1] - f[i][j - limit - 1][0] + mod) % mod;
            }
        }
        return (int)((f[zero][one][0] + f[zero][one][1]) % mod);
    }
}
/**
 * limit: 最多有连续limit个0和连续limit个1
 * limit 怎么考虑
 * dfs(i,j,k): 表示用i个0，j个1构造稳定二进制数组的方案数，且其中第i+j个位置填k
 * k = 0
 * dfs(i,j,0)
 * dfs(i-1,j,1) + dfs(i-1,j,0) - dfs(i-limit-1,j,1)
 */