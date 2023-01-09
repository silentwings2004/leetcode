package LC2401_2700;
import java.util.*;
public class LC2412_MinimumMoneyRequiredBeforeTransactions {
    /**
     * You are given a 0-indexed 2D integer array transactions, where transactions[i] = [costi, cashbacki].
     *
     * The array describes transactions, where each transaction must be completed exactly once in some order. At any
     * given moment, you have a certain amount of money. In order to complete transaction i, money >= costi must hold
     * true. After performing a transaction, money becomes money - costi + cashbacki.
     *
     * Return the minimum amount of money required before any transaction so that all of the transactions can be
     * completed regardless of the order of the transactions.
     *
     * Input: transactions = [[2,1],[5,0],[4,2]]
     * Output: 10
     *
     * Input: transactions = [[3,0],[0,3]]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= transactions.length <= 10^5
     * transactions[i].length == 2
     * 0 <= costi, cashbacki <= 10^9
     * @param transactions
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public long minimumMoney(int[][] transactions) {
        long sum = 0;
        for (int[] x : transactions) {
            int a = x[0], b = x[1];
            if (a > b) sum += a - b;
        }

        long res = 0;
        for (int[] x : transactions) {
            int a = x[0], b = x[1];
            long s = sum;
            if (a > b) s -= a - b;
            res = Math.max(res, s + a);
        }
        return res;
    }

    // S2
    // time = O(nlogn), sapce = O(1)
    public long minimumMoney2(int[][] transactions) {
        Arrays.sort(transactions, (o1, o2) -> o1[1] - o2[1]);

        long cur = 0, maxCostForNetGain = 0, hisLow = 0;
        for (int[] x : transactions) {
            long cost = x[0], back = x[1];
            if (cost <= back) maxCostForNetGain = Math.max(maxCostForNetGain, cost);
            else {
                cur -= cost;
                hisLow = Math.min(hisLow, cur);
                cur += back;
            }
        }
        cur -= maxCostForNetGain;
        hisLow = Math.min(hisLow, cur);
        return Math.abs(hisLow);
    }
}
/**
 * money >= (a0 - b0) + (a1 - b1) + ... + (ai-1 - bi-1) + ai
 * 枚举下哪笔交易的cost作为全局交易的最大值。
 * 然后目标让前面越大越好 => 看哪些交易ai > bi
 * 把所有a > b的交易相加，即可得到前面的最大值
 *
 * 把cost高的放在前面
 * netLoss大的放在前面
 * netGain transactions: order by cost in descending order
 * netLoss transactions: max{cost[i] - (Totalloss - (back[i] - cost[i]))}
 * => max {back[i] - TotalLoss}  TotalLoss is fixed => cashback最大的放在后面
 * 只需要按照cashback从小到大排序
 */