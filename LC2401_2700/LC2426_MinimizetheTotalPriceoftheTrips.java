package LC2401_2700;
import java.util.*;
public class LC2426_MinimizetheTotalPriceoftheTrips {
    /**
     * There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1. You are given the integer n
     * and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between
     * nodes ai and bi in the tree.
     *
     * Each node has an associated price. You are given an integer array price, where price[i] is the price of the ith
     * node.
     *
     * The price sum of a given path is the sum of the prices of all nodes lying on that path.
     *
     * Additionally, you are given a 2D integer array trips, where trips[i] = [starti, endi] indicates that you start
     * the ith trip from the node starti and travel to the node endi by any path you like.
     *
     * Before performing your first trip, you can choose some non-adjacent nodes and halve the prices.
     *
     * Return the minimum total price sum to perform all the given trips.
     *
     * Input: n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
     * Output: 23
     *
     * Input: n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 50
     * edges.length == n - 1
     * 0 <= ai, bi <= n - 1
     * edges represents a valid tree.
     * price.length == n
     * price[i] is an even integer.
     * 1 <= price[i] <= 1000
     * 1 <= trips.length <= 100
     * 0 <= starti, endi <= n - 1
     * @param n
     * @param edges
     * @param price
     * @param trips
     * @return
     */
    // time = O(m * n), space = O(m * n)
    final int N = 55, M = N * 2;
    int[] h, e, ne, w;
    int idx;
    int[] s;
    boolean[] st;
    int[][] f;
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        w = price;
        s = new int[N];
        st = new boolean[N];
        f = new int[N][2];
        Arrays.fill(h, -1);
        idx = 0;

        for (int[] x : edges) {
            int a = x[0], b = x[1];
            add(a, b);
            add(b, a);
        }

        for (int[] t : trips) {
            int a = t[0], b = t[1];
            Arrays.fill(st, false);
            st[a] = true;
            s[a] += w[a];
            dfs(a, b);
        }

        Arrays.fill(st, false);
        dfs2(0, -1);
        return Math.min(f[0][0], f[0][1]);
    }

    private void dfs2(int u, int fa) {
        st[u] = true;
        f[u][0] = s[u];
        f[u][1] = s[u] / 2;

        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (st[j]) continue;
            dfs2(j, u);
            f[u][0] += Math.min(f[j][0], f[j][1]);
            f[u][1] += f[j][0];
        }
    }

    private boolean dfs(int start, int end) {
        if (start == end) return true;

        for (int i = h[start]; i != -1; i = ne[i]) {
            int j = e[i];
            if (st[j]) continue;
            st[j] = true;
            s[j] += w[j];
            if (dfs(j, end)) return true;
            s[j] -= w[j];
        }
        return false;
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}