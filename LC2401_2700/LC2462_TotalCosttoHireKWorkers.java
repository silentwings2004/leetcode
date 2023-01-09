package LC2401_2700;
import java.util.*;
public class LC2462_TotalCosttoHireKWorkers {
    /**
     * You are given a 0-indexed integer array costs where costs[i] is the cost of hiring the ith worker.
     *
     * You are also given two integers k and candidates. We want to hire exactly k workers according to the following
     * rules:
     *
     * You will run k sessions and hire exactly one worker in each session.
     * In each hiring session, choose the worker with the lowest cost from either the first candidates workers or the
     * last candidates workers. Break the tie by the smallest index.
     * For example, if costs = [3,2,7,7,1,2] and candidates = 2, then in the first hiring session, we will choose the
     * 4th worker because they have the lowest cost [3,2,7,7,1,2].
     * In the second hiring session, we will choose 1st worker because they have the same lowest cost as 4th worker but
     * they have the smallest index [3,2,7,7,2]. Please note that the indexing may be changed in the process.
     * If there are fewer than candidates workers remaining, choose the worker with the lowest cost among them. Break
     * the tie by the smallest index.
     * A worker can only be chosen once.
     * Return the total cost to hire exactly k workers.
     *
     * Input: costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
     * Output: 11
     *
     * Input: costs = [1,2,4,1], k = 3, candidates = 3
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= costs.length <= 10^5
     * 1 <= costs[i] <= 10^5
     * 1 <= k, candidates <= costs.length
     * @param costs
     * @param k
     * @param candidates
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int n = costs.length;
        int i = 0, j = n - 1;
        while (candidates-- > 0 && i <= j) {
            pq.offer(new int[]{costs[i], i, 0});
            if (i != j) pq.offer(new int[]{costs[j], j, 1});
            i++;
            j--;
        }

        int cnt = 0;
        long res = 0;
        while (i <= j && cnt < k) {
            int[] t = pq.poll();
            res += t[0];
            cnt++;
            if (t[2] == 0) pq.offer(new int[]{costs[i], i++, 0});
            else pq.offer(new int[]{costs[j], j--, 1});
        }
        while (cnt < k) {
            res += pq.poll()[0];
            cnt++;
        }
        return res;
    }
}
