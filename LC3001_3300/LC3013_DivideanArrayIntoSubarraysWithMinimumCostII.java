package LC3001_3300;
import java.util.*;
public class LC3013_DivideanArrayIntoSubarraysWithMinimumCostII {
    /**
     * You are given a 0-indexed array of integers nums of length n, and two positive integers k and dist.
     *
     * The cost of an array is the value of its first element. For example, the cost of [1,2,3] is 1 while the cost of
     * [3,4,1] is 3.
     *
     * You need to divide nums into k disjoint contiguous
     * subarrays
     * , such that the difference between the starting index of the second subarray and the starting index of the kth
     * subarray should be less than or equal to dist. In other words, if you divide nums into the
     * subarrays nums[0..(i1 - 1)], nums[i1..(i2 - 1)], ..., nums[ik-1..(n - 1)], then ik-1 - i1 <= dist.
     *
     * Return the minimum possible sum of the cost of these subarrays.
     *
     * Input: nums = [1,3,2,6,4,2], k = 3, dist = 3
     * Output: 5
     *
     * Input: nums = [10,1,2,2,2,1], k = 4, dist = 3
     * Output: 15
     *
     * Input: nums = [10,8,18,9], k = 3, dist = 1
     * Output: 36
     *
     * Constraints:
     *
     * 3 <= n <= 10^5
     * 1 <= nums[i] <= 10^9
     * 3 <= k <= n
     * k - 2 <= dist <= n - 2
     * @param nums
     * @param k
     * @param dist
     * @return
     */
    // time = O(nlogn), space = O(n)
    TreeMap<Integer, Integer> minh, maxh;
    int k, sz;
    public long minimumCost(int[] nums, int k, int dist) {
        this.k = k;
        sz = 0;
        minh = new TreeMap<>();
        maxh = new TreeMap<>((o1, o2) -> o2 - o1);
        int n = nums.length;
        long res = (long)1e18, s = 0;
        for (int i = 1, j = 1; i < n; i++) {
            int x = nums[i];
            if (i - j + 1 <= k - 1) s += x;
            else {
                if (x < get()) s += x - get();
            }
            insert(x);
            if (i - j > dist) {
                int y = nums[j];
                if (minh.containsKey(y)) remove(minh, y);
                else {
                    remove(maxh, y);
                    sz--;
                    if (!minh.isEmpty()) {
                        int t = minh.firstKey();
                        remove(minh, t);
                        maxh.put(t, maxh.getOrDefault(t, 0) + 1);
                        sz++;
                    }
                }
                if (get() >= y) s += get() - y;
                j++;
            }
            if (i - j + 1 >= k - 1) res = Math.min(res, s);
        }
        return res + nums[0];
    }

    private void insert(int x) {
        minh.put(x, minh.getOrDefault(x, 0) + 1);
        int t = minh.firstKey();
        remove(minh, t);
        maxh.put(t, maxh.getOrDefault(t, 0) + 1);
        sz++;
        if (sz > k - 1) {
            t = maxh.firstKey();
            remove(maxh, t);
            sz--;
            minh.put(t, minh.getOrDefault(t, 0) + 1);
        }
    }

    private void remove(TreeMap<Integer, Integer> map, int x) {
        map.put(x, map.get(x) - 1);
        if (map.get(x) == 0) map.remove(x);
    }

    private int get() {
        return maxh.firstKey();
    }
}