package LC2401_2700;
import java.util.*;
public class LC2662_MinimumCostofaPathWithSpecialRoads {
    /**
     * You are given an array start where start = [startX, startY] represents your initial position (startX, startY) in
     * a 2D space. You are also given the array target where target = [targetX, targetY] represents your target position
     * (targetX, targetY).
     *
     * The cost of going from a position (x1, y1) to any other position in the space (x2, y2) is |x2 - x1| + |y2 - y1|.
     *
     * There are also some special roads. You are given a 2D array specialRoads where specialRoads[i] = [x1i, y1i, x2i,
     * y2i, costi] indicates that the ith special road can take you from (x1i, y1i) to (x2i, y2i) with a cost equal to
     * costi. You can use each special road any number of times.
     *
     * Return the minimum cost required to go from (startX, startY) to (targetX, targetY).
     *
     * Input: start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
     * Output: 5
     *
     * Input: start = [3,2], target = [5,7], specialRoads = [[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]
     * Output: 7
     *
     * Constraints:
     *
     * start.length == target.length == 2
     * 1 <= startX <= targetX <= 10^5
     * 1 <= startY <= targetY <= 10^5
     * 1 <= specialRoads.length <= 200
     * specialRoads[i].length == 5
     * startX <= x1i, x2i <= targetX
     * startY <= y1i, y2i <= targetY
     * 1 <= costi <= 10^5
     * @param start
     * @param target
     * @param specialRoads
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int N = 410, M = N * N, INF = 0x3f3f3f3f;
    int[] h, e, w, ne;
    int[] dist;
    boolean[] st;
    int idx, id;
    HashMap<String, Integer> map;
    HashSet<String> set;
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        map = new HashMap<>();
        set = new HashSet<>();
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        w = new int[M];
        dist = new int[N];
        st = new boolean[N];
        id = 0;
        Arrays.fill(h, -1);
        idx = 0;

        String s = convert(start[0], start[1]), t = convert(target[0], target[1]);
        if (s.equals(t)) return 0;
        map.put(s, id++);
        map.put(t, id++);

        for (int[] w : specialRoads) {
            int x1 = w[0], y1 = w[1], x2 = w[2], y2 = w[3], c = w[4];
            String str1 = convert(x1, y1), str2 = convert(x2, y2);
            if (!map.containsKey(str1)) map.put(str1, id++);
            if (!map.containsKey(str2)) map.put(str2, id++);
            int a = map.get(str1), b = map.get(str2);
            int d = get(x1, y1, x2, y2);
            c = Math.min(d, c);
            if (set.add(convert(a, b))) add(a, b, c);
        }

        for (String x : map.keySet()) {
            for (String y : map.keySet()) {
                if (x.equals(y)) continue;
                int a = map.get(x), b = map.get(y);
                String[] str1 = x.split("#"), str2 = y.split("#");
                int x1 = Integer.parseInt(str1[0]), y1 = Integer.parseInt(str1[1]);
                int x2 = Integer.parseInt(str2[0]), y2 = Integer.parseInt(str2[1]);
                int c = get(x1, y1, x2, y2);
                if (set.add(a + "#" + b)) add(a, b, c);
                if (set.add(b + "#" + a)) add(b, a, c);
            }
        }
        return dijkstra(0);
    }

    private int dijkstra(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{0, start});

        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int ver = t[1], distance = t[1];
            if (ver == 1) break;
            if (st[ver]) continue;
            st[ver] = true;

            for (int i = h[ver]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[ver] + w[i]) {
                    dist[j] = dist[ver] + w[i];
                    pq.offer(new int[]{dist[j], j});
                }
            }
        }
        return dist[1];
    }

    private void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    private String convert(int x, int y) {
        return x + "#" + y;
    }

    private int get(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }
}