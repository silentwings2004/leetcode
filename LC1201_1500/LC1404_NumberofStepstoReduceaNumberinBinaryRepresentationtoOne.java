package LC1201_1500;

public class LC1404_NumberofStepstoReduceaNumberinBinaryRepresentationtoOne {
    /**
     * Given the binary representation of an integer as a string s, return the number of steps to reduce it to 1 under
     * the following rules:
     *
     * If the current number is even, you have to divide it by 2.
     *
     * If the current number is odd, you have to add 1 to it.
     *
     * It is guaranteed that you can always reach one for all test cases.
     *
     * Input: s = "1101"
     * Output: 6
     *
     * Input: s = "10"
     * Output: 1
     *
     * Input: s = "1"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 500
     * s consists of characters '0' or '1'
     * s[0] == '1'
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int numSteps(String s) {
        int n = s.length(), res = 0;
        for (int i = n - 1, carry = 0; i >= 0; i--) {
            int t = s.charAt(i) - '0' + carry;
            if (t == 1 && i == 0) break;
            carry = t / 2;
            t %= 2;
            if (t == 0) res++;
            else {
                res += 2;
                carry++;
            }
        }
        return res;
    }
}