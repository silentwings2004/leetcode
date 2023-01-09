package LC1201_1500;

public class LC1464_MaximumProductofTwoElementsinanArray {
    /**
     * Given the array of integers nums, you will choose two different indices i and j of that array. Return the
     * maximum value of (nums[i]-1)*(nums[j]-1).
     *
     * Input: nums = [3,4,5,2]
     * Output: 12
     *
     * Constraints:
     *
     * 2 <= nums.length <= 500
     * 1 <= nums[i] <= 10^3
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int maxProduct(int[] nums) {
        int max = nums[0], sec = 0, n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] > max) {
                sec = max;
                max = nums[i];
            } else if (nums[i] > sec) sec = nums[i];
        }
        return (max - 1) * (sec - 1);
    }
}
