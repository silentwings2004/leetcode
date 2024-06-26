package LC2101_2400;
import java.util.*;
public class LC2244_MinimumRoundstoCompleteAllTasks {
    /**
     * You are given a 0-indexed integer array tasks, where tasks[i] represents the difficulty level of a task. In each
     * round, you can complete either 2 or 3 tasks of the same difficulty level.
     *
     * Return the minimum rounds required to complete all the tasks, or -1 if it is not possible to complete all the
     * tasks.
     *
     * Input: tasks = [2,2,3,3,2,4,4,4,4,4]
     * Output: 4
     *
     * Input: tasks = [2,3,3]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= tasks.length <= 10^5
     * 1 <= tasks[i] <= 10^9
     * @param tasks
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int minimumRounds(int[] tasks) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : tasks) map.put(x, map.getOrDefault(x, 0) + 1);

        int res = 0;
        for (int key : map.keySet()) {
            if (map.get(key) < 2) return -1;
            int amount = map.get(key);
            res += amount % 3 == 0 ? amount / 3 : amount / 3 + 1;
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(logn)
    public int minimumRounds2(int[] tasks) {
        Arrays.sort(tasks);
        int n = tasks.length, res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && tasks[j] == tasks[i]) j++;
            int len = j - i;
            if (len == 1) return -1;
            if (len % 3 == 0) res += len / 3;
            else res += len / 3 + 1;
            i = j - 1;
        }
        return res;
    }
}