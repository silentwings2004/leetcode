package LC301_600;
import java.util.*;
public class LC315_CountofSmallerNumbersAfterSelf {
    /**
     * You are given an integer array nums and you have to return a new counts array. The counts array has the property
     * where counts[i] is the number of smaller elements to the right of nums[i].
     *
     * Input: nums = [5,2,6,1]
     * Output: [2,1,1,0]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     * @param nums
     * @return
     */
    // S1: merge sort + bs
    // time = O(nlogn * logn), space = O(n)
    private int[] sorted;
    private int[] ans;
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (nums == null || nums.length == 0) return res;

        int n = nums.length;
        sorted = nums.clone();
        ans = new int[n];

        helper(nums, 0, n - 1);

        for (int num : ans) res.add(num);
        return res;
    }

    private void helper(int[] nums, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        helper(nums, left, mid);
        helper(nums, mid + 1, right);

        for (int i = left; i <= mid; i++) {
            int idx = lowerBound(sorted, mid + 1, right, nums[i]);
            ans[i] += idx - (mid + 1) + 1;
        }

        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, p = 0;
        while (i <= mid && j <= right) {
            if (sorted[i] <= sorted[j]) temp[p++] = sorted[i++];
            else temp[p++] = sorted[j++];
        }

        while (i <= mid) temp[p++] = sorted[i++];
        while (j <= right) temp[p++] = sorted[j++];

        for (i = 0; i < right - left + 1; i++) {
            sorted[i + left] = temp[i];
        }
    }

    private int lowerBound(int[] sorted, int left, int right, int target) {
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (sorted[mid] < target) left = mid;
            else right = mid - 1;
        }
        return sorted[left] < target ? left : left - 1;
    }

    // S2: divide & conquer + merge sort
    // time = O(nlogn), space = O(n)
    private int[] index;
    private int[] answer;
    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (nums == null || nums.length == 0) return res;

        int n = nums.length;
        answer = new int[n];
        index = new int[n];
        for (int i = 0; i < n; i++) index[i] = i;

        partition(nums, 0, n - 1);

        for (int num : answer) res.add(num);
        return res;
    }

    private void partition(int[] nums, int a, int b) {
        if (a >= b) return;

        int m = a + (b - a) / 2;
        partition(nums, a, m);
        partition(nums, m + 1, b);

        int i = a, j = m + 1;
        while (i <= m && j <= b) {
            if (nums[i] <= nums[j]) answer[index[i++]] += j - (m + 1);
            else j++;
        }
        while (i <= m) answer[index[i++]] += j - (m + 1);

        i = a;
        j = m + 1;
        int p = 0;
        int[] temp = new int[b - a + 1];
        int[] tempIdx = new int[b - a + 1];

        while (i <= m && j <= b) {
            if (nums[i] <= nums[j]) {
                temp[p] = nums[i];
                tempIdx[p++] = index[i++];
            } else {
                temp[p] = nums[j];
                tempIdx[p++] = index[j++];
            }
        }
        while (i <= m) {
            temp[p] = nums[i];
            tempIdx[p++] = index[i++];
        }
        while (j <= b) {
            temp[p] = nums[j];
            tempIdx[p++] = index[j++];
        }

        for (i = 0; i < temp.length; i++) {
            nums[i + a] = temp[i];
            index[i + a] = tempIdx[i];
        }
    }

    // S3: red-black tree (TLE!!!)
    // time = O(nlogn), space = O(n)
    public List<Integer> countSmaller3(int[] nums) {
        List<Integer> res = new LinkedList<>();
        // corner case
        if (nums == null || nums.length == 0) return res;

        int n = nums.length;
        TreeNode root = new TreeNode(nums[n - 1]);
        res.add(0);
        for (int i = n - 2; i >= 0; i--) {
            res.add(0, helper(root, nums[i]));
        }
        return res;
    }

    private int helper(TreeNode root, int t) {
        TreeNode cur = root;
        int count = 0;
        while (cur != null) {
            if (cur.val < t) {
                count += cur.leftSize + cur.selfCount;
                if (cur.right == null) {
                    cur.right = new TreeNode(t);
                    break;
                }
                cur = cur.right;
            } else if (cur.val > t) {
                cur.leftSize++;
                if (cur.left == null) {
                    cur.left = new TreeNode(t);
                    break;
                }
                cur = cur.left;
            } else {
                cur.selfCount++;
                count+= cur.leftSize;
                break;
            }
        }
        return count;
    }

    private class TreeNode {
        private int val;
        private int leftSize;
        private int selfCount;
        private TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.leftSize = 0;
            this.selfCount = 1;
        }
    }

    // S4: Segment Tree
    // time = O(nlogn), space = O(n)
    public List<Integer> countSmaller4(int[] nums) {
        List<Integer> res = new LinkedList<>();
        int n = nums.length;
        SegTreeNode root = new SegTreeNode(-10000, 10000);
        init(root, -10000, 10000);

        for (int i = n - 1; i >= 0; i--) {
            update(root, nums[i], 1);
            res.add(0, query(root, -10000, nums[i] - 1));
        }
        return res;
    }

    private class SegTreeNode {
        private int start, end;
        private int count;
        private SegTreeNode left, right;
        public SegTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private void init(SegTreeNode node, int a, int b) {
        if (a == b) return;

        int mid = a + (b - a) / 2;
        if (node.left == null) {
            node.left = new SegTreeNode(a, mid);
            node.right = new SegTreeNode(mid + 1, b);
        }

        init(node.left, a, mid);
        init(node.right, mid + 1, b);

        node.count = node.left.count + node.right.count;
    }

    private void update(SegTreeNode node, int index, int val) {
        if (node.end < index || node.start > index) return;
        if (node.start == node.end) {
            node.count += val;
            return;
        }

        update(node.left, index, val);
        update(node.right, index, val);

        node.count = node.left.count + node.right.count;
    }

    private int query(SegTreeNode node, int a, int b) {
        if (node.end < a || node.start > b) return 0;
        if (a <= node.start && node.end <= b) return node.count;

        return query(node.left, a, b) + query(node.right, a, b);
    }

    // S5: BIT
    // time = O(nlogn), space = O(n)
    final int N = 20010;
    int[] tr;
    public List<Integer> countSmaller5(int[] nums) {
        tr = new int[N];
        int n = nums.length, idx = 0;
        List<Integer> res = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i] + 10001;
            res.add(0, query(x - 1));
            add(x, 1);
        }
        return res;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, int c) {
        for (int i = x; i < N; i += lowbit(i)) tr[i] += c;
    }

    private int query(int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res += tr[i];
        return res;
    }
}
/**
 * ref: LC1649: 翻译过来就是count smaller number before self 与该题几乎一致
 * 都可以用分治来做，用处并不是特别大，思想很经典
 * 分治法一般会和归并排序放在一起用
 * A: [y y y y y z z z z z]
 * B: [y y y y y] C:[z z z z z] + ...
 *     a      mid   mid+1    b
 * 固定一个y，z扫一遍 => C是有序的话，可以用二分法提高效率
 * 分治 + 归并排序 -> 小问题 解决 大问题，返回到上一级，也把它搞成有序
 * 先拆分分解递归，返回之后要搞成有序，这样再往上传递方便解决更大的问题 -> 归并排序搞成有序的
 * 有点像递归，最大特色是返回的时候需要额外做一个排序
 *
 * 树状数组主要用来：
 * 1. 某个位置 + c
 * 2. 求前缀的和
 * 1 ~ x - 1  => query(x - 1)
 * add(x, 1)
 * 树状数组的下标就是这里的数值
 */
