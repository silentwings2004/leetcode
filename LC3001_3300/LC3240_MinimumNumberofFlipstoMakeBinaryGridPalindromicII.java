package LC3001_3300;

public class LC3240_MinimumNumberofFlipstoMakeBinaryGridPalindromicII {
    /**
     * You are given an m x n binary matrix grid.
     *
     * A row or column is considered palindromic if its values read the same forward and backward.
     *
     * You can flip any number of cells in grid from 0 to 1, or from 1 to 0.
     *
     * Return the minimum number of cells that need to be flipped to make all rows and columns palindromic, and the
     * total number of 1's in grid divisible by 4.
     *
     * Input: grid = [[1,0,0],[0,1,0],[0,0,1]]
     *
     * Output: 3
     *
     * Input: grid = [[0,1],[0,1],[0,0]]
     *
     * Output: 2
     *
     * Input: grid = [[1],[1]]
     *
     * Output: 2
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m * n <= 2 * 10^5
     * 0 <= grid[i][j] <= 1
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(1)
    public int minFlips(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int one = 0;
                if (grid[i][j] == 1) one++;
                if (grid[i][n - 1 - j] == 1) one++;
                if (grid[m - 1 - i][j] == 1) one++;
                if (grid[m - 1 - i][n - 1 - j] == 1) one++;
                if (one % 4 == 0) continue;
                if (one % 2 == 1) res++;
                else res += 2;
            }
        }

        int x = 0, y = 0, z = 0;
        if (m % 2 == 1) {
            for (int j = 0; j < n / 2; j++) {
                int one = 0;
                if (grid[m / 2][j] == 1) one++;
                if (grid[m / 2][n - 1 - j] == 1) one++;
                if (one == 0) continue;
                if (one == 1) {
                    res++;
                    x++;
                } else y++;
            }
        }

        if (n % 2 == 1) {
            for (int i = 0; i < m / 2; i++) {
                int one = 0;
                if (grid[i][n / 2] == 1) one++;
                if (grid[m - 1 - i][n / 2] == 1) one++;
                if (one == 0) continue;
                if (one == 1) {
                    res++;
                    x++;
                } else y++;
            }
        }

        if (m % 2 == 1 && n % 2 == 1) {
            if (grid[m / 2][n / 2] == 1) z++;
        }

        int v = (x + y) * 2 + z;
        v %= 4;
        if (v == 0) return res;
        if (v % 2 == 1) {
            v--;
            res++;
        }
        if (v == 0) return res;
        if (x > 0) return res;
        return res + 2;
    }
}