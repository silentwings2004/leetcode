package LC001_300;
import java.util.*;
public class LC122_BestTimetoBuyandSellStockII {
    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     *
     * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and
     * sell one share of the stock multiple times).
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy
     * again).
     *
     * Input: prices = [7,1,5,3,6,4]
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= prices.length <= 3 * 10^4
     * 0 <= prices[i] <= 10^4
     *
     * @param prices
     * @return
     */
    // time = O(n), space = O(1)
    public int maxProfit(int[] prices) {
        int n = prices.length, res = 0;
        for (int i = 0; i < n - 1; i++) {
            res += Math.max(0, prices[i + 1] - prices[i]);
        }
        return res;
    }
}
/**
 * 买入之前必须先要把手头上的股票都卖出
 * 把每一个交易都可以拆成一天天的个别交易
 * 只需要记录交易获益>0的那一天天交易即可！
 * Ref: AC1163 纪念品
 */