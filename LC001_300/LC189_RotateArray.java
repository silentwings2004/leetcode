package LC001_300;
import java.util.*;
public class LC189_RotateArray {
    /**
     * Given an array, rotate the array to the right by k steps, where k is non-negative.
     *
     * Input: nums = [1,2,3,4,5,6,7], k = 3
     * Output: [5,6,7,1,2,3,4]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -2^31 <= nums[i] <= 2^31 - 1
     * 0 <= k <= 10^5
     *
     *
     * Follow up:
     *
     * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
     * Could you do it in-place with O(1) extra space?
     * @param nums
     * @param k
     */
    // time = O(n), space = O(1)
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int t = nums[i];
            nums[i++] = nums[j];
            nums[j--] = t;
        }
    }
}