package LC1201_1500;
import java.util.*;
public class LC1248_LeastNumberofUniqueIntegersafterKRemovals {
    /**
     * Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers
     * on it.
     *
     * Return the number of nice sub-arrays.
     *
     * Input: nums = [1,1,2,1,1], k = 3
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50000
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] presum = new int[n];
        presum[0] = (nums[0] % 2 == 1 ? 1 : 0);
        for (int i = 1; i < n; i++) presum[i] = presum[i - 1] + (nums[i] % 2 == 1 ? 1 : 0);

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            int diff = presum[i] - k;
            res += map.getOrDefault(diff, 0);
            map.put(presum[i], map.getOrDefault(presum[i], 0) + 1);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int numberOfSubarrays2(int[] nums, int k) {
        int n = nums.length, res = 0, last = -1;
        for (int i = 0, j = -1, cnt = 0; i < n; i++) {
            cnt += nums[i] % 2;
            if (nums[i] % 2 == 1 && j == -1) j = i;
            if (cnt > k) {
                last = j;
                while (cnt > k) cnt -= nums[j++] % 2;
                while (nums[j] % 2 == 0) j++;
            }
            if (cnt == k) res += j - last;
        }
        return res;
    }
}
