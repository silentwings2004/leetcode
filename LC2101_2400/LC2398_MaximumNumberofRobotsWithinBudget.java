package LC2101_2400;
import java.util.*;
public class LC2398_MaximumNumberofRobotsWithinBudget {
    /**
     * You have n robots. You are given two 0-indexed integer arrays, chargeTimes and runningCosts, both of length n.
     * The ith robot costs chargeTimes[i] units to charge and costs runningCosts[i] units to run. You are also given an
     * integer budget.
     *
     * The total cost of running k chosen robots is equal to max(chargeTimes) + k * sum(runningCosts), where
     * max(chargeTimes) is the largest charge cost among the k robots and sum(runningCosts) is the sum of running costs
     * among the k robots.
     *
     * Return the maximum number of consecutive robots you can run such that the total cost does not exceed budget.
     *
     * Input: chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
     * Output: 3
     *
     * Input: chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
     * Output: 0
     *
     * Constraints:
     *
     * chargeTimes.length == runningCosts.length == n
     * 1 <= n <= 5 * 10^4
     * 1 <= chargeTimes[i], runningCosts[i] <= 10^5
     * 1 <= budget <= 10^15
     * @param chargeTimes
     * @param runningCosts
     * @param budget
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length;
        long[] presum = new long[n + 1];
        for (int i = 1; i <= n; i++) presum[i] = presum[i - 1] + runningCosts[i - 1];

        int l = 0, r = n;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (helper(chargeTimes, presum, mid) <= budget) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private long helper(int[] nums, long[] presum, int k) {
        int n = nums.length, idx = 0;
        long[] maxBoot = new long[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) deque.pollLast();
            deque.offerLast(i);
            if (!deque.isEmpty() && i - deque.peekFirst() + 1 > k) deque.pollFirst();
            if (i >= k - 1) maxBoot[idx++] = nums[deque.peekFirst()];
        }

        long res = Long.MAX_VALUE;
        for (int i = k - 1; i < n; i++) {
            res = Math.min(res, maxBoot[i - (k - 1)] + (presum[i + 1] - presum[i - (k - 1)]) * k);
        }
        return res;
    }
}