package LC2101_2400;
import java.util.*;
public class LC2371_MinimizeMaximumValueinaGrid {
    /**
     * You are given an m x n integer matrix grid containing distinct positive integers.
     *
     * You have to replace each integer in the matrix with a positive integer satisfying the following conditions:
     *
     * The relative order of every two elements that are in the same row or column should stay the same after the
     * replacements.
     * The maximum number in the matrix after the replacements should be as small as possible.
     * The relative order stays the same if for all pairs of elements in the original matrix such that grid[r1][c1] >
     * grid[r2][c2] where either r1 == r2 or c1 == c2, then it must be true that grid[r1][c1] > grid[r2][c2] after the replacements.
     *
     * For example, if grid = [[2, 4, 5], [7, 3, 9]] then a good replacement could be either grid = [[1, 2, 3],
     * [2, 1, 4]] or grid = [[1, 2, 3], [3, 1, 4]].
     *
     * Return the resulting matrix. If there are multiple answers, return any of them.
     *
     * Input: grid = [[3,1],[2,5]]
     * Output: [[2,1],[1,2]]
     *
     * Input: grid = [[10]]
     * Output: [[1]]
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 1000
     * 1 <= m * n <= 10^5
     * 1 <= grid[i][j] <= 10^9
     * grid consists of distinct integers.
     * @param grid
     * @return
     */
    // time = O(m * n * log(m * n)), space = O(m * n)
    public int[][] minScore(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rm = new int[m], cm = new int[n];
        int[][] arr = new int[m * n][3];
        Arrays.fill(rm, 1);
        Arrays.fill(cm, 1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i * n + j] = new int[]{grid[i][j], i, j};
            }
        }
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        for (int i = 0; i < m * n; i++) {
            int[] cur = arr[i];
            int x = cur[1], y = cur[2];
            grid[x][y] = Math.max(rm[x], cm[y]);
            rm[x] = grid[x][y] + 1;
            cm[y] = grid[x][y] + 1;
        }
        return grid;
    }
}
