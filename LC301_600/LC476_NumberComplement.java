package LC301_600;

public class LC476_NumberComplement {
    /**
     * The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in
     * its binary representation.
     *
     * For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
     * Given an integer num, return its complement.
     *
     * Input: num = 5
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= num < 2^31
     *
     * Note: This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
     * @param num
     * @return
     */
    // S1
    // time = O(logn), space = O(1)
    public int findComplement(int num) {
        int res = 0, i = 0;
        while (num > 0) {
            if ((num & 1) == 0) res += Math.pow(2, i);
            i++;
            num >>= 1;
        }
        return res;
    }

    // S2
    // time = O(logn), space = O(1)
    public int findComplement2(int num) {
        if (num == 0) return 1;
        int cnt = 0;
        for (int x = num; x > 0; x >>= 2) cnt++;
        return (int)(~num & (1L << cnt) - 1);
    }

    // S3
    // time = O(logn), space = O(1)
    public int findComplement3(int num) {
        int j = 31;
        while (j >= 0 && (num >> j & 1) == 0) j--;
        for (int i = j; i >= 0; i--) num ^= 1 << i;
        return num;
    }
}