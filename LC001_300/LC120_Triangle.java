package LC001_300;
import java.util.*;
public class LC120_Triangle {
    /**
     * Given a triangle array, return the minimum path sum from top to bottom.
     *
     * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the
     * current row, you may move to either index i or index i + 1 on the next row.
     *
     * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
     * Output: 11
     *
     * Constraints:
     *
     * 1 <= triangle.length <= 200
     * triangle[0].length == 1
     * triangle[i].length == triangle[i - 1].length + 1
     * -10^4 <= triangle[i][j] <= 10^4
     *
     *
     * Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
     * @param triangle
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) f[n - 1][i] = triangle.get(n - 1).get(i);
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                f[i][j] = Math.min(f[i + 1][j], f[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return f[0][0];
    }
}
/**
 * 从下往上递推
 * f(i,j) = min{f(i+1, j), f(i+1,j+1)} + w
 */