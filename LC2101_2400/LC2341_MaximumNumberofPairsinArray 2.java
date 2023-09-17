package LC2101_2400;
import java.util.*;
public class LC2341_MaximumNumberofPairsinArray {
    /**
     * You are given a 0-indexed integer array nums. In one operation, you may do the following:
     *
     * Choose two integers in nums that are equal.
     * Remove both integers from nums, forming a pair.
     * The operation is done on nums as many times as possible.
     *
     * Return a 0-indexed integer array answer of size 2 where answer[0] is the number of pairs that are formed and
     * answer[1] is the number of leftover integers in nums after doing the operation as many times as possible.
     *
     * Input: nums = [1,3,2,1,3,2,2]
     * Output: [3,1]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int[] numberOfPairs(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);
        int[] res = new int[2];
        for (int v : map.values()) {
            res[0] += v / 2;
            res[1] += v % 2;
        }
        return res;
    }
}
