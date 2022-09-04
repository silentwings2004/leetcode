package LC2101_2400;
import java.util.*;
public class LC2386_FindtheKSumofanArray {
    /**
     * You are given an integer array nums and a positive integer k. You can choose any subsequence of the array and
     * sum all of its elements together.
     *
     * We define the K-Sum of the array as the kth largest subsequence sum that can be obtained (not necessarily distinct).
     *
     * Return the K-Sum of the array.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
     * the order of the remaining elements.
     *
     * Note that the empty subsequence is considered to have a sum of 0.
     *
     * Input: nums = [2,4,-2], k = 5
     * Output: 2
     *
     * Input: nums = [1,-2,3,4,-10,12], k = 16
     * Output: 10
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 10^5
     * -109 <= nums[i] <= 10^9
     * 1 <= k <= min(2000, 2^n)
     * @param nums
     * @param k
     * @return
     */
    // S1: PQ
    // time = O(nlogn), space = O(n)
    public long kSum(int[] nums, int k) {
        int n = nums.length;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) sum += nums[i];
            else nums[i] = -nums[i];
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o2[0], o1[0]));
        Arrays.sort(nums);

        long res = sum;
        pq.offer(new long[]{sum - nums[0], 0});
        k--;

        while (k > 0) {
            long[] cur = pq.poll();
            res = cur[0];
            int idx = (int) cur[1];
            if (idx < n - 1) {
                pq.offer(new long[]{cur[0] + nums[idx] - nums[idx + 1], idx + 1}); // pick idx item, try idx + 1
                pq.offer(new long[]{cur[0] - nums[idx + 1], idx + 1}); // not pick idx item, replace it with idx + 1
            }
            k--;
        }
        return res;
    }
}
/**
 * 多路归并算法
 * 只用保留最大的k个数，只需要归并出来前k个
 * 每个数枚举一遍
 * 倒着做，先算下最大的和，把所有正数相加
 * 要把总和变小，变小的话就要和最大和做差值
 * => 从剩下的数里凑出最小的k个数，要么+负数，要么-正数
 * 从小到大排序，最多只会选前k个数 => O(nk) = O(k^2)
 */