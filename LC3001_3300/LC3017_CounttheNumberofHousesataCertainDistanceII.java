package LC3001_3300;

public class LC3017_CounttheNumberofHousesataCertainDistanceII {
    /**
     * You are given three positive integers n, x, and y.
     *
     * In a city, there exist houses numbered 1 to n connected by n streets. There is a street connecting the house
     * numbered i with the house numbered i + 1 for all 1 <= i <= n - 1 . An additional street connects the house
     * numbered x with the house numbered y.
     *
     * For each k, such that 1 <= k <= n, you need to find the number of pairs of houses (house1, house2) such that the
     * minimum number of streets that need to be traveled to reach house2 from house1 is k.
     *
     * Return a 1-indexed array result of length n where result[k] represents the total number of pairs of houses such
     * that the minimum streets required to reach one house from the other is k.
     *
     * Note that x and y can be equal.
     *
     * Input: n = 3, x = 1, y = 3
     * Output: [6,0,0]
     *
     * Input: n = 5, x = 2, y = 4
     * Output: [10,8,2,0,0]
     *
     * Input: n = 4, x = 1, y = 1
     * Output: [6,4,2,0]
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * 1 <= x, y <= n
     * @param n
     * @param x
     * @param y
     * @return
     */
    // time = O(n), space = O(1)
    public long[] countOfPairs(int n, int x, int y) {
        if (x > y) return countOfPairs(n, y, x);
        long[] res = new long[n];
        for (int i = 1; i <= n; i++) {
            res[0] += 2;
            res[Math.min(i - 1, Math.abs(i - y) + x)]--;
            res[Math.min(n - i, Math.abs(i - x) + 1 + n - y)]--;
            res[Math.min(Math.abs(i - x), Math.abs(i - y) + 1)]++;
            res[Math.min(Math.abs(i - x) + 1, Math.abs(i - y))]++;
            int r = Math.max(x - i, 0) + Math.max(i - y, 0);
            res[r + (y - x + 0) / 2]--;
            res[r + (y - x + 1) / 2]--;
        }
        for (int i = 1; i < n; i++) res[i] += res[i - 1];
        return res;
    }
}
/**
 * 我们画一个示意图，将图划分为ABC三个区域，其中[x,y]部分为C。
 * A-A-A-C(x)-C------C-C(y)-B-B-B
 *        |______________|
 * 任意两个房子之间的最短距离，可以落入如下六个分类之中, AA,BB,AC,BC,AB,CC.
 * 比如AC表示其中一个放在位于A区，另一个房子位于C区。
 * 我们分类讨论。
 * 1. AA：f1(t, a)
 * 2. BB: f1(t, b)
 * 3. AB: f2(t, a, b)
 * 4. AC: f3(t, a, (d-1) / 2, (d-1) - (d-1)/2)
 * 5. BC: f3(t, b, (d-1) / 2, (d-1) - (d-1)/2)
 * 6. CC: f4(t, d)
 *
 * 距离 = 两个编号之差
 * i 到 j 的距离 = abs(i - j)
 * 差分数组，把一堆连续的数字都+1
 *
 * i 到左边的房子：[1,i-1] + 1
 * i 到右边的房子：[i+1,n-i] + 1
 */