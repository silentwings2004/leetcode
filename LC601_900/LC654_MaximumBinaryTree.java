package LC601_900;
import java.util.*;
public class LC654_MaximumBinaryTree {
    /**
     * You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums
     * using the following algorithm:
     *
     * Create a root node whose value is the maximum value in nums.
     * Recursively build the left subtree on the subarray prefix to the left of the maximum value.
     * Recursively build the right subtree on the subarray suffix to the right of the maximum value.
     * Return the maximum binary tree built from nums.
     *
     * Input: nums = [3,2,1,6,0,5]
     * Output: [6,3,5,null,2,0,null,null,1]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 1000
     * All integers in nums are unique.
     * @param nums
     * @return
     */
    // S1: dfs
    // time = O(n^2), space = O(n)
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int start, int end) {
        // base case
        if (start > end) return null;

        int max = nums[start], idx = start;
        for (int i = start; i <= end; i++) {
            if (nums[i] > max) {
                idx = i;
                max = nums[i];
            }
        }

        TreeNode root = new TreeNode(max);
        root.left = dfs(nums, start, idx - 1);
        root.right = dfs(nums, idx + 1, end);
        return root;
    }

    // S2: monotonic stack
    // time = O(n), space = O(n)
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        Stack<TreeNode> stack = new Stack<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            TreeNode node = new TreeNode(nums[i]);
            while (!stack.isEmpty() && stack.peek().val < nums[i]) node.left = stack.pop(); // 不停替换，只取最后的一个
            // 找到了根，只能拼到右子树里，而上面的node.left其实也在这个peek node的右子树里
            if (!stack.isEmpty()) stack.peek().right = node;
            stack.push(node);
        }
        while (stack.size() > 1) stack.pop(); // 退到后来，只有最大值不会被pop出栈，一定会留在栈底！
        return stack.peek();
    }

    // S3: RMQ
    // time = O(nlogn), space = O(n)
    int n, k;
    int[][] f;
    int[] nums;
    public TreeNode constructMaximumBinaryTree3(int[] nums) {
        this.nums = nums;
        n = nums.length;
        k = (int)(Math.log(n) / Math.log(2));
        f = new int[n][k + 1];
        for (int j = 0; j <= k; j++) {
            for (int i = 0; i + (1 << j) - 1 < n; i++) {
                if (j == 0) f[i][j] = i;
                else {
                    int l = f[i][j - 1], r = f[i + (1 << j - 1)][j - 1];
                    if (nums[l] > nums[r]) f[i][j] = l;
                    else f[i][j] = r;
                }
            }
        }
        return build(0, n - 1);
    }

    private TreeNode build(int l, int r) {
        if (l > r) return null;
        int k = query(l, r);
        TreeNode root = new TreeNode(nums[k]);
        root.left = build(l, k - 1);
        root.right = build(k + 1, r);
        return root;
    }

    private int query(int l, int r) {
        int len = r - l + 1;
        int k = (int)(Math.log(len) / Math.log(2));
        int a = f[l][k], b = f[r - (1 << k) + 1][k];
        if (nums[a] > nums[b]) return a;
        return b;
    }
}
/**
 * 我们维护一个"单调递减"的序列。
 * 如果当前数组元素里都是递减的，那么他们必然组成连续的右节点的关系。
 * 此时突然出现了一个较大的数A，那么A的左节点必然连接目前栈里面恰好比A小的那个元素。
 * 所以你需要不停地腾退栈顶元素，并且把最后一个恰好比A小的那个元素B接到A的左节点上。
 * 同时，A需要设置为当前栈顶元素C的右节点。
 * 如下图所示，相当于把A插入了C的右子树里，原先B子树都移到了A的左子树。这样就实现了题目所要求的目的。
 *  C
 *    \     A
 *      B
 *        \
 * 最终，栈底的元素（最大值）就是全局的根节点。
 *
 * 算法的瓶颈在找"最值"上。
 * 1. 线段树 => O(logn)
 * 2. St表/RMQ/倍增 => O(nlogn) 初始化一个dp数组，O(1)时间找区间最值
 * f(i,j)表示从i开始的长度为2^j的这一段的最值[i, i+(2^j)-1]的最大值
 * f(i,j) = max{f(i,j-1), f(i + 2^j-1, j - 1)}
 * k = [log2len]
 * max{f(L, k), f(R-2^k+1, k)}
 */