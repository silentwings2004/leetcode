package LC3001_3300;

public class LC3227_VowelsGameinaString {
    /**
     * Alice and Bob are playing a game on a string.
     *
     * You are given a string s, Alice and Bob will take turns playing the following game where Alice starts first:
     *
     * On Alice's turn, she has to remove any non-empty substring from s that contains an odd number of vowels.
     * On Bob's turn, he has to remove any non-empty substring from s that contains an even number of vowels.
     * The first player who cannot make a move on their turn loses the game. We assume that both Alice and Bob play
     * optimally.
     *
     * Return true if Alice wins the game, and false otherwise.
     *
     * The English vowels are: a, e, i, o, and u.
     *
     * Input: s = "leetcoder"
     * Output: true
     *
     * Input: s = "bbcd"
     * Output: false
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public boolean doesAliceWin(String s) {
        String vowel = "aeiou";
        int n = s.length(), cnt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (vowel.indexOf(c) != -1) cnt++;
        }
        return cnt != 0;
    }
}