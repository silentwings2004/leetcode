package LC601_900;
import java.util.*;
public class LC746_MinCostClimbingStairs {
    /**
     * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost,
     * you can either climb one or two steps.
     *
     * You can either start from the step with index 0, or the step with index 1.
     *
     * Return the minimum cost to reach the top of the floor.
     *
     * Input: cost = [10,15,20]
     * Output: 15
     *
     * Constraints:
     *
     * 2 <= cost.length <= 1000
     * 0 <= cost[i] <= 999
     * @param cost
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int minCostClimbingStairs(int[] cost) {
        // corner case
        if (cost == null || cost.length == 0) return 0;

        int old = 0, now = 0;
        for (int i = 2; i <= cost.length; i++) {
            int temp = now;
            now = Math.min(old + cost[i - 2], now + cost[i - 1]);
            old = temp;

        }
        return now;
    }

    // S2: dp
    // time = O(n), space = O(n)
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int[] f = new int[n];
        f[0] = cost[0];
        f[1] = cost[1];

        for (int i = 2; i < n; i++) {
            f[i] = Math.min(f[i - 1], f[i - 2]) + cost[i];
        }
        return Math.min(f[n - 1], f[n - 2]);
    }
}