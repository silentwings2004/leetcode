package LC601_900;
import java.util.*;
public class LC784_LetterCasePermutation {
    /**
     * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
     *
     * Return a list of all possible strings we could create. You can return the output in any order.
     *
     * Input: S = "a1b2"
     * Output: ["a1b2","a1B2","A1b2","A1B2"]
     *
     * Constraints:
     *
     * S will be a string with length between 1 and 12.
     * S will consist only of letters or digits.
     *
     * @param S
     * @return
     */
    // time = O(2^n * n), space = O(2^n * n)
    List<String> res;
    public List<String> letterCasePermutation(String s) {
        res = new ArrayList<>();
        char[] chars = s.toCharArray();
        dfs(chars, 0);
        return res;
    }

    private void dfs(char[] chars, int u) {
        if (u == chars.length) res.add(String.valueOf(chars));
        else {
            dfs(chars, u + 1);
            if (!Character.isDigit(chars[u])) {
                chars[u] ^= 32;
                dfs(chars, u + 1);
                chars[u] ^= 32;
            }
        }
    }
}
