package LC3001_3300;
import java.util.*;
public class LC3011_FindifArrayCanBeSorted {
    /**
     * You are given a 0-indexed array of positive integers nums.
     *
     * In one operation, you can swap any two adjacent elements if they have the same number of
     * set bits
     * . You are allowed to do this operation any number of times (including zero).
     *
     * Return true if you can sort the array, else return false.
     *
     * Input: nums = [8,4,2,30,15]
     * Output: true
     *
     * Input: nums = [1,2,3,4,5]
     * Output: true
     *
     * Input: nums = [3,16,8,4,2]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 2^8
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(1)
    int[] p;
    public boolean canSortArray(int[] nums) {
        int n = nums.length;
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        for (int i = 1; i < n; i++) {
            if (Integer.bitCount(nums[i]) == Integer.bitCount(nums[i - 1])) {
                p[find(i)] = find(i - 1);
            }
        }
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n; i++) {
            int fa = find(i);
            map.putIfAbsent(fa, new ArrayList<>());
            map.get(fa).add(i);
        }
        for (List<Integer> q : map.values()) {
            List<Integer> p = new ArrayList<>();
            for (int x : q) p.add(nums[x]);
            Collections.sort(p);
            for (int i = 0; i < q.size(); i++) nums[q.get(i)] = p.get(i);
        }
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) return false;
        }
        return true;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}