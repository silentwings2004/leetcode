package LC601_900;
import java.util.*;
public class LC813_LargestSumofAverages {
    /**
     * You are given an integer array nums and an integer k. You can partition the array into at most k non-empty
     * adjacent subarrays. The score of a partition is the sum of the averages of each subarray.
     *
     * Note that the partition must use every integer in nums, and that the score is not necessarily an integer.
     *
     * Return the maximum score you can achieve of all the possible partitions. Answers within 10-6 of the actual answer
     * will be accepted.
     *
     * Input: nums = [9,1,2,3,9], k = 3
     * Output: 20.00000
     *
     * Input: nums = [1,2,3,4,5,6,7], k = 4
     * Output: 20.50000
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 10^4
     * 1 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // S1: DP
    // time = O(n^2 * k), space = O(n * k)
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        double[][] f = new double[n + 1][k + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], Integer.MIN_VALUE);
        f[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int t = 0; t < i; t++) {
                    f[i][j] = Math.max(f[i][j], f[t][j - 1] + (s[i] - s[t]) * 1.0 / (i - t));
                }
            }
        }
        return f[n][k];
    }

    // S1.2
    // time = O(n^2 * k), space = O(n * k)
    public double largestSumOfAverages2(int[] nums, int k) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        double[][] f = new double[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                if (j == 1) f[i][j] = s[i] * 1.0 / i;
                else {
                    for (int t = 0; t < i; t++) {
                        f[i][j] = Math.max(f[i][j], f[t][j - 1] + (s[i] - s[t]) * 1.0 / (i - t));
                    }
                }
            }
        }
        return f[n][k];
    }
}
/**
 * 序列型dp问题
 * 状态表示f(i,j):
 * 集合：所有将(1，i)分成j段的方案的集合
 * 属性：max
 * 状态计算：以倒数第二段的终点来分类 0，1，..., i-1
 */