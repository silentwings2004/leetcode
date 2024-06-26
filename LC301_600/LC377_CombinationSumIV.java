package LC301_600;
import java.util.*;
public class LC377_CombinationSumIV {
    /**
     * Given an array of distinct integers nums and a target integer target, return the number of possible combinations
     * that add up to target.
     *
     * The answer is guaranteed to fit in a 32-bit integer.
     *
     * Input: nums = [1,2,3], target = 4
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= 1000
     * All the elements of nums are unique.
     * 1 <= target <= 1000
     *
     *
     * Follow up: What if negative numbers are allowed in the given array? How does it change the problem? What
     * limitation we need to add to the question to allow negative numbers?
     * @param nums
     * @param target
     * @return
     */
    // S1: DFS + pruning
    // time = O(n * t), space = O(t)   t: target value
    public int combinationSum4(int[] nums, int target) {
        int[] f = new int[target + 1];
        Arrays.fill(f, -1);
        return dfs(nums, target, f);
    }

    private int dfs(int[] nums, int t, int[] f) {
        if (t == 0) return 1;
        if (f[t] != -1) return f[t];

        int res = 0;
        for (int x : nums) {
            if (t >= x) {
                res += dfs(nums, t - x, f);
            }
        }
        f[t] = res;
        return res;
    }

    // S2: DP
    // time = O(n * m), space = O(n)
    public int combinationSum42(int[] nums, int target) {
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int x : nums) {
                if (i >= x) f[i] += f[i - x];
            }
        }
        return f[target];
    }
}
/**
 * 1. 每个数可以用无限次
 * 2. 顺序不同，方案不同
 * 与背包问题不同，背包问题不考虑顺序
 * 考虑第一个位置n种选择，第二个位置还是n种选择，以此类推，这里的顺序是位置
 * dp:
 * 状态表示 f[i,j]
 * 集合：前i个位置，总和为j的所有方案
 * 属性：count
 * 状态计算：前i-1个位置随意，最后一个是ak，总和为j的方案 => 前i-1个位置总和为j-ak
 *
 * 优化：
 * 状态表示 f[j]
 * 集合：当前总和为j的所有方案
 * 属性：count
 * 状态计算：
 * 假设最后一个是ak的话，考虑如何去计算？
 * 统计前面的方案个数，数量没有限制，总和是j - ak => f[j-ak]  从小到大去枚举
 * follow-up:
 * 1 + (-1) = 0 => 答案无限个，限制个数则答案变得有限
 */