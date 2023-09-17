package LC2700_3000;

import java.util.HashSet;

public class LC2709_GreatestCommonDivisorTraversal {
    /**
     * You are given a 0-indexed integer array nums, and you are allowed to traverse between its indices. You can
     * traverse between index i and index j, i != j, if and only if gcd(nums[i], nums[j]) > 1, where gcd is the greatest
     * common divisor.
     *
     * Your task is to determine if for every pair of indices i and j in nums, where i < j, there exists a sequence of
     * traversals that can take us from i to j.
     *
     * Return true if it is possible to traverse between all such pairs of indices, or false otherwise.
     *
     * Input: nums = [2,3,6]
     * Output: true
     *
     * Input: nums = [3,9,5]
     * Output: false
     *
     * Input: nums = [4,3,12,8]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int N = 100010;
    int[] p, q;
    public boolean canTraverseAllPairs(int[] nums) {
        int n = nums.length;
        if (n == 1) return true;

        p = new int[N];
        q = new int[N];
        for (int x : nums) p[x] = x;
        for (int x : nums) get_primes(x);

        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) {
            if (x == 1) return false;
            set.add(find(x));
        }
        return set.size() == 1;
    }

    private void get_primes(int x) {
        int t = x;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) {
                if (q[i] == 0) q[i] = t;
                else if (find(t) != find(q[i])) {
                    p[find(t)] = find(q[i]);
                }
                while (x % i == 0) x /= i;
            }
        }
        if (x > 1) {
            if (q[x] == 0) q[x] = t;
            else if (find(t) != find(q[x])) p[find(t)] = find(q[x]);
        }
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}