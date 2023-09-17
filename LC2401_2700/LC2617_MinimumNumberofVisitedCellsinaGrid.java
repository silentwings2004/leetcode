package LC2401_2700;
import java.util.*;
public class LC2617_MinimumNumberofVisitedCellsinaGrid {
    /**
     * You are given a 0-indexed m x n integer matrix grid. Your initial position is at the top-left cell (0, 0).
     *
     * Starting from the cell (i, j), you can move to one of the following cells:
     *
     * Cells (i, k) with j < k <= grid[i][j] + j (rightward movement), or
     * Cells (k, j) with i < k <= grid[i][j] + i (downward movement).
     * Return the minimum number of cells you need to visit to reach the bottom-right cell (m - 1, n - 1). If there is
     * no valid path, return -1.
     *
     * Input: grid = [[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]
     * Output: 4
     *
     * Input: grid = [[3,4,2,1],[4,2,1,1],[2,1,1,0],[3,4,1,0]]
     * Output: 3
     *
     * Input: grid = [[2,1,0],[1,0,0]]
     * Output: -1
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 10^5
     * 1 <= m * n <= 10^5
     * 0 <= grid[i][j] < m * n
     * grid[m - 1][n - 1] == 0
     * @param grid
     * @return
     */
    // time = O(m * n * log(m * n)), space = O(m * n)
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        int[][] dist = new int[m][n];
        dist[0][0] = 1;
        TreeSet<Integer>[] r = new TreeSet[m];
        TreeSet<Integer>[] c = new TreeSet[n];
        for (int i = 0; i < m; i++) {
            r[i] = new TreeSet<>();
            for (int j = 0; j < n; j++) r[i].add(j);
        }
        for (int j = 0; j < n; j++) {
            c[j] = new TreeSet<>();
            for (int i = 0; i < m; i++) c[j].add(i);
        }
        r[0].remove(0);
        c[0].remove(0);

        while (!q.isEmpty()) {
            int[] t = q.poll();
            int x = t[0], y = t[1];
            if (x == m - 1 && y == n - 1) return dist[x][y];

            int a = Math.min(grid[x][y] + y, n - 1), b = Math.min(grid[x][y] + x, m - 1);
            while (true) {
                Integer hk = r[x].higher(y);
                if (hk != null && hk <= a && dist[x][hk] == 0) {
                    q.offer(new int[]{x, hk});
                    dist[x][hk] = dist[x][y] + 1;
                    r[x].remove(hk);
                    c[hk].remove(x);
                } else break;
            }

            while (true) {
                Integer hk = c[y].higher(x);
                if (hk != null && hk <= b && dist[hk][y] == 0) {
                    q.offer(new int[]{hk, y});
                    dist[hk][y] = dist[x][y] + 1;
                    c[y].remove(hk);
                    r[hk].remove(y);
                } else break;
            }
        }
        return -1;
    }
}