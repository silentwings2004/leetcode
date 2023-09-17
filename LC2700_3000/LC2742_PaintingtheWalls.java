package LC2700_3000;
import java.util.*;
public class LC2742_PaintingtheWalls {
    /**
     * You are given two 0-indexed integer arrays, cost and time, of size n representing the costs and the time taken to
     * paint n different walls respectively. There are two painters available:
     *
     * A paid painter that paints the ith wall in time[i] units of time and takes cost[i] units of money.
     * A free painter that paints any wall in 1 unit of time at a cost of 0. But the free painter can only be used if
     * the paid painter is already occupied.
     * Return the minimum amount of money required to paint the n walls.
     *
     * Input: cost = [1,2,3,2], time = [1,2,3,2]
     * Output: 3
     *
     * Input: cost = [2,3,4,2], time = [1,1,1,1]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= cost.length <= 500
     * cost.length == time.length
     * 1 <= cost[i] <= 10^6
     * 1 <= time[i] <= 500
     * @param cost
     * @param time
     * @return
     */
    // time = O(n^2), space = O(n)
    final int INF = 0x3f3f3f3f;
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[] f = new int[n + 1];
        Arrays.fill(f, INF);
        f[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n; j >= 0; j --) {
                f[j] = Math.min(f[j], f[Math.max(j - time[i] - 1, 0)] + cost[i]);
            }
        }
        return f[n];
    }
}