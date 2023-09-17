package LC2700_3000;
import java.util.*;
public class LC2780_MinimumIndexofaValidSplit {
    /**
     * An element x of an integer array arr of length m is dominant if freq(x) * 2 > m, where freq(x) is the number of
     * occurrences of x in arr. Note that this definition implies that arr can have at most one dominant element.
     *
     * You are given a 0-indexed integer array nums of length n with one dominant element.
     *
     * You can split nums at an index i into two arrays nums[0, ..., i] and nums[i + 1, ..., n - 1], but the split is
     * only valid if:
     *
     * 0 <= i < n - 1
     * nums[0, ..., i], and nums[i + 1, ..., n - 1] have the same dominant element.
     * Here, nums[i, ..., j] denotes the subarray of nums starting at index i and ending at index j, both ends being
     * inclusive. Particularly, if j < i then nums[i, ..., j] denotes an empty subarray.
     *
     * Return the minimum index of a valid split. If no valid split exists, return -1.
     *
     * Input: nums = [1,2,2,2]
     * Output: 2
     *
     * Input: nums = [2,1,3,1,1,1,7,1,2,1]
     * Output: 4
     *
     * Input: nums = [3,3,3,3,7,2,2]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * nums has exactly one dominant element.
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int minimumIndex(List<Integer> nums) {
        int n = nums.size(), mc = 0, t = nums.get(0);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            if (map.get(x) > mc) {
                mc = map.get(x);
                t = x;
            }
        }

        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + (nums.get(i - 1) == t ? 1 : 0);
        for (int i = 0; i < n; i++) {
            int fl = i + 1, bl = n - fl;
            int fc = s[i + 1], bc = s[n] - fc;
            if (2 * fc > fl && 2 * bc > bl) return i;
        }
        return -1;
    }
}