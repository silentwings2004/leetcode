package LC301_600;
import java.util.*;
public class LC311_SparseMatrixMultiplication {
    /**
     * Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the result of mat1 x mat2.
     * You may assume that multiplication is always possible.
     *
     * Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
     * Output: [[7,0,0],[-7,0,3]]
     *
     * Constraints:
     *
     * m == mat1.length
     * k == mat1[i].length == mat2.length
     * n == mat2[i].length
     * 1 <= m, n, k <= 100
     * -100 <= mat1[i][j], mat2[i][j] <= 100
     * @param mat1
     * @param mat2
     * @return
     */
    // time = O(m * k * n), space = O(1)
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length, k = mat1[0].length, n = mat2[0].length;
        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int l = 0; l < k; l++) {
                if (mat1[i][l] != 0) {
                    for (int j = 0; j < n; j++) {
                        if (mat2[l][j] != 0) {
                            res[i][j] += mat1[i][l] * mat2[l][j];
                        }
                    }
                }
            }
        }
        return res;
    }

    // S2
    // time = O(m * n), space = O(m * n)
    public int[][] multiply2(int[][] mat1, int[][] mat2) {
        int m = mat1.length, k = mat1[0].length, n = mat2[0].length;
        List<int[]>[] q1 = new List[m];
        List<int[]>[] q2 = new List[n];
        for (int i = 0; i < m; i++) q1[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) q2[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) {
                if (mat1[i][j] != 0) {
                    q1[i].add(new int[]{j, mat1[i][j]});
                }
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < k; i++) {
                if (mat2[i][j] != 0) {
                    q2[j].add(new int[]{i, mat2[i][j]});
                }
            }
        }

        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = dotProduct(q1[i], q2[j]);
            }
        }
        return res;
    }

    private int dotProduct(List<int[]> vec1, List<int[]> vec2) {
        int m = vec1.size(), n = vec2.size();
        int res = 0;
        for (int i = 0, j = 0; i < m && j < n;) {
            if (vec1.get(i)[0] == vec2.get(j)[0]) {
                res += vec1.get(i)[1] * vec2.get(j)[1];
                i++;
                j++;
            } else if (vec1.get(i)[0] > vec2.get(j)[0]) j++;
            else i++;
        }
        return res;
    }
}