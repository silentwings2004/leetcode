package LC1501_1800;
import java.util.*;
public class LC1514_PathwithMaximumProbability {
    /**
     * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where
     * edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing
     * that edge succProb[i].
     *
     * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and
     * return its success probability.
     *
     * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct
     * answer by at most 1e-5.
     *
     * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
     * Output: 0.25000
     *
     * Constraints:
     *
     * 2 <= n <= 10^4
     * 0 <= start, end < n
     * start != end
     * 0 <= a, b < n
     * a != b
     * 0 <= succProb.length == edges.length <= 2*10^4
     * 0 <= succProb[i] <= 1
     * There is at most one edge between every two nodes.
     * @param n
     * @param edges
     * @param succProb
     * @param start
     * @param end
     * @return
     */
    // S1: BFS
    // time = O(m + n), space = O(m * n)
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Pair>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(new Pair(edges[i][1], succProb[i]));
            graph[edges[i][1]].add(new Pair(edges[i][0], succProb[i]));
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        double[] prob = new double[n];
        prob[start] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Pair next : graph[cur]) {
                if (prob[next.node] >= prob[cur] * next.val) continue; // no need to update
                prob[next.node] = prob[cur] * next.val;
                queue.offer(next.node);
            }
        }
        return prob[end];
    }

    private class Pair {
        private int node;
        private double val;
        public Pair(int node, double val) {
            this.node = node;
            this.val = val;
        }
    }

    // S2: Dijkstra
    // time = O(ElogE) = O(mlogm), space = O(n)
    public double maxProbability2(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Pair>[] graph = new List[n]; // {nextId, Prob}
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0], b = edges[i][1];
            double p = succProb[i];
            graph[a].add(new Pair(b, -Math.log(p)));
            graph[b].add(new Pair(a, -Math.log(p)));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.val, o2.val));
        pq.offer(new Pair(start, 0));
        boolean[] visited = new boolean[n];

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int node = cur.node;
            double dist = cur.val;
            if (visited[node]) continue;
            visited[node] = true;
            if (node == end) return Math.exp(-dist);

            for (Pair x : graph[node]) {
                int next = x.node;
                double weight = x.val;
                if (visited[next]) continue;
                pq.offer(new Pair(next, dist + weight));
            }
        }
        return 0;
    }

    // S3: spfa
    final int N = 10010, M = N * 4;
    final double INF = 1e-9;
    int[] h, e, ne;
    double[] w, dist;
    int idx;
    int[] q;
    boolean[] st;
    public double maxProbability3(int n, int[][] edges, double[] succProb, int start, int end) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        w = new double[M];
        dist = new double[N];
        q = new int[N];
        st = new boolean[N];
        Arrays.fill(h, -1);
        idx = 0;

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0], b = edges[i][1];
            double c = succProb[i];
            add(a, b, c);
            add(b, a, c);
        }

        double t = spfa(start, end);
        return t == INF ? 0 : t;
    }

    private double spfa(int a, int b) {
        Arrays.fill(dist, INF);
        dist[a] = 1;
        int hh = 0, tt = 1;
        q[0] = a;
        st[a] = true;

        while (hh != tt) {
            int t = q[hh++];
            if (hh == N) hh = 0;
            st[t] = false;

            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] < dist[t] * w[i]) {
                    dist[j] = dist[t] * w[i];
                    if (!st[j]) {
                        q[tt++] = j;
                        if (tt == N) tt = 0;
                        st[j] = true;
                    }
                }
            }
        }
        return dist[b];
    }

    private void add(int a, int b, double c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}
/**
 * 从起点到终点，路径乘积最大的
 * 1. bfs
 * 2. Dijkstra
 * 0 [1]
 * 1 [0.5]
 * 2 [0.2] => [0.25]
 * 0(1) -> 1(0.5)  2(0.2) -> 0(0.25) 2(0.25) 0(0.04) 1(0.1)
 * 传统bfs不走重复的点
 * 这里bfs的唯一区别是：之前bfs访问结点之后，都把它标记为visited，不会再放入queue里
 * 这里是只要更新到next路径，就会把它放入队列中，之后都要重新走一遍
 *
 * S2: Dijkstra
 * 图论里求最短路径的算法
 * 只能处理单源非负权重的最短路径问题 = single-source, non-negative weight, min weight-sum
 * Priority_queue, {node, distance_from_start_to_node}
 * the popped node, if never been popped, distance_from_start_to_node is the optimal distance for this node
 * maximize prob(E1) * prob(E2) * ... * prob(Ek)
 * minimize -log(prob(E1)) - log(prob(E2)) - ... - log(prob(Ek))
 * 取对数，取负号后，所有权重都变成正的了。
 * maximize distance product
 * Dijkstra: minimize distance sum => non-negative weight
 * maximize p1*p2*p3*...
 * minimize -log(p1*p2*p3*...) => -log(p1) + -log(p2) + -log(p3) + ...
 * 找一个最小化的边和
 * d => exp(-d)
 */
