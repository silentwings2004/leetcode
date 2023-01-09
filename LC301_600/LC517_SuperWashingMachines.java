package LC301_600;

public class LC517_SuperWashingMachines {
    /**
     * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
     *
     * For each move, you could choose any m (1 <= m <= n) washing machines, and pass one dress of each washing machine
     * to one of its adjacent washing machines at the same time.
     *
     * Given an integer array machines representing the number of dresses in each washing machine from left to right on
     * the line, return the minimum number of moves to make all the washing machines have the same number of dresses.
     * If it is not possible to do it, return -1.
     *
     * Input: machines = [1,0,5]
     * Output: 3
     *
     * Input: machines = [0,3,0]
     * Output: 2
     *
     * Input: machines = [0,2,0]
     * Output: -1
     *
     * Constraints:
     *
     * n == machines.length
     * 1 <= n <= 10^4
     * 0 <= machines[i] <= 10^5
     * @param machines
     * @return
     */
    // time = O(n), space = O(1)
    public int findMinMoves(int[] machines) {
        int sum = 0, n = machines.length;
        for (int x : machines) sum += x;
        if (sum % n != 0) return -1;
        int avg = sum / n, left = 0, right = sum, res = 0;
        for (int i = 0; i < n; i++) {
            right -= machines[i];
            if (i > 0) left += machines[i - 1];
            int l = Math.max(avg * i - left, 0);
            int r = Math.max(avg * (n - i - 1) - right, 0);
            res = Math.max(res, l + r);
        }
        return res;
    }
}
/**
 * n | sum
 * 贪心问题
 * 每步操作只能弥补左边的一个空缺或者右边的一个空缺
 * 操作数 >= left + right
 */