package LC601_900;

public class LC861_ScoreAfterFlippingMatrix {
    /**
     * You are given an m x n binary matrix grid.
     *
     * A move consists of choosing any row or column and toggling each value in that row or column (i.e., changing all
     * 0's to 1's, and all 1's to 0's).
     *
     * Every row of the matrix is interpreted as a binary number, and the score of the matrix is the sum of these
     * numbers.
     *
     * Return the highest possible score after making any number of moves (including zero moves).
     *
     * Input: grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
     * Output: 39
     *
     * Input: grid = [[0]]
     * Output: 1
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 20
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(1)
    public int matrixScore(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] ^= 1;
                }
            }
        }

        int res = (1 << n - 1) * m;
        for (int j = 1; j < n; j++) {
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) cnt++;
            }
            res += (1 << n - 1 - j) * Math.max(cnt, m - cnt);
        }
        return res;
    }
}
/**
 * 顺序无所谓
 * 最高位最大，枚举第一列 => 每一行是否要操作就唯一确定了
 */