package LC1801_2100;
import java.util.*;
public class LC1992_FindAllGroupsofFarmland {
    /**
     * You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1
     * represents a hectare of farmland.
     *
     * To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland.
     * These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not
     * four-directionally adjacent to another farmland in a different group.
     *
     * land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right
     * corner of land is (m-1, n-1). Find the coordinates of the top left and bottom right corner of each group of
     * farmland. A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is
     * represented by the 4-length array [r1, c1, r2, c2].
     *
     * Return a 2D array containing the 4-length arrays described above for each group of farmland in land.
     * If there are no groups of farmland, return an empty array. You may return the answer in any order.
     *
     * Input: land = [[1,0,0],[0,1,1],[0,1,1]]
     * Output: [[0,0,0,0],[1,1,2,2]]
     *
     * Constraints:
     *
     * m == land.length
     * n == land[i].length
     * 1 <= m, n <= 300
     * land consists of only 0's and 1's.
     * Groups of farmland are rectangular in shape.
     * @param land
     * @return
     */
    public int[][] findFarmland(int[][] land) {
        // corner case
        if (land == null || land.length == 0 || land[0] == null || land[0].length == 0) return new int[0][0];

        List<int[]> res = new ArrayList<>();
        int m = land.length, n = land[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 1) {
                    helper(land, i, j, res);
                }
            }
        }
        int[][] ans = new int[res.size()][4];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }

    private void helper(int[][] land, int x, int y, List<int[]> res) {
        int m = land.length, n = land[0].length;
        int r = -1, c = -1;

        int i = x, j = y;
        for (j = y; j < n; j++) {
            if (land[i][j] == 0) break;
        }
        j--;

        for (i = x; i < m; i++) {
            if (land[i][j] == 1) land[i][j] = 0;
            else break;
        }

        i--;
        r = i;
        c = j;
        res.add(new int[]{x, y, r, c});

        for (i = x; i <= r; i++) {
            for (j = y; j <= c; j++) {
                land[i][j] = 0;
            }
        }
    }
}
