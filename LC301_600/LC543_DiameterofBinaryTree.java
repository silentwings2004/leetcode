package LC301_600;

public class LC543_DiameterofBinaryTree {
    /**
     * Given the root of a binary tree, return the length of the diameter of the tree.
     *
     * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may
     * or may not pass through the root.
     *
     * The length of a path between two nodes is represented by the number of edges between them.
     *
     * Input: root = [1,2,3,4,5]
     * Output: 3
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^4].
     * -100 <= Node.val <= 100
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res - 1;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int l = dfs(node.left);
        int r = dfs(node.right);
        res = Math.max(res, l + r + 1);
        return Math.max(l, r) + 1;
    }
}
/**
 * 各点连通，且没有环，无向无环连通图
 * 最关键的一点：对diameter有个奇特的审查角度
 * 不管路径如何分布，肯定会有一个拐点
 * 递归 -> 把每个结点到达最远的距离计算出来，均摊为1
 * 问的虽然是最长路径，但是我递归算的是每个结点到叶子节点的距离，间接来求
 * 以node为拐点的最长路径我们也知道，就是leftPath + node + rightPath
 * 所以所有的path都不重复不遗漏的遍历了一遍
 * 遇到树，递归函数 -> 求的是很特别的，结点和自身的最远距离，间接去求
 *
 * 一般树的直径怎么求？
 * 用无向图去存，随便从一个点出发作为根来搜，找到一个到根节点最远的点，再以这个点为根，再找到距离这个点最远的点
 * 两遍dfs
 * 任何一个路径都存在一个最高点
 * 只要枚举树里的每个点作为最高点，就一定能找到答案
 * 求下穿过这点路径的最大值，可以分成两段
 */
