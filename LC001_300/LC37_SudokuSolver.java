package LC001_300;
import java.util.*;
public class LC37_SudokuSolver {
    /**
     * Write a program to solve a Sudoku puzzle by filling the empty cells.
     *
     * A sudoku solution must satisfy all of the following rules:
     *
     * Each of the digits 1-9 must occur exactly once in each row.
     * Each of the digits 1-9 must occur exactly once in each column.
     * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
     * The '.' character indicates empty cells.
     *
     * Constraints:
     *
     * board.length == 9
     * board[i].length == 9
     * board[i][j] is a digit or '.'.
     * It is guaranteed that the input board has only one solution.
     * @param board
     */
    // S1: DFS
    // time = O(1), space = O(1)
    public void solveSudoku(char[][] board) {
        // corner case
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;

        solve(board);
    }

    private boolean solve(char[][] board) {
        int row = board.length, col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) { // first check if it is valid, then check if solvable
                            board[i][j] = c;
                            if (solve(board)) return true;
                            board[i][j] = '.'; // setback
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int i, int j, char c) {
        for (int k = 0; k < 9; k++) {
            if (board[k][j] == c) return false;
            if (board[i][k] == c) return false;
            if (board[(i / 3) * 3 + k / 3][(j / 3) * 3 + k % 3] == c) return false;
        }
        return true;
    }

    // S1.2: DFS
    // time = O(1), space = O(1)
    public void solveSudoku2(char[][] board) {
        // corner case
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;

        dfs(board, 0, 0);
    }

    private boolean dfs(char[][] board, int i, int j) {
        if (i == 9) return true;
        if (j == 9) return dfs(board, i + 1, 0); // 进入下一行，该行已填完
        if (board[i][j] != '.') return dfs(board, i, j + 1);

        for (char k = '1'; k <= '9'; k++) {
            if (!isOk(board, i, j, k)) continue;
            board[i][j] = k;
            if (dfs(board, i, j + 1)) return true;
            board[i][j] = '.'; // backtrack
        }
        return false;
    }

    private boolean isOk(char[][] board, int i, int j, char k) {
        for (int row = 0; row < 9; row++) {
            if (board[row][j] == k) return false;
        }

        for (int col = 0; col < 9; col++) {
            if (board[i][col] == k) return false;
        }

        // 以左上角来定义模块
        int x = i / 3 * 3, y = j / 3 * 3; // 小模块的左上角
        for (int p = 0; p < 3; p++) {
            for (int q = 0; q < 3; q++) {
                if (board[x + p][y + q] == k) return false;
            }
        }
        return true;
    }

    // S3: bit optimization
    // time = O(1), space = O(1)
    class Solution {
        final int N = 9, M = 1 << N;
        char[][] board;
        int[] row, col, ones, map;
        int[][] cell;
        public void solveSudoku(char[][] board) {
            this.board = board;
            row = new int[N];
            col = new int[N];
            ones = new int[M];
            map = new int[M];
            cell = new int[3][3];

            // init
            for (int i = 0; i < N; i++) row[i] = col[i] = M - 1;
            for (int i = 0; i < 3; i++) Arrays.fill(cell[i], M - 1);
            for (int i = 0; i < N; i++) map[1 << i] = i;
            for (int i = 0; i < M; i++) ones[i] = Integer.bitCount(i);

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] != '.') {
                        int t = board[i][j] - '1';
                        draw(i, j, t, true);
                    } else cnt++;
                }
            }
            dfs(cnt);
        }

        private void draw(int x, int y, int t, boolean flag) {
            if (flag) board[x][y] = (char)(t + '1');
            else board[x][y] = '.';

            int v = 1 << t;
            if (!flag) v = -v;

            row[x] -= v;
            col[y] -= v;
            cell[x / 3][y / 3] -= v;
        }

        private boolean dfs(int cnt) {
            if (cnt == 0) return true;

            int minv = 10, x = -1, y = -1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == '.') {
                        int state = get(i, j);
                        if (ones[state] < minv) {
                            minv = ones[state];
                            x = i;
                            y = j;
                        }
                    }
                }
            }

            int state = get(x, y);
            for (int i = state; i > 0; i -= lowbit(i)) {
                int t = map[lowbit(i)];
                draw(x, y, t, true);
                if (dfs(cnt - 1)) return true;
                draw(x, y, t, false);
            }
            return false;
        }

        private int get(int x, int y) {
            return row[x] & col[y] & cell[x / 3][y / 3];
        }

        private int lowbit(int x) {
            return x & -x;
        }
    }
}
/**
 * 暴力搜搜
 * 不违反规则，我们就继续填
 * 无论填什么不符合规则，那就退回去上一层重新填
 * 遇到死胡同就换，不行就回溯上一层
 * 递归的思想
 * 当前没矛盾的话就move on，有矛盾的话就换一格
 */