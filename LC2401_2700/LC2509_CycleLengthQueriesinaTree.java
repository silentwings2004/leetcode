package LC2401_2700;

public class LC2509_CycleLengthQueriesinaTree {
    /**
     * You are given an integer n. There is a complete binary tree with 2n - 1 nodes. The root of that tree is the node
     * with the value 1, and every node with a value val in the range [1, 2n - 1 - 1] has two children where:
     *
     * The left node has the value 2 * val, and
     * The right node has the value 2 * val + 1.
     * You are also given a 2D integer array queries of length m, where queries[i] = [ai, bi]. For each query, solve the
     * following problem:
     *
     * Add an edge between the nodes with values ai and bi.
     * Find the length of the cycle in the graph.
     * Remove the added edge between nodes with values ai and bi.
     * Note that:
     *
     * A cycle is a path that starts and ends at the same node, and each edge in the path is visited only once.
     * The length of a cycle is the number of edges visited in the cycle.
     * There could be multiple edges between two nodes in the tree after adding the edge of the query.
     * Return an array answer of length m where answer[i] is the answer to the ith query.
     *
     * Input: n = 3, queries = [[5,3],[4,7],[2,3]]
     * Output: [4,5,3]
     *
     * Input: n = 2, queries = [[1,2]]
     * Output: [2]
     *
     * Constraints:
     *
     * 2 <= n <= 30
     * m == queries.length
     * 1 <= m <= 10^5
     * queries[i].length == 2
     * 1 <= ai, bi <= 2n - 1
     * ai != bi
     * @param n
     * @param queries
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] cycleLengthQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            int fa = lca(a, b);
            long x = findDepth(a), y = findDepth(b), z = findDepth(fa);
            res[i] = (int)(1 + x - z + y - z);
        }
        return res;
    }

    private int lca(int x, int y) {
        long dx = findDepth(x), dy = findDepth(y);
        if (dx < dy) {
            int t = x;
            x = y;
            y = t;

            long td = dx;
            dx = dy;
            dy = td;
        }

        while (dx > dy) {
            x /= 2;
            dx = findDepth(x);
        }

        while (x != y) {
            x /= 2;
            y /= 2;
        }
        return x;
    }

    private long findDepth(int x) {
        long y = 1, depth = 1, sum = 1;
        while (y < x) {
            sum *= 2;
            y += sum;
            depth++;
        }
        return depth;
    }
}