package LC301_600;
import java.util.*;
public class LC376_WiggleSubsequence {
    /**
     * Given an integer array nums, return the length of the longest wiggle sequence.
     *
     * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between
     * positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with
     * fewer than two elements is trivially a wiggle sequence.
     *
     * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) are alternately
     * positive and negative.
     * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences, the first because its first two
     * differences are positive and the second because its last difference is zero.
     * A subsequence is obtained by deleting some elements (eventually, also zero) from the original sequence, leaving
     * the remaining elements in their original order.
     *
     * Input: nums = [1,7,4,9,2,5]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 1000
     *
     * @param nums
     * @return
     */
    // S1: Greedy
    // time = O(n), space = O(1)
    public int wiggleMaxLength(int[] nums) {
        // res初始值为1，因为后面出现一段斜率变化之后，res = 2 => 初始值为1
        int n = nums.length, res = 1, dir = -2;
        for (int i = 1; i < n; i++) {
            int dir_prev = dir;
            if (nums[i] > nums[i - 1]) dir = 1;
            else if (nums[i] < nums[i - 1]) dir = -1;
            else dir = dir_prev; // 遇到平的，就追随之前的斜率。
            if (dir != dir_prev) res++; // 出现正负交替的时候，才能算一个拐点
        }
        return res;
    }

    // S2: DP
    // time = O(n), space = O(1)
    public int wiggleMaxLength2(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        // initialization
        int p = 1, q = 1; // 只有一个元素的话，自身就是一个wiggle sequence，所以初始值为1

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                q = p + 1;
            } else if (nums[i] < nums[i - 1]) {
                p = q + 1;
            } // == 的时候完全没帮助 => 直接跳过
        }
        return Math.max(p, q);
    }

    // S3: dp + rolling matrix
    public int wiggleMaxLength3(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[2][2]; // 0: up; 1: down
        int old = 0, now = 0;
        // init
        dp[old][0] = 1;
        dp[now][1] = 1;

        for (int i = 1; i < n; i++) {
            old = now;
            now = 1 - now;
            if (nums[i] < nums[i - 1]) {
                dp[now][0] = dp[old][1] + 1;
            } else dp[now][0] = dp[old][0];

            if (nums[i] > nums[i - 1]) {
                dp[now][1] = dp[old][0] + 1;
            } else dp[now][1] = dp[old][1];
        }
        return Math.max(dp[now][0], dp[now][1]);
    }

    // S4: Greedy
    // time = O(n), space = O(n)
    public int wiggleMaxLength4(int[] nums) {
        List<Integer> q = new ArrayList<>();
        q.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) continue;
            q.add(nums[i]);
        }
        int m = q.size();
        if (m <= 2) return m;

        int res = 2;
        for (int i = 1; i + 1 < m; i++) {
            int a = q.get(i - 1), b = q.get(i), c = q.get(i + 1);
            if (a < b && b > c || a > b && b < c) res++;
        }
        return res;
    }
}

/**
 * 贪心：找出原数组所有的拐点
 * 拐点定义 + -> -, - -> +
 * 如果是0的话，就继承上一个斜率的正负，不会出现0
 * 只有出现正负交替的时候，才算拐点
 * 没有任何拐点的话，初始值应为1
 *
 * S2: DP
 * 如果之前是向上 => 后面向下才有帮助，如果依然向上则没有帮助，长度 q = p + 1
 * 同样，如果之前是向下 => 新增向上，就得到一个更长的wiggle sequence，p = q + 1
 *
 * 1. 去除连续相同的数
 */
