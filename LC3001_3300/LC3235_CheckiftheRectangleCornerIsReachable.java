package LC3001_3300;

public class LC3235_CheckiftheRectangleCornerIsReachable {
    /**
     * You are given two positive integers X and Y, and a 2D array circles, where circles[i] = [xi, yi, ri] denotes a
     * circle with center at (xi, yi) and radius ri.
     *
     * There is a rectangle in the coordinate plane with its bottom left corner at the origin and top right corner at
     * the coordinate (X, Y). You need to check whether there is a path from the bottom left corner to the top right
     * corner such that the entire path lies inside the rectangle, does not touch or lie inside any circle, and touches
     * the rectangle only at the two corners.
     *
     * Return true if such a path exists, and false otherwise.
     *
     * Input: X = 3, Y = 4, circles = [[2,1,1]]
     *
     * Output: true
     *
     * Input: X = 3, Y = 3, circles = [[1,1,2]]
     *
     * Output: false
     *
     * Input: X = 3, Y = 3, circles = [[2,1,1],[1,2,1]]
     *
     * Output: false
     *
     * Constraints:
     *
     * 3 <= X, Y <= 10^9
     * 1 <= circles.length <= 1000
     * circles[i].length == 3
     * 1 <= xi, yi, ri <= 10^9
     * @param X
     * @param Y
     * @param circles
     * @return
     */
    // time = O(n^2 * logn), space = O(n)
    int[] p;
    public boolean canReachCorner(int X, int Y, int[][] circles) {
        int n = circles.length;
        p = new int[n + 2];
        for (int i = 0; i <= n + 1; i++) p[i] = i;
        for (int i = 0; i < n; i++) {
            int a = circles[i][0], b = circles[i][1], r = circles[i][2];
            if (a <= r || b + r >= Y) p[find(i)] = find(n); // 与左上相交
            if (b <= r || a + r >= X) p[find(i)] = find(n + 1); // 与右下相交
            for (int j = 0; j < i; j++) {
                int[] c = circles[j];
                if (1L * (a - c[0]) * (a - c[0]) + 1L * (b - c[1]) * (b - c[1]) <= 1L * (r + c[2]) * (r + c[2])) {
                    p[find(i)] = find(j);
                }
            }
            if (find(n) == find(n + 1)) return false;
        }
        return true;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
/**
 * 第 i 个圆抽象成节点 i (0 <= i <= n - 1)
 * 2. 左边界和上边界抽象成节点 n
 * 3. 右边界和下边界抽象成节点 n + 1
 * 4. 根据圆和圆，圆和矩形的相交/相切关系，在这些节点之间连边
 * 5. 答案就是 n 和 n + 1 没有连起来 (不在同一个连通块内)
 */