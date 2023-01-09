package LC1501_1800;

public class LC1752_CheckifArrayIsSortedandRotated {
    /**
     * Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some
     * number of positions (including zero). Otherwise, return false.
     *
     * There may be duplicates in the original array.
     *
     * Note: An array A rotated by x positions results in an array B of the same length such that
     * A[i] == B[(i+x) % A.length], where % is the modulo operation.
     *
     * Input: nums = [3,4,5,1,2]
     * Output: true
     *
     * Input: nums = [2,1,3,4]
     * Output: false
     *
     * Input: nums = [1,2,3]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public boolean check(int[] nums) {
        int n = nums.length, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[(i + 1) % n]) cnt++;
        }
        return cnt <= 1;
    }
}
