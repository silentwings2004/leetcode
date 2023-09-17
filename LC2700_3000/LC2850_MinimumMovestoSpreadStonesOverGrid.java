package LC2700_3000;
import java.util.*;
public class LC2850_MinimumMovestoSpreadStonesOverGrid {
    /**
     * You are given a 0-indexed 2D integer matrix grid of size 3 * 3, representing the number of stones in each cell.
     * The grid contains exactly 9 stones, and there can be multiple stones in a single cell.
     *
     * In one move, you can move a single stone from its current cell to any other cell if the two cells share a side.
     *
     * Return the minimum number of moves required to place one stone in each cell.
     *
     * Input: grid = [[1,1,0],[1,1,1],[1,2,1]]
     * Output: 3
     *
     * Input: grid = [[1,3,0],[1,0,0],[1,0,3]]
     * Output: 4
     *
     * Constraints:
     *
     * grid.length == grid[i].length == 3
     * 0 <= grid[i][j] <= 9
     * Sum of grid is equal to 9.
     * @param grid
     * @return
     */
    int[][] g;
    List<int[]> p, q;
    int res;
    public int minimumMoves(int[][] grid) {
        res = 0x3f3f3f3f;
        g = grid;
        p = new ArrayList<>();
        q = new ArrayList<>();
        int n = g.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] > 1) p.add(new int[]{i, j});
                else if (g[i][j] == 0) q.add(new int[]{i, j});
            }
        }
        dfs(0, 0);
        return res;
    }

    private void dfs(int u, int s) {
        if (u == q.size()) res = Math.min(res, s);
        else {
            int a = q.get(u)[0], b = q.get(u)[1];
            for (int[] t : p) {
                int x = t[0], y = t[1];
                if (g[x][y] <= 1) continue;
                int d = Math.abs(a - x) + Math.abs(b - y);
                g[x][y]--;
                dfs(u + 1, s + d);
                g[x][y]++;
            }
        }
    }
}