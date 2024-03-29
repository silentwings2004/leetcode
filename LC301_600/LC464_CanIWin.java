package LC301_600;

public class LC464_CanIWin {
    /**
     * In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10. The player who
     * first causes the running total to reach or exceed 100 wins.
     *
     * What if we change the game so that players cannot re-use integers?
     *
     * For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement
     * until they reach a total >= 100.
     *
     * Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move can force a win,
     * otherwise, return false. Assume both players play optimally.
     *
     * Input: maxChoosableInteger = 10, desiredTotal = 11
     * Output: false
     *
     * Constraints:
     *
     * 1 <= maxChoosableInteger <= 20
     * 0 <= desiredTotal <= 300
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    // S1
    // time = O(2^n), space = O(2^n)
    private int[] visited; // must win -> 2, must lose -> 1
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int totalSum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (totalSum < desiredTotal) return false; // 及早判断

        visited = new int[1 << (maxChoosableInteger + 1)]; // visited = new int[1 << 21];
        return dfs(0, 0, maxChoosableInteger, desiredTotal);
    }

    private boolean dfs(int state, int sum, int maxChoosableInteger, int desiredTotal) {
        if (visited[state] == 2) return true;
        if (visited[state] == 1) return false;

        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (((state >> i) & 1) == 1) continue; // has already been chosen
            if (sum + i >= desiredTotal) {
                visited[state] = 2;
                return true;
            }

            if (!dfs(state | (1 << i), sum + i, maxChoosableInteger, desiredTotal)) { // opponent must lose!
                visited[state] = 2;
                return true;
            }
        }
        visited[state] = 1;
        return false;
    }

    // S2
    // time = O(n * 2^n), space = O(2^n)
    Boolean[] f;
    int n, m;
    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        n = maxChoosableInteger;
        m = desiredTotal;
        if (n * (n + 1) / 2 < m) return false;
        f = new Boolean[1 << n];
        return dp(0);
    }

    private boolean dp(int x) {
        if (f[x] != null) return f[x];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if ((x >> i & 1) == 1) sum += i + 1;
        }
        for (int i = 0; i < n; i++) {
            if ((x >> i & 1) == 1) continue;
            if (sum + i + 1 >= m) return f[x] = true;
            if (!dp(x + (1 << i))) return f[x] = true;
        }
        return f[x] = false;
    }
}
/**
 * 决策问题：甲方乙方互相博弈的问题，eg. stone game
 * A: 1 => B: win
 *    2 => B: win
 *    ..
 *    4 => B: 0 => A: win
 *            1 => A: win
 *            ...
 *              => A: win
 * 我会选一个让对手必输的option
 * 递归 => bool
 * use state compression
 * 0 ~ 20的全排列 = 20! => 记忆化
 * 真正独立变量是state
 *
 * 用一个二进制数去表示状态，1表示用过了这个数，0表示没用过
 * f(state) 必胜或者必败
 */
