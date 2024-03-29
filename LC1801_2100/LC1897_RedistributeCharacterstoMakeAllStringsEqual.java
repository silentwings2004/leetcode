package LC1801_2100;
import java.util.*;
public class LC1897_RedistributeCharacterstoMakeAllStringsEqual {
    /**
     * You are given an array of strings words (0-indexed).
     *
     * In one operation, pick two distinct indices i and j, where words[i] is a non-empty string, and move any
     * character from words[i] to any position in words[j].
     *
     * Return true if you can make every string in words equal using any number of operations, and false otherwise.
     *
     * Input: words = ["abc","aabc","bc"]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 100
     * words[i] consists of lowercase English letters.
     * @param words
     * @return
     */
    // time = O(n * k), space = O(1)
    public boolean makeEqual(String[] words) {
        int[] cnt = new int[26];
        int n = words.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                cnt[words[i].charAt(j) - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % n != 0) return false;
        }
        return true;
    }
}