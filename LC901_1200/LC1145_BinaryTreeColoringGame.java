package LC901_1200;

public class LC1145_BinaryTreeColoringGame {
    /**
     * Two players play a turn based game on a binary tree. We are given the root of this binary tree, and the number
     * of nodes n in the tree. n is odd, and each node has a distinct value from 1 to n.
     *
     * Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y with
     * 1 <= y <= n and y != x. The first player colors the node with value x red, and the second player colors the node
     * with value y blue.
     *
     * Then, the players take turns starting with the first player. In each turn, that player chooses a node of their
     * color (red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen node (either the left
     * child, right child, or parent of the chosen node.)
     *
     * If (and only if) a player cannot choose such a node in this way, they must pass their turn. If both players pass
     * their turn, the game ends, and the winner is the player that colored more nodes.
     *
     * You are the second player. If it is possible to choose such a y to ensure you win the game, return true. If it is
     * not possible, return false.
     *
     * Input: root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
     * Output: true
     *
     * Input: root = [1,2,3], n = 3, x = 1
     * Output: false
     *
     * Constraints:
     *
     * The number of nodes in the tree is n.
     * 1 <= x <= n <= 100
     * n is odd.
     * 1 <= Node.val <= n
     * All the values of the tree are unique.
     * @param root
     * @param n
     * @param x
     * @return
     */
    // time = O(n), space = O(n)
    int ans = 0;
    int n;
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        this.n = n;
        dfs(root, x);
        return ans > n - ans;
    }


    private int dfs(TreeNode node, int x) {
        if (node == null) return 0;
        int l = dfs(node.left, x);
        int r = dfs(node.right, x);
        if (node.val == x) ans = Math.max(Math.max(l, r), n - l - r - 1);
        return l + r + 1;
    }
}
/**
 * 哪一方形成的连通块最大
 * 蓝色块只能选红色点的父节点和2个儿子，只有这3种选法
 * n是奇数 => 不可能平局
 * s1, s2, n - s1 - s2 - 1
 */