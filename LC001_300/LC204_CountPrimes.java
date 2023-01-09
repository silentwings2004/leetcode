package LC001_300;
import java.util.*;
public class LC204_CountPrimes {
    /**
     * Count the number of prime numbers less than a non-negative number, n.
     *
     * Input: n = 10
     * Output: 4
     *
     * Constraints:
     *
     * 0 <= n <= 5 * 10^6
     * @param n
     * @return
     */
    // S1
    // time = O(sqrt(n) * log(logn)), space = O(n)
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int res = 0;

        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                res++;
                for (int j = 2; i * j < n; i++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return res;
    }

    // S2: Eratosthenes
    // time = O(n(logn)(loglogn)), space = O(n)
    public int countPrimes2(int n) {
        int[] q = new int[n + 1];
        for (int i = 2; i * i <= n; i++) {
            if (q[i] == 0) {
                int j = i * 2;
                while (j < n) {
                    q[j] = 1;
                    j += i;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (q[i] == 0) count++;
        }
        return count;
    }

    // S3: 线性筛法
    // time = O(n), space = O(n)
    public int countPrimes3(int n) {
        boolean[] st = new boolean[n + 1];
        int[] primes = new int[n];
        int idx = 0;

        for (int i = 2; i < n; i++) {
            if (!st[i]) primes[idx++] = i;
            for (int j = 0; i * primes[j] < n; j++) {
                st[i * primes[j]] = true;
                if (i % primes[j] == 0) break;
            }
        }
        return idx;
    }
}
/**
 * 线性筛法
 * 每个合数N 一定会被且只会被最小质因子筛一次
 * p是N的最小质因子
 * i= N/p  p <= i
 * pj 一定 <+ i 的所有质因子
 * 因此pj一定是pj * i的最小质因子
 */