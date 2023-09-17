package LC2700_3000;
import java.util.*;
public class LC2799_CountCompleteSubarraysinanArray {
    /**
     * You are given an array nums consisting of positive integers.
     *
     * We call a subarray of an array complete if the following condition is satisfied:
     *
     * The number of distinct elements in the subarray is equal to the number of distinct elements in the whole array.
     * Return the number of complete subarrays.
     *
     * A subarray is a contiguous non-empty part of an array.
     *
     * Input: nums = [1,3,1,2,2]
     * Output: 4
     *
     * Input: nums = [5,5,5,5]
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 2000
     * @param nums
     * @return
     */
    // S1
    // time = O(n^2), space = O(n)
    public int countCompleteSubarrays(int[] nums) {
        int n = nums.length;
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        int cnt = set.size();

        int res = 0;
        for (int i = 0; i < n; i++) {
            set.clear();
            for (int j = i; j < n; j++) {
                set.add(nums[j]);
                if (set.size() == cnt) res++;
            }
        }
        return res;
    }

    // S2: sliding window
    // time = O(n), space = O(n)
    public int countCompleteSubarrays2(int[] nums) {
        int n = nums.length;
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        int cnt = set.size();

        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            while (map.size() == cnt) {
                int x = nums[j++];
                map.put(x, map.get(x) - 1);
                if (map.get(x) == 0) map.remove(x);
            }
            res += j;
        }
        return res;
    }
}