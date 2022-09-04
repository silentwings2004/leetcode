package LC2101_2400;

public class LC2380_TimeNeededtoRearrangeaBinaryString {
    /**
     * You are given a binary string s. In one second, all occurrences of "01" are simultaneously replaced with "10".
     * This process repeats until no occurrences of "01" exist.
     *
     * Return the number of seconds needed to complete this process.
     *
     * Input: s = "0110101"
     * Output: 4
     *
     * Input: s = "11100"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s[i] is either '0' or '1'.
     * @param s
     * @return
     */
    // S1: brute-force
    // time = O(n^2), space = O(n)
    public int secondsToRemoveOccurrences(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length, res = 0;
        while (true) {
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                if (i + 1 < n && chars[i] == '0' && chars[i + 1] == '1') {
                    chars[i] = '1';
                    chars[i + 1] = '0';
                    i++;
                    flag = true;
                }
            }
            if (!flag) break;
            res++;
        }
        return res;
    }
}
/**
 * 每一步都是0往右跳
 * 为什么不会超时呢？
 * 0000001111
 *   a    b
 * 第一个0要等待a - 1秒，再跳过b个1 => a + b - 1 => O(n^2)
 */
