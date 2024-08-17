package LC3001_3300;
import java.util.*;
public class LC3257_MaximumValueSumbyPlacingThreeRooksII {
    /**
     * You are given a m x n 2D array board representing a chessboard, where board[i][j] represents the value of the
     * cell (i, j).
     *
     * Rooks in the same row or column attack each other. You need to place three rooks on the chessboard such that the
     * rooks do not attack each other.
     *
     * Return the maximum sum of the cell values on which the rooks are placed.
     *
     * Input: board = [[-3,1,1,1],[-3,1,-3,1],[-3,2,1,1]]
     * Output: 4
     *
     * Input: board = [[1,2,3],[4,5,6],[7,8,9]]
     * Output: 15
     *
     * Input: board = [[1,1,1],[1,1,1],[1,1,1]]
     * Output: 3
     * @param board
     * @return
     */
    // time = O(m * n * log(m)), space = O(m * n)
    public long maximumValueSum(int[][] board) {
        int m = board.length, n = board[0].length;
        int[][] c1 = new int[n][m], c2 = new int[n][m];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (i == 0) c1[j][i] = board[i][j];
                else c1[j][i] = Math.max(c1[j][i - 1], board[i][j]);
            }
            for (int i = m - 1; i >= 0; i--) {
                if (i == m - 1) c2[j][i] = board[i][j];
                else c2[j][i] = Math.max(c2[j][i + 1], board[i][j]);
            }
        }

        long res = Long.MIN_VALUE;
        for (int i = 1; i < m - 1; i++) {
            List<int[]> q1 = new ArrayList<>();
            List<int[]> q2 = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int y = c1[j][i - 1], z = c2[j][i + 1];
                q1.add(new int[]{y, j});
                q2.add(new int[]{z, j});
            }
            Collections.sort(q1, (o1, o2) -> o2[0] - o1[0]);
            Collections.sort(q2, (o1, o2) -> o2[0] - o1[0]);
            for (int j = 0; j < n; j++) {
                int x = board[i][j];
                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        if (check(j, q1.get(a)[1], q2.get(b)[1])) {
                            res = Math.max(res, (long)x + q1.get(a)[0] + q2.get(b)[0]);
                        }
                    }
                }

            }
        }
        return res;
    }

    private boolean check(int a, int b, int c) {
        return a != b && a != c && b != c;
    }
}