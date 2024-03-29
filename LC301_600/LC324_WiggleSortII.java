package LC301_600;
import java.util.*;
public class LC324_WiggleSortII {
    /**
     * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
     *
     * You may assume the input array always has a valid answer.
     *
     * Input: nums = [1,5,1,1,6,4]
     * Output: [1,6,1,5,1,4]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^4
     * 0 <= nums[i] <= 5000
     * It is guaranteed that there will be an answer for the given input nums.
     *
     * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
     * @param nums
     */
    // S1: Sort
    // time = O(nlogn), space = O(n)
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int i = (n - 1) / 2, j = n - 1;

        int flag = 0, idx = 0;
        int[] res = new int[n];
        for (int k = 0; k < n ; k++){
            if (flag == 0) res[idx++] = nums[i--];
            else res[idx++] = nums[j--];
            flag = 1 - flag;
        }
        for (i = 0; i < n; i++) nums[i] = res[i];
    }

    // S2: quick select
    // time = O(n), space = O(logn)
    int n;
    public void wiggleSort2(int[] nums) {
        n = nums.length;
        int mid = quick_select(nums, 0, n - 1, n / 2);

        int i = 0, j = n - 1, t = i;
        while (t <= j) {
            if (nums[A(t)] > mid) swap(nums, A(t++), A(i++)); // 注意这里比pivot大的值2要从左到右来放！
            else if (nums[A(t)] < mid) swap(nums, A(t), A(j--)); // 小的值则是从右往左放！
            else t++;
        }
    }

    private int A(int i) {
        return (i * 2 + 1) % (n | 1);
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    private int quick_select(int[] q, int l, int r, int k) {
        if (l >= r) return q[r];

        int x = q[l + r >> 1], i = l - 1, j = r + 1;
        while (i < j) {
            while (q[++i] < x);
            while (q[--j] > x);
            if (i < j) {
                int t = q[i];
                q[i] = q[j];
                q[j] = t;
            }
        }
        if (k <= j) return quick_select(q, l, j, k);
        return quick_select(q, j + 1, r, k);
    }
}
/**
 * 我们会先排序，利用已知的大小关系来排列数组的元素。
 * 一个简单的方法就是用双指针，一个指向末尾（最大的数），另一个指向中间（中位数）。
 * 每次取数的时候大小交错，取完一个分别将各自的指针往前移动一次。这样一定能保证是大小交错的。
 *
 * 1. 找中位数 -> 快速选择算法  time = O(n)  space = O(logn)   nth element  AC786
 * (1) 第一类：大于mid 2
 * (2) 第二类：等于mid 1
 * (3) 第三类：小于mid 0
 * 三色排序
 * 把所有的2放入偶数位置
 * 剩余的数放入偶数位置
 * 先放2，再放1和0
 * A(i) - (2i + 1) % (n | 1)
 */
