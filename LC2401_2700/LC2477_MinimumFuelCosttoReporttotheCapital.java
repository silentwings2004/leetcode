package LC2401_2700;
import java.util.*;
public class LC2477_MinimumFuelCosttoReporttotheCapital {
    /**
     * There is a tree (i.e., a connected, undirected graph with no cycles) structure country network consisting of n
     * cities numbered from 0 to n - 1 and exactly n - 1 roads. The capital city is city 0. You are given a 2D integer
     * array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.
     *
     * There is a meeting for the representatives of each city. The meeting is in the capital city.
     *
     * There is a car in each city. You are given an integer seats that indicates the number of seats in each car.
     *
     * A representative can use the car in their city to travel or change the car and ride with another representative.
     * The cost of traveling between two cities is one liter of fuel.
     *
     * Return the minimum number of liters of fuel to reach the capital city.
     *
     * Input: roads = [[0,1],[0,2],[0,3]], seats = 5
     * Output: 3
     *
     * Input: roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
     * Output: 7
     *
     * Input: roads = [], seats = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * roads.length == n - 1
     * roads[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * roads represents a valid tree.
     * 1 <= seats <= 10^5
     * @param roads
     * @param seats
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer>[] graph;
    long[] st;
    long res = 0;
    int seats;
    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;
        this.seats = seats;
        st = new long[n];
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] x : roads) {
            int a = x[0], b = x[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(0, -1);
        return res;
    }

    private long dfs(int u, int fa) {
        for (int next : graph[u]) {
            if (next == fa) continue;
            st[u] += dfs(next, u);
        }

        if (u != 0) {
            st[u]++;
            long t = st[u] % seats == 0 ? st[u] / seats : st[u] / seats + 1;
            res += t;
        }
        return st[u];
    }
}