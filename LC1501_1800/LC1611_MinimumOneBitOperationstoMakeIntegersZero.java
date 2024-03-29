package LC1501_1800;
import java.util.*;
public class LC1611_MinimumOneBitOperationstoMakeIntegersZero {
    /**
     * Given an integer n, you must transform it into 0 using the following operations any number of times:
     *
     * Change the rightmost (0th) bit in the binary representation of n.
     * Change the ith bit in the binary representation of n if the (i-1)th bit is set to 1 and the (i-2)th through 0th
     * bits are set to 0.
     * Return the minimum number of operations to transform n into 0.
     *
     * Input: n = 6
     * Output: 4
     *
     * Constraints:
     *
     * 0 <= n <= 10^9
     * @param n
     * @return
     */
    // S1: recursion
    // time = O((logn)^2), space = O(logn)
    HashMap<String, Integer> mem, mem2;
    public int minimumOneBitOperations(int n) {
        mem = new HashMap<>();
        mem2 = new HashMap<>();
        return dfs(Integer.toBinaryString(n));
    }

    private int dfs(String s) {
        if (s.equals("0")) return 0;
        if (s.equals("1")) return 1;
        if (mem.containsKey(s)) return mem.get(s);

        int v = 0;
        if (s.charAt(0) == '0') v = dfs(s.substring(1));
        else {
            String t = s.substring(1);
            StringBuilder sb = new StringBuilder();
            sb.append('1').append("0".repeat(t.length() - 1));
            v = 1 + helper(t) + dfs(sb.toString());
        }
        mem.put(s, v);
        return v;
    }

    private int helper(String s) {
        if (s.equals("0")) return 1;
        if (s.equals("1")) return 0;
        if (mem2.containsKey(s)) return mem2.get(s);

        int v = 0;
        if (s.charAt(0) == '1') v = dfs(s.substring(1));
        else {
            String t = s.substring(1);
            StringBuilder sb = new StringBuilder();
            sb.append('1').append("0".repeat(t.length() - 1));
            v = 1 + helper(t) + dfs(sb.toString());
        }
        mem2.put(s, v);
        return v;
    }

    // S2: Gray code
    // time = O(1), space = O(1)
    public int minimumOneBitOperations2(int n) {
        if (n == 0) return 0;
        String s = Integer.toBinaryString(n);
        int i = 0;
        while (i < s.length() && s.charAt(i) == '0') i++;
        s = s.substring(i);

        int last = 0, ans = 0;
        for (i = 0; i < s.length(); i++) {
            int x = 0;
            if (s.charAt(i) == '1') x = last ^ 1;
            else x = last;
            last = x;
            ans = ans * 2 + x;
        }
        return ans;
    }
}
/**
 * 101011 => 000000
 * 1(xxxxx) => => 1(10000) => 0(10000) => 0(00000)
 *        helper(xxxxx)    +1       + dfs(10000)
 * helper(xxxxx): the operations required to convert xxxxx to 100000
 * dfs(1xxxxx) = helper(xxxxx) + 1 + dfs(10000)
 * 1. 1xxxx: dfs(xxxx)
 * 2. 0(xxxx) -> 0(1000) -> 1(1000) -> 1(0000)
 *          helper(xxxx) +1  + dfs(1000)
 *
 * S2:
 * 110 -> 010 -> 011 -> 001 -> 000   adjacent two numbers only differ by one bit
 * 这个序列就是个格雷码 ref: LC89
 * n: 2^n
 * 00...00 -> -> -> -> 每个序列相邻的只differ by 1 bit，而且最后1位和第1位也只相差1个bit
 * 000，001，011，010，110，111，101，100
 *  110->010->011->001->000
 *  这道题本质上就是在n-bit的格雷码里排第几个
 *  关于格雷码的生成方法，可以参见089. Gray Code的解答。我们有格雷码的通项公式
 *  for (int i=0; i<(1<<n); i++)
 *     gray[i] = i^(i>>1);
 * 在本题中，我们只要找到n在格雷码序列中的位置（即对应的索引i），就可以判定它与0之间的距离。
 * 那么如何从gray[i]反求出i呢？
 * 其实很简单，假设i的二进制表达是 abcdef，那么
 *   abcdef
 * ^ 0abcde
 * ---------
 *   xxxxxx (binary string of n)
 *
 */
