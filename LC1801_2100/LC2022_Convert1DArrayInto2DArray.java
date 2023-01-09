package LC1801_2100;

public class LC2022_Convert1DArrayInto2DArray {
    /**
     * You are given a 0-indexed 1-dimensional (1D) integer array original, and two integers, m and n. You are tasked
     * with creating a 2-dimensional (2D) array with m rows and n columns using all the elements from original.
     *
     * The elements from indices 0 to n - 1 (inclusive) of original should form the first row of the constructed 2D
     * array, the elements from indices n to 2 * n - 1 (inclusive) should form the second row of the constructed 2D
     * array, and so on.
     *
     * Return an m x n 2D array constructed according to the above procedure, or an empty 2D array if it is impossible.
     *
     * Input: original = [1,2,3,4], m = 2, n = 2
     * Output: [[1,2],[3,4]]
     *
     * Constraints:
     *
     * 1 <= original.length <= 5 * 10^4
     * 1 <= original[i] <= 10^5
     * 1 <= m, n <= 4 * 10^4
     * @param original
     * @param m
     * @param n
     * @return
     */
    // time = O(m * n), space = O(1)
    public int[][] construct2DArray(int[] original, int m, int n) {
        // corner case
        if (original == null || original.length == 0) return new int[0][0];

        int k = original.length;
        if (k != m * n) return new int[0][0];

        int[][] res = new int[m][n];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = original[idx++];
            }
        }
        return res;
    }
}
