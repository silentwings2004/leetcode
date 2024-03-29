package LC001_300;
import java.util.*;
public class LC33_SearchinRotatedSortedArray {
    /**
     * You are given an integer array nums sorted in ascending order (with distinct values), and an integer target.
     * <p>
     * Suppose that nums is rotated at some pivot unknown to you beforehand
     * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
     * <p>
     * If target is found in the array return its index, otherwise, return -1.
     * <p>
     * Input: nums = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 5000
     * -10^4 <= nums[i] <= 10^4
     * All values of nums are unique.
     * nums is guaranteed to be rotated at some pivot.
     * -10^4 <= target <= 10^4
     *
     * @param nums
     * @param target
     * @return
     */
    // S1: BS
    // time = O(logn), space = O(1)
    public int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) { // 先二分出分界点，再用二分找target!
            int mid = l + r + 1 >> 1;
            if (nums[mid] >= nums[0]) l = mid;
            else r = mid - 1;
        }

        if (target >= nums[0]) l = 0;
        else {
            l = r + 1;
            r = n - 1;
        }

        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return nums[r] == target ? r : -1;
    }

    // S2: BS
    // time = O(logn), space = O(1)
    public int search2(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            // 如果mid与target在同一个区间 -> 使用传统的二分法；否则就要改变left, right让它们收归在同一个区间内即可。
            if (nums[mid] >= nums[0] && target >= nums[0] || nums[mid] < nums[0] && target < nums[0]) {
                if (nums[mid] < target) left = mid + 1;
                else right = mid;
            } else if (nums[mid] >= nums[0]) left = mid + 1;
            else right = mid - 1;
        }
        return nums[left] == target ? left : -1;
    }
}
/**
 *     4 5 6 7 0 1 2      target = 0
 *     L     M L'  R
 *     nums[mid] v.s. target
 * if (nums[mid] < target) left = mid + 1;
 * else right = mid - 1;
 * 如何判断nums[mid]和target分别在哪个区间呢？
 * 敏锐的发现一个技巧：题目里隐含 -> 只要和nums[0]比较即可
 * 所有>=nums[0]的数肯定在左区间，而小于nums[0]的数肯定在右区间
 */
