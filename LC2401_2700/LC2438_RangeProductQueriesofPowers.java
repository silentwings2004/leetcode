package LC2401_2700;
import java.util.*;
public class LC2438_RangeProductQueriesofPowers {
    /**
     * Given a positive integer n, there exists a 0-indexed array called powers, composed of the minimum number of
     * powers of 2 that sum to n. The array is sorted in non-decreasing order, and there is only one way to form the
     * array.
     *
     * You are also given a 0-indexed 2D integer array queries, where queries[i] = [lefti, righti]. Each queries[i]
     * represents a query where you have to find the product of all powers[j] with lefti <= j <= righti.
     *
     * Return an array answers, equal in length to queries, where answers[i] is the answer to the ith query. Since the
     * answer to the ith query may be too large, each answers[i] should be returned modulo 10^9 + 7.
     *
     * Input: n = 15, queries = [[0,1],[2,2],[0,3]]
     * Output: [2,4,64]
     *
     * Input: n = 2, queries = [[0,0]]
     * Output: [2]
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * 1 <= queries.length <= 10^5
     * 0 <= starti <= endi < powers.length
     * @param n
     * @param queries
     * @return
     */
    // time = O(m), space = O(1)
    public int[] productQueries(int n, int[][] queries) {
        List<Integer> power = new LinkedList<>();
        for (int i = 0; i < 32; i++) {
            if ((n >> i & 1) == 1) power.add(i);
        }

        int m = queries.length;
        int[] res = new int[m];
        long mod = (long)(1e9 + 7);
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1];
            long p = 1;
            for (int j = l; j <= r; j++) {
                p = (p * (long) Math.pow(2, power.get(j))) % mod;
            }
            res[i] = (int) p;
        }
        return res;
    }
}
