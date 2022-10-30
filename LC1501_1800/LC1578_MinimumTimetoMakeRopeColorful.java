package LC1501_1800;
import java.util.*;
public class LC1578_MinimumTimetoMakeRopeColorful {
    /**
     * Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i] is the color of
     * the ith balloon.
     *
     * Alice wants the rope to be colorful. She does not want two consecutive balloons to be of the same color, so she
     * asks Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a 0-indexed
     * integer array neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove the ith balloon
     * from the rope.
     *
     * Return the minimum time Bob needs to make the rope colorful.
     *
     * Input: colors = "abaac", neededTime = [1,2,3,4,5]
     * Output: 3
     *
     * Input: colors = "abc", neededTime = [1,2,3]
     * Output: 0
     *
     * Input: colors = "aabaa", neededTime = [1,2,3,4,1]
     * Output: 2
     *
     * Constraints:
     *
     * n == colors.length == neededTime.length
     * 1 <= n <= 10^5
     * 1 <= neededTime[i] <= 10^4
     * colors contains only lowercase English letters.
     * @param s
     * @param cost
     * @return
     */
    // S1: Greedy
    // time = O(nlogn), space = O(n)
    public int minCost(String s, int[] cost) {
        int n = s.length(), res = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (i + 1 < n && s.charAt(i + 1) == s.charAt(i)) {
                pq.offer(cost[i]);
                int j = i + 1;
                while (j < n && s.charAt(j) == s.charAt(i)) pq.offer(cost[j++]);
                while (pq.size() > 1) res += pq.poll();
                pq.clear();
                i = j - 1;
            }
        }
        return res;
    }

    // S2: DP
    // time = O(n), space = O(n)
    public int minCost2(String s, int[] cost) {
        int n = cost.length, INF = (int) 1e9;
        int[][] f = new int[n][27];
        for (int[] x : f) Arrays.fill(x, INF);
        f[0][s.charAt(0) - 'a'] = 0; // not delete
        f[0][26] = cost[0]; // delete

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 27; j++) {
                f[i][j] = f[i - 1][j] + cost[i];
            }
            int j = s.charAt(i) - 'a';
            for (int k = 0; k < 27; k++) {
                if (k != j) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][k]);
                }
            }
        }

        int res = INF;
        for (int i = 0; i < 27; i++) res = Math.min(res, f[n - 1][i]);
        return res;
    }

    // S3: Greedy
    // time = O(n), space = O(1)
    public int minCost3(String colors, int[] neededTime) {
        int prev = 0, res = 0, n = colors.length();
        for (int i = 1; i < n; i++) {
            if (colors.charAt(prev) != colors.charAt(i)) prev = i;
            else {
                if (neededTime[prev] < neededTime[i]) {
                    res += neededTime[prev];
                    prev = i;
                } else res += neededTime[i];
            }
        }
        return res;
    }
}
/**
 * dp
 * 1. 状态表示：
 * (1) 集合：所有从1~i中删且最后剩下的字母是j的删法的集合
 * (2) 属性：min
 * 2. 状态计算：集合划分
 * (1) 删掉第i个字母：f(i-1,j) + cost[i]
 * (2) 不删掉第i个字母: 分成27类，和这个字母相同的不能用 f(i-1,k) 枚举下k，取个min
 */
