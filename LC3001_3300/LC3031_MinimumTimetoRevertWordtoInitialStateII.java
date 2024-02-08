package LC3001_3300;

public class LC3031_MinimumTimetoRevertWordtoInitialStateII {
    /**
     * You are given a 0-indexed string word and an integer k.
     *
     * At every second, you must perform the following operations:
     *
     * Remove the first k characters of word.
     * Add any k characters to the end of word.
     * Note that you do not necessarily need to add the same characters that you removed. However, you must perform both
     * operations at every second.
     *
     * Return the minimum time greater than zero required for word to revert to its initial state.
     *
     * Input: word = "abacaba", k = 3
     * Output: 2
     *
     * Input: word = "abacaba", k = 4
     * Output: 1
     *
     * Input: word = "abcbabcd", k = 2
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= word.length <= 10^5
     * 1 <= k <= word.length
     * word consists only of lowercase English letters.
     * @param word
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    final int P = 131;
    long[] h, p;
    public int minimumTimeToInitialState(String word, int k) {
        int n = word.length(), t = (n + k - 1) / k, res = t;
        p = new long[n + 1];
        h = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + word.charAt(i - 1);
        }

        for (int i = 1; i < t; i++) {
            long ht = get(i * k, n - 1), hs = get(0, n - i * k - 1);
            if (ht == hs) {
                res = i;
                break;
            }
        }
        return res;
    }

    private long get(int l, int r) {
        l++;
        r++;
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}