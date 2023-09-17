package LC901_1200;
import java.util.*;
public class LC1128_NumberofEquivalentDominoPairs {
    /**
     * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either
     * (a == c and b == d), or (a == d and b == c) - that is, one domino can be rotated to be equal to another domino.
     *
     * Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to
     * dominoes[j].
     *
     * Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
     * Output: 1
     *
     * Input: dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= dominoes.length <= 4 * 10^4
     * dominoes[i].length == 2
     * 1 <= dominoes[i][j] <= 9
     * @param dominoes
     * @return
     */
    // time = O(n), space = O(n)
    public int numEquivDominoPairs(int[][] dominoes) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int[] d : dominoes) {
            int key = 0;
            if (d[0] > d[1]) key = d[1] * 10 + d[0];
            else key = d[0] * 10 + d[1];
            res += map.getOrDefault(key, 0);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return res;
    }
}