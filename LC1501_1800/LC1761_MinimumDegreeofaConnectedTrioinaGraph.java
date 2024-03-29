package LC1501_1800;
import java.util.*;
public class LC1761_MinimumDegreeofaConnectedTrioinaGraph {
    /**
     * You are given an undirected graph. You are given an integer n which is the number of nodes in the graph and an
     * array edges, where each edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.
     *
     * A connected trio is a set of three nodes where there is an edge between every pair of them.
     *
     * The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.
     *
     * Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.
     *
     * Input: n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= n <= 400
     * edges[i].length == 2
     * 1 <= edges.length <= n * (n-1) / 2
     * 1 <= ui, vi <= n
     * ui != vi
     * There are no repeated edges.
     * @param n
     * @param edges
     * @return
     */
    // S1: adjacent matrix + adjacent list
    // time = O(n^3), space = O(n^2)
    public int minTrioDegree(int n, int[][] edges) {
        int[][] connect = new int[n + 1][n + 1];
        int[] degree = new int[n + 1];
        List<Integer>[] next = new List[n + 1];
        for (int i = 0; i <= n; i++) next[i] = new ArrayList<>();
        for (int[] e : edges) {
            connect[e[0]][e[1]] = 1;
            connect[e[1]][e[0]] = 1;
            degree[e[0]]++;
            degree[e[1]]++;

            int x = Math.min(e[0], e[1]);
            int y = Math.max(e[0], e[1]);
            next[x].add(y);
        }

        int res = Integer.MAX_VALUE;
        for (int a = 1; a <= n; a++) {
            for (int i = 0; i < next[a].size(); i++) {
                for (int j = i + 1; j < next[a].size(); j++) {
                    int b = next[a].get(i);
                    int c = next[a].get(j);
                    if (connect[b][c] == 1) {
                        res = Math.min(res, degree[a] + degree[b] + degree[c] - 6);
                    }
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // S2: HashSet Array
    // time = O(n^3), space = O(n^2)
    public int minTrioDegree2(int n, int[][] edges) {
        HashSet<Integer>[] graph = new HashSet[n];
        for (int i = 0; i < n; i++) graph[i] = new HashSet<>();
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int a = edge[0] - 1, b = edge[1] - 1;
            int x = Math.min(a, b);
            int y = Math.max(a, b);
            graph[x].add(y);
            degree[x]++;
            degree[y]++;
        }

        int res = Integer.MAX_VALUE;
        for (int a = 0; a < n; a++) {
            for (int b : graph[a]) {
                for (int c : graph[a]) {
                    if (b == c) continue;
                    if (!graph[b].contains(c) && !graph[c].contains(b)) continue;
                    res = Math.min(res, degree[a] + degree[b] + degree[c] - 6);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // S3: Ennumeration (最优解!)
    // time = O(n^3), space = O(n)
    final int N = 1000010;
    public int minTrioDegree3(int n, int[][] edges) {
        boolean[] st = new boolean[N];
        int[] d = new int[n + 1];
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }
            d[a]++;
            d[b]++;
            st[a * 1000 + b] = true;
        }

        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (st[i * 1000 + j]) {
                    for (int k = j + 1; k <= n; k++) {
                        if (st[i * 1000 + k] && st[j * 1000 + k]) {
                            res = Math.min(res, d[i] + d[j] + d[k] - 6);
                        }
                    }
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
/**
 * 先枚举一个东西，然后再往上面靠拢
 * 先确定一个A,构建adjacent matrix / adjacent table
 * c[i][j]: if node i and node j are connected (邻接矩阵)
 * O(E) / O(n^2) 事先预处理
 * next[a] = {{},{}}  -> 邻接表
 * for (int a = 1; a <= n; a++) {
 *      // find{b,c} in next[a]
 *      for (int b : next[a]) {
 *          for (int c : next[a]) {
 *              // check b and c connected?  -> O(1)
 *              if c[b][c] == 1:
 *                  ret = min(degree[a] + degree[b] + degree[c] - 6);
 *          }
 *      }
 * }
 * 400^3 = 64e6
 * 完全图会TLE，重复计算同一个trio => 不希望把一个trio重复三遍
 * 从边上下手，无向边 -> 有向边，只允许小节点走向大节点
 */