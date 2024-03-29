package LC301_600;
import java.util.*;
public class LC337_HouseRobberIII {
    /**
     * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called
     * root.
     *
     * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all
     * houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses
     * were broken into on the same night.
     *
     * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the
     * police.
     *
     * Input: root = [3,2,3,null,3,null,1]
     * Output: 7
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^4].
     * 0 <= Node.val <= 10^4
     * @param root
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    private HashMap<TreeNode, Integer> flag1;
    private HashMap<TreeNode, Integer> flag0;
    public int rob(TreeNode root) {
        // corner case
        if (root == null) return 0;

        flag1 = new HashMap<>();
        flag0 = new HashMap<>();

        return dfs(root, 1);
    }

    private int dfs(TreeNode node, int flag) { // flag = 1 : can choose ; flag = 0 : cannot choose
        // base case
        if (node == null) return 0;

        if (flag == 1 & flag1.containsKey(node)) return flag1.get(node);
        if (flag == 0 & flag0.containsKey(node)) return flag0.get(node);

        int res = 0;
        if (flag == 0) {
            res = dfs(node.left, 1) + dfs(node.right, 1);
        } else {
            int op1 = node.val + dfs(node.left, 0) + dfs(node.right, 0);
            int op2 = dfs(node.left, 1) + dfs(node.right, 1);
            res = Math.max(op1, op2);
        }

        if (flag == 1) flag1.put(node, res);
        if (flag == 0) flag0.put(node, res);
        return res;
    }

    // S2: 树形dp
    // time = O(n), space = O(n)
    public int rob2(TreeNode root) {
        if (root == null) return 0;
        int[] f = dfs(root);
        return Math.max(f[0], f[1]);
    }

    private int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0};

        int[] l = dfs(node.left);
        int[] r = dfs(node.right);
        return new int[]{Math.max(l[0], l[1]) + Math.max(r[0], r[1]), l[0] + r[0] + node.val};
    }
}
/**
 * 二叉树 99% 都可以用dfs递归的方法来解
 * 千方百计弄个dfs，越通用越好
 * root可选可不选
 * (root, 1/0)
 *     x
 * (node, 1/0)
 * 最怕重复 -> 记忆化
 * 遍历过程中会有大量重复的状态
 *
 * 对于每个点来说，有2种选择方式：
 * 选 / 不选
 * f(u,0) = max{f(x, 0), f(x, 1)} + max{f(y, 0), f(y, 1)}
 * f(u,1) = f(x, 0) + f(y, 0) + v[u]
 * return max{f(root, 0), f(root, 1)}
 */