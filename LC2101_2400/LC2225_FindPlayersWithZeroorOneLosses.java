package LC2101_2400;
import java.util.*;
public class LC2225_FindPlayersWithZeroorOneLosses {
    /**
     * You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri
     * defeated player loseri in a match.
     *
     * Return a list answer of size 2 where:
     *
     * answer[0] is a list of all players that have not lost any matches.
     * answer[1] is a list of all players that have lost exactly one match.
     * The values in the two lists should be returned in increasing order.
     *
     * Note:
     *
     * You should only consider the players that have played at least one match.
     * The testcases will be generated such that no two matches will have the same outcome.
     *
     * Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
     * Output: [[1,2,10],[4,5,7,8]]
     *
     * Constraints:
     *
     * 1 <= matches.length <= 10^5
     * matches[i].length == 2
     * 1 <= winneri, loseri <= 10^5
     * winneri != loseri
     * All matches[i] are unique.
     * @param matches
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    final int N = 100010;
    public List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> res = new ArrayList<>();
        int[] cnt = new int[N];
        Arrays.fill(cnt, -1);
        for (int[] x : matches) {
            int a = x[0], b = x[1];
            if (cnt[a] == -1) cnt[a] = 0;
            if (cnt[b] == -1 || cnt[b] == 0) cnt[b] = 1;
            else cnt[b]++;
        }

        List<Integer> q1 = new ArrayList<>();
        List<Integer> q2 = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (cnt[i] == -1) continue;
            else if (cnt[i] == 0) q1.add(i);
            else if (cnt[i] == 1) q2.add(i);
        }
        res.add(q1);
        res.add(q2);
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public List<List<Integer>> findWinners2(int[][] matches) {
        List<List<Integer>> res = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] x : matches) {
            int a = x[0], b = x[1];
            map.put(a, map.getOrDefault(a, 0));
            map.put(b, map.getOrDefault(b, 0) + 1);
        }
        List<Integer> win = new ArrayList<>();
        List<Integer> lose = new ArrayList<>();
        for (int x : map.keySet()) {
            if (map.get(x) == 0) win.add(x);
            if (map.get(x) == 1) lose.add(x);
        }
        res.add(win);
        res.add(lose);
        return res;
    }
}
