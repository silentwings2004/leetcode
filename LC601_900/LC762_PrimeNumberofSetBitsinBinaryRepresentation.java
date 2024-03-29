package LC601_900;
import java.util.*;
public class LC762_PrimeNumberofSetBitsinBinaryRepresentation {
    /**
     * Given two integers left and right, return the count of numbers in the inclusive range [left, right] having a
     * prime number of set bits in their binary representation.
     *
     * Recall that the number of set bits an integer has is the number of 1's present when written in binary.
     *
     * For example, 21 written in binary is 10101, which has 3 set bits.
     *
     * Input: left = 6, right = 10
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= left <= right <= 10^6
     * 0 <= right - left <= 10^4
     * @param left
     * @param right
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int countPrimeSetBits(int left, int right) {
        int res = 0;
        for (int i = left; i <= right; i++) {
            int num = Integer.bitCount(i);
            if (isPrime(num)) res++;
        }
        return res;
    }

    private boolean isPrime(int k) {
        if (k < 2) return false;
        if (k % 2 == 0) return k == 2;
        for (int i = 3; i * i <= k; i += 2) {
            if (k % i == 0) return false;
        }
        return true;
    }

    // S2: Ennumeration
    // time = O(n), space = O(1)
    public int countPrimeSetBits2(int left, int right) {
        HashSet<Integer> set = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));
        int res = 0;
        for (int i = left; i <= right; i++) {
            int x = Integer.bitCount(i);
            if (set.contains(x)) res++;
        }
        return res;
    }
}
