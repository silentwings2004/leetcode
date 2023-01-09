package LC301_600;

public class LC520_DetectCapital {
    /**
     * We define the usage of capitals in a word to be right when one of the following cases holds:
     *
     * All letters in this word are capitals, like "USA".
     * All letters in this word are not capitals, like "leetcode".
     * Only the first letter in this word is capital, like "Google".
     * Given a string word, return true if the usage of capitals in it is right.
     *
     * Input: word = "USA"
     * Output: true
     *
     * Constraints:
     *
     * 1 <= word.length <= 100
     * word consists of lowercase and uppercase English letters.
     * @param word
     * @return
     */
    // time = O(n), space = O(1)
    public boolean detectCapitalUse(String word) {
        int n = word.length(), cnt = 0;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                cnt++;
                if (i == 0) flag = true;
            }
        }
        return cnt == 0 || cnt == n || cnt == 1 && flag;
    }
}