package LC1501_1800;
import java.util.*;
public class LC1624_LargestSubstringBetweenTwoEqualCharacters {
    /**
     * Given a string s, return the length of the longest substring between two equal characters, excluding the two
     * characters. If there is no such substring return -1.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     * Input: s = "aa"
     * Output: 0
     *
     * Input: s = "abca"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 300
     * s contains only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int maxLengthBetweenEqualCharacters(String s) {
        int n = s.length();
        int[][] cnt = new int[26][2];
        for (int i = 0; i < 26; i++) Arrays.fill(cnt[i], -1);

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (cnt[c - 'a'][0] == -1) cnt[c- 'a'][0] = i;
            else cnt[c - 'a'][1] = i;
        }

        int res = -1;
        for (int i = 0; i < 26; i++) {
            if (cnt[i][0] == -1 || cnt[i][1] == -1) continue;
            res = Math.max(res, cnt[i][1] - cnt[i][0] - 1);
        }
        return res;
    }
}
