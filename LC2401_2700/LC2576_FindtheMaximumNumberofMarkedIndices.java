package LC2401_2700;
import java.util.*;
public class LC2576_FindtheMaximumNumberofMarkedIndices {
    /**
     * You are given a 0-indexed integer array nums.
     *
     * Initially, all of the indices are unmarked. You are allowed to make this operation any number of times:
     *
     * Pick two different unmarked indices i and j such that 2 * nums[i] <= nums[j], then mark i and j.
     * Return the maximum possible number of marked indices in nums using the above operation any number of times.
     *
     * Input: nums = [3,5,2,4]
     * Output: 2
     *
     * Input: nums = [9,2,5,4]
     * Output: 4
     *
     * Input: nums = [7,6,8]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(nums, mid)) l = mid;
            else r = mid - 1;
        }
        return r % 2 == 0 ? r : r - 1;
    }

    private boolean check(int[] nums, int t) {
        int n = nums.length;
        for (int i = 0, j = n / 2; j < n;) {
            if (nums[i] * 2 <= nums[j]) {
                i++;
                j++;
                t -= 2;
                if (t <= 0) break;
            } else j++;
        }
        return t <= 0;
    }
}
