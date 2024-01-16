package LC2700_3000;

public class LC2997_MinimumNumberofOperationstoMakeArrayXOREqualtoK {
    /**
     * You are given a 0-indexed integer array nums and a positive integer k.
     *
     * You can apply the following operation on the array any number of times:
     *
     * Choose any element of the array and flip a bit in its binary representation. Flipping a bit means changing a 0
     * to 1 or vice versa.
     * Return the minimum number of operations required to make the bitwise XOR of all elements of the final array equal
     * to k.
     *
     * Note that you can flip leading zero bits in the binary representation of elements. For example, for the number
     * (101)2 you can flip the fourth bit and obtain (1101)2.
     *
     * Input: nums = [2,1,3,4], k = 1
     * Output: 2
     *
     * Input: nums = [2,0,2,0], k = 0
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^6
     * 0 <= k <= 10^6
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int minOperations(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < 21; i++) {
            int s = 0, t = k >> i & 1;
            for (int x : nums) s ^= x >> i & 1;
            if (s != t) res++;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int minOperations2(int[] nums, int k) {
        for (int x : nums) k ^= x;
        return Integer.bitCount(k);
    }
}