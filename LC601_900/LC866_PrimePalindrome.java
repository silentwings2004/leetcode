package LC601_900;

public class LC866_PrimePalindrome {
    /**
     * Find the smallest prime palindrome greater than or equal to n.
     *
     * Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1.
     *
     * For example, 2,3,5,7,11 and 13 are primes.
     *
     * Recall that a number is a palindrome if it reads the same from left to right as it does from right to left.
     *
     * For example, 12321 is a palindrome.
     *
     * Input: n = 6
     * Output: 7
     *
     * Note:
     *
     * 1 <= n <= 10^8
     * The answer is guaranteed to exist and be less than 2 * 10^8.
     * @param n
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int primePalindrome(int n) {
        if (n > 7 && n <= 11) return 11; // 11本身是个质数，无法被下面只考虑奇数位翻转的case考虑到，需要单独考虑！

        String M = String.valueOf(n);
        int N = M.length() / 2;

        int a = (int)Math.pow(10, N);
        for (int i = a; i <= 20000; i++) {
            String s = String.valueOf(i);
            String s1 = s.substring(0, s.length() - 1);
            s1 = reverse(s1);
            s = s  + s1;

            int k = Integer.valueOf(s);
            if (k >= n && isPrime(k)) return k;
        }
        return -1;
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    private boolean isPrime(int k) {
        if (k < 2) return false;
        if (k % 2 == 0) return k == 2;
        for (int i = 3; i * i <= k; i += 2) {
            if (k % i == 0) return false;
        }
        return true;
    }

    // S2
    // time = O(n), space = O(1)
    public int primePalindrome2(int n) {
        if (n > 7 && n <= 11) return 11;
        for (int i = 1;; i++) {
            int x = get(i);
            if (x >= n && is_prime(x)) return x;
        }
    }

    private int get(int x) {
        String a = String.valueOf(x);
        String b = new StringBuilder(a).reverse().toString();
        return Integer.parseInt(a + b.substring(1));
    }

    private boolean is_prime(int x) {
        if (x < 2) return false;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}
/**
 * 又要质数，又要回文数，并没有什么共通的地方，没有什么巧妙的办法给求出来
 * x x x x x x
 * y y y z z z
 *
 * x x x x x
 * y y y z z
 *
 * 真正要枚举的只要10^4 => 可以接受
 * O(N) * (sqrt(N)) => O(10^6) 一般极限就是10^6
 * 找出比N大的数
 * 找有4位数的回文数
 * n = 10000
 * 10 => 1001
 * xyz -> xyzzyx 有偶数位的肯定不会是一个质数 => 肯定是11的倍数
 * x*1e5+x*1e0
 * y*1e4+y*1e1
 * z*1e3+z*1e2
 *
 * 10 => 101
 * 9999 => 99999999
 *
 * 100 => 10001
 * 20000 => 200000002
 */
