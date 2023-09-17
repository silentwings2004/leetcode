package LC2700_3000;

public class LC2834_FindtheMinimumPossibleSumofaBeautifulArray {
    /**
     * You are given positive integers n and target.
     *
     * An array nums is beautiful if it meets the following conditions:
     *
     * nums.length == n.
     * nums consists of pairwise distinct positive integers.
     * There doesn't exist two distinct indices, i and j, in the range [0, n - 1], such that nums[i] + nums[j] == target.
     * Return the minimum possible sum that a beautiful array could have.
     *
     * Input: n = 2, target = 3
     * Output: 4
     *
     * Input: n = 3, target = 3
     * Output: 8
     *
     * Input: n = 1, target = 1
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 1 <= target <= 10^5
     * @param n
     * @param target
     * @return
     */
    // time = O(1), space = O(1)
    public long minimumPossibleSum(int n, int target) {
        long m = Math.min(target / 2, n);
        return (1 + m) * m / 2 + (target + target + n - m - 1) * (n - m) / 2;
    }
}