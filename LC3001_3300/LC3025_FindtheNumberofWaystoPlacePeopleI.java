package LC3001_3300;
import java.util.*;
public class LC3025_FindtheNumberofWaystoPlacePeopleI {
    /**
     * You are given a 2D array points of size n x 2 representing integer coordinates of some points on a 2D-plane,
     * where points[i] = [xi, yi].
     *
     * We define the right direction as positive x-axis (increasing x-coordinate) and the left direction as negative
     * x-axis (decreasing x-coordinate). Similarly, we define the up direction as positive y-axis (increasing
     * y-coordinate) and the down direction as negative y-axis (decreasing y-coordinate)
     *
     * You have to place n people, including Chisato and Takina, at these points such that there is exactly one person
     * at every point. Chisato wants to be alone with Takina, so Chisato will build a rectangular fence with Chisato's
     * position as the upper left corner and Takina's position as the lower right corner of the fence (Note that the
     * fence might not enclose any area, i.e. it can be a line). If any person other than Chisato and Takina is either
     * inside the fence or on the fence, Chisato will be sad.
     *
     * Return the number of pairs of points where you can place Chisato and Takina, such that Chisato does not become
     * sad on building the fence.
     *
     * Note that Chisato can only build a fence with Chisato's position as the upper left corner, and Takina's position
     * as the lower right corner. For example, Chisato cannot build either of the fences in the picture below with four
     * corners (1, 1), (1, 3), (3, 1), and (3, 3), because:
     *
     * With Chisato at (3, 3) and Takina at (1, 1), Chisato's position is not the upper left corner and Takina's
     * position is not the lower right corner of the fence.
     * With Chisato at (1, 3) and Takina at (1, 1), Takina's position is not the lower right corner of the fence.
     *
     * Input: points = [[1,1],[2,2],[3,3]]
     * Output: 0
     *
     * Input: points = [[6,2],[4,4],[2,6]]
     * Output: 2
     *
     * Input: points = [[3,1],[1,3],[1,1]]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= n <= 50
     * points[i].length == 2
     * 0 <= points[i][0], points[i][1] <= 50
     * All points[i] are distinct.
     * @param points
     * @return
     */
    // time = O(n^3), space = O(logn)
    public int numberOfPairs(int[][] points) {
        int n = points.length, res = 0;
        Arrays.sort(points, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (points[i][0] <= points[j][0] && points[i][1] >= points[j][1]) {
                    boolean flag = true;
                    for (int k = i + 1; k < j; k++) {
                        if (points[k][0] >= points[i][0] && points[k][0] <= points[j][0] && points[k][1] >= points[j][1] && points[k][1] <= points[i][1]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) res++;
                }
            }
        }
        return res;
    }
}