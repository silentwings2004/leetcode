package LC2401_2700;

public class LC2426_NumberofPairsSatisfyingInequality {
    /**
     * You are given two 0-indexed integer arrays nums1 and nums2, each of size n, and an integer diff. Find the number
     * of pairs (i, j) such that:
     *
     * 0 <= i < j <= n - 1 and
     * nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
     * Return the number of pairs that satisfy the conditions.
     *
     * Input: nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
     * Output: 3
     *
     * Input: nums1 = [3,-1], nums2 = [-2,2], diff = -1
     * Output: 0
     *
     * Constraints:
     *
     * n == nums1.length == nums2.length
     * 2 <= n <= 10^5
     * -10^4 <= nums1[i], nums2[i] <= 10^4
     * -10^4 <= diff <= 10^4
     * @param nums1
     * @param nums2
     * @param diff
     * @return
     */
    // time = O(nlogn), space = O(n)
    int n = 50050;
    int[] tr;
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        tr = new int[n];
        int m = nums1.length;
        long res = 0;

        for (int i = 0; i < m; i++) {
            int x = nums1[i] - nums2[i] + 20010;
            res += query(x + diff);
            add(x, 1);
        }
        return res;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, int v) {
        for (int i = x; i <= n; i += lowbit(i)) tr[i] += v;
    }

    private long query(int x) {
        long res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res += tr[i];
        return res;
    }
}