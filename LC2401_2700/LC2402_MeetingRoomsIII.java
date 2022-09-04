package LC2401_2700;
import java.util.*;
public class LC2402_MeetingRoomsIII {
    /**
     * You are given an integer n. There are n rooms numbered from 0 to n - 1.
     *
     * You are given a 2D integer array meetings where meetings[i] = [starti, endi] means that a meeting will be held
     * during the half-closed time interval [starti, endi). All the values of starti are unique.
     *
     * Meetings are allocated to rooms in the following manner:
     *
     * Each meeting will take place in the unused room with the lowest number.
     * If there are no available rooms, the meeting will be delayed until a room becomes free. The delayed meeting
     * should have the same duration as the original meeting.
     * When a room becomes unused, meetings that have an earlier original start time should be given the room.
     * Return the number of the room that held the most meetings. If there are multiple rooms, return the room with the
     * lowest number.
     *
     * A half-closed interval [a, b) is the interval between a and b including a and not including b.
     *
     * Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
     * Output: 0
     *
     * Input: n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * 1 <= meetings.length <= 10^5
     * meetings[i].length == 2
     * 0 <= starti < endi <= 5 * 10^5
     * All the values of starti are unique.
     * @param n
     * @param meetings
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings,((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]));
        int[] cnt = new int[n];
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) set.add(i);
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1])); // {end, id}

        for (int[] x : meetings) {
            int start = x[0], end = x[1];
            while (!pq.isEmpty() && pq.peek()[0] <= start) {
                int[] t = pq.poll();
                set.add(t[1]);
            }
            if (set.size() == 0) {
                int[] t = pq.poll();
                pq.offer(new int[]{t[0] + x[1] - x[0], t[1]});
                cnt[t[1]]++;
            } else {
                int id = set.first();
                set.remove(id);
                pq.offer(new int[]{x[1], id});
                cnt[id]++;
            }
        }

        int max = 0, res = 0;
        for (int i = 0; i < n; i++) {
            if (cnt[i] > max) {
                max = cnt[i];
                res = i;
            }
        }
        return res;
    }
}
