package LC2700_3000;
import java.util.*;
public class LC2751_RobotCollisions {
    /**
     * There are n 1-indexed robots, each having a position on a line, health, and movement direction.
     *
     * You are given 0-indexed integer arrays positions, healths, and a string directions (directions[i] is either 'L'
     * for left or 'R' for right). All integers in positions are unique.
     *
     * All robots start moving on the line simultaneously at the same speed in their given directions. If two robots
     * ever share the same position while moving, they will collide.
     *
     * If two robots collide, the robot with lower health is removed from the line, and the health of the other robot
     * decreases by one. The surviving robot continues in the same direction it was going. If both robots have the same
     * health, they are both removed from the line.
     *
     * Your task is to determine the health of the robots that survive the collisions, in the same order that the robots
     * were given, i.e. final heath of robot 1 (if survived), final health of robot 2 (if survived), and so on. If there
     * are no survivors, return an empty array.
     *
     * Return an array containing the health of the remaining robots (in the order they were given in the input), after
     * no further collisions can occur.
     *
     * Note: The positions may be unsorted.
     *
     * Input: positions = [5,4,3,2,1], healths = [2,17,9,15,10], directions = "RRRRR"
     * Output: [2,17,9,15,10]
     *
     * Input: positions = [3,5,2,6], healths = [10,10,15,12], directions = "RLRL"
     * Output: [14]
     *
     * Input: positions = [1,2,5,6], healths = [10,10,11,11], directions = "RLRL"
     * Output: []
     *
     * Constraints:
     *
     * 1 <= positions.length == healths.length == directions.length == n <= 10^5
     * 1 <= positions[i], healths[i] <= 10^9
     * directions[i] == 'L' or directions[i] == 'R'
     * All values in positions are distinct
     * @param positions
     * @param healths
     * @param directions
     * @return
     */
    // time = O(nlogn), space = O(n)
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        List<Integer> res = new ArrayList<>();
        TreeMap<Integer, int[]> ml = new TreeMap<>();
        TreeMap<Integer, int[]> mr = new TreeMap<>((o1, o2) -> o2 - o1);
        int n = positions.length;
        for (int i = 0; i < n; i++) {
            int p = positions[i], h = healths[i];
            if (directions.charAt(i) == 'L') ml.put(p, new int[]{h, i});
            else mr.put(p, new int[]{h, i});
        }

        boolean[] st = new boolean[n];
        for (int rk : mr.keySet()) {
            int ha = mr.get(rk)[0], ia = mr.get(rk)[1];
            int pos = rk;
            List<Integer> rem = new ArrayList<>();
            while (ml.higherKey(pos) != null) {
                int hk = ml.higherKey(pos);
                int hb = ml.get(hk)[0], ib = ml.get(hk)[1];
                if (ha == hb) {
                    st[ia] = true;
                    st[ib] = true;
                    rem.add(hk);
                    break;
                } else if (ha > hb) {
                    ha--;
                    st[ib] = true;
                    rem.add(hk);
                    pos = hk;
                } else {
                    hb--;
                    st[ia] = true;
                    ml.put(hk, new int[]{hb, ib});
                    break;
                }
            }
            for (int x : rem) ml.remove(x);
            if (!st[ia]) mr.put(rk, new int[]{ha, ia});
        }

        for (int i = 0; i < n; i++) {
            if (st[i]) continue;
            if (directions.charAt(i) == 'L') res.add(ml.get(positions[i])[0]);
            else res.add(mr.get(positions[i])[0]);
        }
        return res;
    }
}