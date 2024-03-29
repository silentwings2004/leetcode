package LC301_600;

public class LC372_SuperPow {
    /**
     * Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer
     * given in the form of an array.
     *
     * Input: a = 2, b = [3]
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= a <= 2^31 - 1
     * 1 <= b.length <= 2000
     * 0 <= b[i] <= 9
     * b does not contain leading zeros.
     * @param a
     * @param b
     * @return
     */
    // time = O(sum(logbi)), space = O(1)
    final long p = 1337;
    public int superPow(int a, int[] b) {
        if (a == 1) return 1;

        long res = 1;
        for (int x : b) {
            res = qmi(res, 10) * qmi(a, x) % p;
        }
        return (int) res;
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % p;
            a = a * a % p;
            k >>= 1;
        }
        return res;
    }
}