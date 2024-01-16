package LC2700_3000;
import java.util.*;
public class LC2944_MinimumNumberofCoinsforFruits {
    /**
     * You are at a fruit market with different types of exotic fruits on display.
     *
     * You are given a 1-indexed array prices, where prices[i] denotes the number of coins needed to purchase the ith
     * fruit.
     *
     * The fruit market has the following offer:
     *
     * If you purchase the ith fruit at prices[i] coins, you can get the next i fruits for free.
     * Note that even if you can take fruit j for free, you can still purchase it for prices[j] coins to receive a new
     * offer.
     *
     * Return the minimum number of coins needed to acquire all the fruits.
     *
     * Input: prices = [3,1,2]
     * Output: 4
     *
     * Input: prices = [1,10,1,1]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= prices.length <= 1000
     * 1 <= prices[i] <= 10^5
     * @param prices
     * @return
     */
    // time = O(n^2), space = O(n)
    public int minimumCoins(int[] prices) {
        int n = prices.length, inf = (int)1e9;
        int[][] f = new int[n + 1][2];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], inf);
        f[0][0] = f[0][1] = 0;
        for (int i = 1; i <= n; i++) {
            f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]) + prices[i - 1];
            for (int j = 1; j < i; j++) {
                if (j + j >= i) {
                    f[i][0] = Math.min(f[i][0], f[j][1]);
                }
            }
        }
        return Math.min(f[n][0], f[n][1]);
    }
}