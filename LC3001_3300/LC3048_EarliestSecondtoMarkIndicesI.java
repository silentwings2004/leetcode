package LC3001_3300;
import java.util.*;
public class LC3048_EarliestSecondtoMarkIndicesI {
    /**
     * You are given two 1-indexed integer arrays, nums and, changeIndices, having lengths n and m, respectively.
     *
     * Initially, all indices in nums are unmarked. Your task is to mark all indices in nums.
     *
     * In each second, s, in order from 1 to m (inclusive), you can perform one of the following operations:
     *
     * Choose an index i in the range [1, n] and decrement nums[i] by 1.
     * If nums[changeIndices[s]] is equal to 0, mark the index changeIndices[s].
     * Do nothing.
     * Return an integer denoting the earliest second in the range [1, m] when all indices in nums can be marked by
     * choosing operations optimally, or -1 if it is impossible.
     *
     * Input: nums = [2,2,0], changeIndices = [2,2,2,2,3,2,2,1]
     * Output: 8
     *
     * Input: nums = [1,3], changeIndices = [1,1,1,2,1,1,1]
     * Output: 6
     *
     * Input: nums = [0,1], changeIndices = [2,2,2]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 2000
     * 0 <= nums[i] <= 10^9
     * 1 <= m == changeIndices.length <= 2000
     * 1 <= changeIndices[i] <= n
     * @param nums
     * @param changeIndices
     * @return
     */
    // time = O(mlogm), space = O(1)
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = nums.length, m = changeIndices.length;
        long s = n;
        for (int x : nums) s += x;
        if (s > m) return -1;

        int l = (int)s, r = m;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, changeIndices, mid)) r = mid;
            else l = mid + 1;
        }
        return check(nums, changeIndices, r) ? r : -1;
    }

    private boolean check(int[] nums, int[] changeIndices, int mid) {
        HashSet<Integer> set = new HashSet<>();
        int tot = mid, waste = 0, cost = 0;
        for (int i = mid - 1; i >= 0; i--) {
            if (set.add(changeIndices[i] - 1)) {
                if (nums[changeIndices[i] - 1] + 1 > i + 1) return false;
                waste += nums[changeIndices[i] - 1] + 1;
            }
            cost++;
            waste = Math.max(waste, cost);
        }
        return waste <= tot && set.size() == nums.length;
    }
}
/**
 * nums[i] 当做第 i 门课程需要 nums[i] 天复习
 * changeIndices[t] 表示在第 t 天，第 changeIndices[t] 门课程可以考试
 * 考试那天是不能复习的
 *
 * 已知答案去判断能否成立，相比直接求答案更容易 => 二分答案
 * 前提：保证可以二分答案
 * => 答案越大，越能够搞定所有课程的复习+考试
 * => 有单调性，可以二分答案
 *
 * 考试的那天越晚越好，这样前面用来复习的时间就越多
 * 但是，我应该复习哪一门课程呢？
 * 规划一下：
 */