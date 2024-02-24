package LC3001_3300;
import java.util.*;
public class LC3040_MaximumNumberofOperationsWiththeSameScoreII {
    /**
     * Given an array of integers called nums, you can perform any of the following operation while nums contains at
     * least 2 elements:
     *
     * Choose the first two elements of nums and delete them.
     * Choose the last two elements of nums and delete them.
     * Choose the first and the last elements of nums and delete them.
     * The score of the operation is the sum of the deleted elements.
     *
     * Your task is to find the maximum number of operations that can be performed, such that all operations have the
     * same score.
     *
     * Return the maximum number of operations possible that satisfy the condition mentioned above.
     *
     * Input: nums = [3,2,1,2,3,4]
     * Output: 3
     *
     * Input: nums = [3,2,6,1,4]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= nums.length <= 2000
     * 1 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(n^2)
    int[][] f;
    HashMap<Integer, Integer> map;
    public int maxOperations(int[] nums) {
        int n = nums.length;
        f = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -1);
        int a = 1 + dfs(nums, 2, n - 1, nums[0] + nums[1]);
        int b = 1 + dfs(nums, 0, n - 3, nums[n - 2] + nums[n - 1]);
        int c = 1 + dfs(nums, 1, n - 2, nums[0] + nums[n - 1]);
        return Math.max(a, Math.max(b, c));
    }

    private int dfs(int[] nums, int l, int r, int t) {
        if (l >= r) return 0;
        if (f[l][r] != -1) return f[l][r];
        int res = 0;
        if (nums[l] + nums[l + 1] == t) res = Math.max(res, 1 + dfs(nums, l + 2, r, t));
        if (nums[r] + nums[r - 1] == t) res = Math.max(res, 1 + dfs(nums, l, r - 2, t));
        if (nums[l] + nums[r] == t) res = Math.max(res, 1 + dfs(nums, l + 1, r - 1, t));
        f[l][r] = res;
        return res;
    }
}
/**
 * 区间dp:要解决的问题都是一段连续子数组的最多操作次数
 * 子问题是[从两侧向内缩小的] => 区间dp
 * dfs(i,j) = 操作 a[i] ... a[j] 这一段子数组(闭区间[i,j])的最多可以进行的操作次数
 */