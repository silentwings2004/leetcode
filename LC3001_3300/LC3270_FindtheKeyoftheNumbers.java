package LC3001_3300;

public class LC3270_FindtheKeyoftheNumbers {
    /**
     * You are given three positive integers num1, num2, and num3.
     *
     * The key of num1, num2, and num3 is defined as a four-digit number such that:
     *
     * Initially, if any number has less than four digits, it is padded with leading zeros.
     * The ith digit (1 <= i <= 4) of the key is generated by taking the smallest digit among the ith digits of num1,
     * num2, and num3.
     * Return the key of the three numbers without leading zeros (if any).
     *
     * Input: num1 = 1, num2 = 10, num3 = 1000
     * Output: 0
     *
     * Input: num1 = 987, num2 = 879, num3 = 798
     * Output: 777
     *
     * Input: num1 = 1, num2 = 2, num3 = 3
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= num1, num2, num3 <= 9999
     * @param num1
     * @param num2
     * @param num3
     * @return
     */
    // time = O(1), space = O(1)
    public int generateKey(int num1, int num2, int num3) {
        int[] a = new int[4];
        for (int i = 0; i < 4; i++) {
            int x = num1 % 10;
            int y = num2 % 10;
            int z = num3 % 10;
            a[i] = Math.min(x, Math.min(y, z));
            num1 /= 10;
            num2 /= 10;
            num3 /= 10;
        }
        int res = 0;
        for (int i = 3; i >= 0; i--) res = res * 10 + a[i];
        return res;
    }
}