package LC1501_1800;
import java.util.*;
public class LC1673_FindtheMostCompetitiveSubsequence {
    /**
     * Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.
     *
     * An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.
     *
     * We define that a subsequence a is more competitive than a subsequence b (of the same length) if
     * in the first position where a and b differ, subsequence a has a number less than the corresponding number in b.
     * For example, [1,3,4] is more competitive than [1,3,5] because the first position they differ is
     * at the final number, and 4 is less than 5.
     *
     * Input: nums = [3,5,2,6], k = 2
     * Output: [2,6]
     *
     * Input: nums = [2,4,3,3,5,4,9,6], k = 4
     * Output: [2,3,3,4]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 105
     * 0 <= nums[i] <= 109
     * 1 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // S1: 单调栈
    // time = O(n), space = O(n)
    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;
        int[] stk = new int[n + 1];
        int tt = 0;
        for (int i = 0; i < n; i++) {
            while (tt > 0 && nums[stk[tt]] > nums[i] && n - i + tt - 1 >= k) tt--;
            stk[++tt] = i;
        }
        int[] res = new int[k];
        for (int i = 1; i <= k; i++) res[i - 1] = nums[stk[i]];
        return res;
    }
}

/**
 * ref: LC402
 * 字典序最小的string
 * 逐个比较
 * 1 3 5 7 9 6        单调栈，maintain k-size
 * 1 3 5 (7 9) 6      递增序列 -> 最多扔 n - k 个元素，超过的话只能老老实实放在栈顶上
 * 后面比较大，只能扔后面
 */
