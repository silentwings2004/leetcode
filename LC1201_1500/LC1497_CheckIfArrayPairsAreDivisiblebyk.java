package LC1201_1500;
import java.util.*;
public class LC1497_CheckIfArrayPairsAreDivisiblebyk {
    /**
     * Given an array of integers arr of even length n and an integer k.
     *
     * We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
     *
     * Return true If you can find a way to do that or false otherwise.
     *
     * Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
     * Output: true
     *
     * Input: arr = [1,2,3,4,5,6], k = 7
     * Output: true
     *
     * Input: arr = [1,2,3,4,5,6], k = 10
     * Output: false
     *
     * Constraints:
     *
     * arr.length == n
     * 1 <= n <= 10^5
     * n is even.
     * -109 <= arr[i] <= 10^9
     * 1 <= k <= 10^5
     * @param arr
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public boolean canArrange(int[] arr, int k) {
        int n = arr.length;
        long s = 0;
        for (int x : arr) s += x;
        if (s % k != 0) return false;
        HashMap<Integer, Integer> map = new HashMap();
        int res = 0;
        for (int x : arr) {
            int r = (x % k + k) % k, t = (k - r) % k;
            if (map.containsKey(t)) {
                map.put(t, map.get(t) - 1);
                if (map.get(t) == 0) map.remove(t);
            } else map.put(r, map.getOrDefault(r, 0) + 1);
        }
        return map.isEmpty();
    }
}
