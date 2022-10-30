package LC2401_2700;

public class LC2423_RemoveLetterToEqualizeFrequency {
    /**
     * ou are given a 0-indexed string word, consisting of lowercase English letters. You need to select one index and
     * remove the letter at that index from word so that the frequency of every letter present in word is equal.
     *
     * Return true if it is possible to remove one letter so that the frequency of all letters in word are equal, and
     * false otherwise.
     *
     * Note:
     *
     * The frequency of a letter x is the number of times it occurs in the string.
     * You must remove exactly one letter and cannot chose to do nothing.
     *
     * Input: word = "abcc"
     * Output: true
     *
     * Input: word = "aazz"
     * Output: false
     *
     * Constraints:
     *
     * 2 <= word.length <= 100
     * word consists of lowercase English letters only.
     * @param word
     * @return
     */
    // time = O(n^2), space = O(1)
    public boolean equalFrequency(String word) {
        int n = word.length();
        for (int i = 0; i < n; i++) {
            if (check(word, i)) return true;
        }
        return false;
    }

    private boolean check(String s, int idx) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            if (i == idx) continue;
            char c = s.charAt(i);
            cnt[c - 'a']++;
        }

        int freq = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                if (freq == 0) freq = cnt[i];
                else if (freq != cnt[i]) return false;
            }
        }
        return true;
    }

    // S2
    // time = O(n), space = O(1)
    public boolean equalFrequency2(String word) {
        int[] cnt = new int[26];
        int maxf = 0, n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            cnt[c - 'a']++;
            maxf = Math.max(maxf, cnt[c - 'a']);
        }

        int m1 = 0, m2 = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0) continue;
            if (cnt[i] == maxf) m1++;
            if (cnt[i] == maxf - 1) m2++;
            if (cnt[i] < maxf - 1) return false;
        }
        return m2 == 1 && maxf == 2 || m1 == 1 || m2 == 0 && maxf == 1;
    }
}