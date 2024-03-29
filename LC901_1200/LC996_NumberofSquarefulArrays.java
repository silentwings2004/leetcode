package LC901_1200;
import java.util.*;
public class LC996_NumberofSquarefulArrays {
    /**
     * An array is squareful if the sum of every pair of adjacent elements is a perfect square.
     *
     * Given an integer array nums, return the number of permutations of nums that are squareful.
     *
     * Two permutations perm1 and perm2 are different if there is some index i such that perm1[i] != perm2[i].
     *
     * Input: nums = [1,17,8]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 12
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n^n), space = O(n)
    private int res = 0;
    public int numSquarefulPerms(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (Math.sqrt(nums[i] + nums[j]) == (int) Math.sqrt(nums[i] + nums[j])) graph[i].add(j);
            }
        }

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            visited[i] = true;
            dfs(nums, graph, i, 1, visited);
            visited[i] = false;
        }
        return res;
    }

    private void dfs(int[] nums, List<Integer>[] graph, int idx, int count, boolean[] visited) {
        int n = graph.length;
        // base case
        if (count == n) {
            res++;
            return;
        }

        int last = -1;
        for (int x : graph[idx]) {
            if (visited[x]) continue;
            if (nums[x] == last) continue;
            visited[x] = true;
            last = nums[x];
            dfs(nums, graph, x, count + 1, visited);
            visited[x] = false;
        }
    }

    // S2: 状态压缩DP
    // time = O(2^n * n^2), space = O(2^n * n)
    public int numSquarefulPerms2(int[] nums) {
        int n = nums.length;
        int[][] f = new int[1 << n][n];
        for (int i = 0; i < n; i++) f[1 << i][i] = 1;
        for (int i = 0; i < 1 << n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    for (int k = 0; k < n; k++) {
                        if ((i >> k & 1) == 0 && is_sqr(nums[j] + nums[k])) {
                            f[i | 1 << k][k] += f[i][j];
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) res += f[(1 << n) - 1][i];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
        for (int v : map.values()) res /= fact(v);
        return res;
    }

    private boolean is_sqr(int x) {
        int t = (int)Math.sqrt(x);
        return t * t == x;
    }

    private int fact(int x) {
        int res = 1;
        for (int i = 1; i <= x; i++) res *= i;
        return res;
    }
}
/**
 * 1 <= A.length <= 12  => 暴力
 * 穷举这样的permutation
 * 只要确定一个头，大概率后面都自动接上去
 * 主动构造
 * 这里面可能会有多个分支
 * [1,8,15,35]
 * 深度优先搜索
 * 根据这个规则不停的往后构造
 * 目标就是所有的元素都访问到
 * 建立边 1 - 8
 * 相当于一笔画，找出所有路径，把所有点都走遍
 * 排序去重
 *
 * 状态压缩dp
 * f(i,j): 已经用过的数的状态是i，且最后一个数为j
 * => O(2^n * n^2)
 */
