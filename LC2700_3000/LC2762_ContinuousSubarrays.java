package LC2700_3000;
import java.util.*;
public class LC2762_ContinuousSubarrays {
    /**
     * You are given a 0-indexed integer array nums. A subarray of nums is called continuous if:
     *
     * Let i, i + 1, ..., j be the indices in the subarray. Then, for each pair of indices i <= i1, i2 <= j,
     * 0 <= |nums[i1] - nums[i2]| <= 2.
     * Return the total number of continuous subarrays.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [5,4,2,4]
     * Output: 8
     *
     * Input: nums = [1,2,3]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long continuousSubarrays(int[] nums) {
        int n = nums.length;
        long res = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0, j = 0; i < n; i++) {
            while (map.size() > 0 && (Math.abs(nums[i] - map.firstKey()) > 2 || Math.abs(nums[i] - map.lastKey()) > 2)) {
                int x = nums[j++];
                map.put(x, map.get(x) - 1);
                if (map.get(x) == 0) map.remove(x);
            }
            res += i - j + 1;
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return res;
    }
}