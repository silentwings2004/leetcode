package LC2401_2700;
import java.util.*;
public class LC2458_HeightofBinaryTreeAfterSubtreeRemovalQueries {
    /**
     * You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are
     * also given an array queries of size m.
     *
     * You have to perform m independent queries on the tree where in the ith query you do the following:
     *
     * Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i]
     * will not be equal to the value of the root.
     * Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.
     *
     * Note:
     *
     * The queries are independent, so the tree returns to its initial state after each query.
     * The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
     *
     * Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
     * Output: [2]
     *
     * Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
     * Output: [3,2,3,2]
     *
     * Constraints:
     *
     * The number of nodes in the tree is n.
     * 2 <= n <= 10^5
     * 1 <= Node.val <= n
     * All the values in the tree are unique.
     * m == queries.length
     * 1 <= m <= min(n, 10^4)
     * 1 <= queries[i] <= n
     * queries[i] != root.val
     * @param root
     * @param queries
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 100010;
    int[][] tr;
    int[] son, d, ans;
    public int[] treeQueries(TreeNode root, int[] queries) {
        tr = new int[N][2];
        son = new int[N];
        d = new int[N];
        ans = new int[N];
        Arrays.fill(son, -1);

        dfs(root);

        Arrays.fill(ans, d[root.val]);
        int cur = root.val, path = 0, maxd = 0;
        while (cur != 0 && son[cur] != -1) {
            path++;
            int k = son[cur];
            maxd = Math.max(maxd, d[tr[cur][k ^ 1]] + path);
            ans[tr[cur][k]] = maxd;
            cur = tr[cur][k];
        }

        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) res[i] = ans[queries[i]] - 1;
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int l = dfs(node.left);
        int r = dfs(node.right);
        int v = node.val;
        if (l > 0) tr[v][0] = node.left.val;
        if (r > 0) tr[v][1] = node.right.val;
        d[v] = Math.max(l, r) + 1;
        if (l > r) son[v] = 0;
        else if (r > l) son[v] = 1;
        return d[v];
    }
}
