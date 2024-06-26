package LC2700_3000;
import java.util.*;
public class LC2982_FindLongestSpecialSubstringThatOccursThriceII {
    /**
     * You are given a string s that consists of lowercase English letters.
     *
     * A string is called special if it is made up of only a single character. For example, the string "abc" is not
     * special, whereas the strings "ddd", "zz", and "f" are special.
     *
     * Return the length of the longest special substring of s which occurs at least thrice, or -1 if no special
     * substring occurs at least thrice.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "aaaa"
     * Output: 2
     *
     * Input: s = "abcdef"
     * Output: -1
     *
     * Input: s = "abcaba"
     * Output: 1
     *
     * Constraints:
     *
     * 3 <= s.length <= 5 * 10^5
     * s consists of only lowercase English letters.
     */
    // time = O(nlogn), space = O(n)
    int[] pos;
    public int maximumLength(String s) {
        int n = s.length();
        pos = new int[n];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;
            map.put(i, j - 1);
            i = j - 1;
        }

        for (int i = 0; i < n; i++) {
            Integer fk = map.floorKey(i);
            int v = map.get(fk);
            pos[i] = v;
        }

        int l = 1, r = n - 2;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(s, mid)) l = mid;
            else r = mid - 1;
        }
        return check(s, r) ? r : -1;
    }

    private boolean check(String s, int mid) {
        int n = s.length();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i + mid - 1 < n; i++) {
            int j = i + mid - 1, u = s.charAt(i) - 'a';
            if (pos[i] >= j) map.put(u, map.getOrDefault(u, 0) + 1);
        }
        for (int v : map.values()) {
            if (v >= 3) return true;
        }
        return false;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int maximumLength2(String s) {
        HashMap<Character, List<Integer>> map = new HashMap<>();
        int cnt = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            cnt++;
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                map.putIfAbsent(s.charAt(i), new ArrayList<>());
                map.get(s.charAt(i)).add(cnt);
                cnt = 0;
            }
        }
        int res = 0;
        for (List<Integer> q : map.values()) {
            if (q.size() == 0) continue;
            Collections.sort(q, (o1, o2) -> o2 - o1);
            q.add(0);
            q.add(0);
            int L1 = q.get(0), L2 = q.get(1), L3 = q.get(2);
            res = Math.max(res, Math.max(L1 - 2, Math.max(Math.min(L1 - 1, L2), L3)));
        }
        return res > 0 ? res : -1;
    }

    // S3
    // time = O(nlogn), space = O(n)
    public int maximumLength3(String s) {
        int n = s.length();
        List<Integer>[] cnt = new List[26];
        for (int i = 0; i < 26; i++) cnt[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;
            cnt[u].add(j - i);
            i = j - 1;
        }

        int res = -1;
        for (int i = 0; i < 26; i++) {
            if (cnt[i].size() == 0) continue;
            Collections.sort(cnt[i]);
            int l = 0, r = cnt[i].get(cnt[i].size() - 1);
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (check(cnt[i], mid)) l = mid;
                else r = mid - 1;
            }
            if (r > 0) res = Math.max(res, r);
        }
        return res;
    }

    private boolean check(List<Integer> q, int mid) {
        int t = 0;
        for (int x : q) {
            if (x < mid) continue;
            t += x - mid + 1;
        }
        return t >= 3;
    }
}
/**
 * 1.设字母 a 的最长特殊子串的长度是L1,那么可以选3个长为L1-2的相同特殊子串
 * 2.设字母 a 的次长特殊子串的长度是L2,如果L1=L2,选3个长为L1-1的
 * 如果L1>L2,选3个长为L2的
 * min(L1-1,L2)
 * 3.设字母 a 的第三长特殊子串的长度是L3，那么可以选3个长为L3的相同特殊子串
 * max(L1-2,min(L1-1,L2), L3)
 * 用堆来维护前三大，可以做到O(n)
 */