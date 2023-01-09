package LC001_300;
import java.util.*;
public class LC29_DivideTwoIntegers {
    /**
     * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod
     * operator.
     *
     * Return the quotient after dividing dividend by divisor.
     *
     * The integer division should truncate toward zero, which means losing its fractional part. For example,
     * truncate(8.345) = 8 and truncate(-2.7335) = -2.
     *
     * Note:
     *
     * Assume we are dealing with an environment that could only store integers within the 32-bit signed integer
     * range: [−2^31,  2^31 − 1]. For this problem, assume that your function returns 231 − 1 when the division result
     * overflows.
     *
     * Input: dividend = 10, divisor = 3
     * Output: 3
     *
     * @param dividend
     * @param divisor
     * @return
     */
    // S1
    // time = O(logk), space = O(1)
    public int divide(int dividend, int divisor) {
        long a = (long) dividend, b = (long) divisor;
        int sign = 1;
        if (a < 0 && b > 0 || a > 0 && b < 0) sign = -1;

        a = Math.abs(a);
        b = Math.abs(b);

        long res = 0;
        while (a >= b) {
            long c = b, sum = 1;
            while ((c << 1) < a) {
                c <<= 1;
                sum <<= 1;
            }
            a -= c;
            res += sum;
        }

        res *= sign;
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        return (int) res;
    }

    // S2
    // time = O(logk), space = O(logk)
    public int divide2(int dividend, int divisor) {
        long x = dividend, y = divisor;
        List<Long> exp = new ArrayList<>();
        boolean is_minus = false;
        if (x < 0 && y > 0 || x > 0 && y < 0) is_minus = true;

        long a = Math.abs(x), b = Math.abs(y);
        for (long i = b; i <= a; i = i + i) exp.add(i);

        long res = 0;
        for (int i = exp.size() - 1; i >= 0; i--) {
            if (a >= exp.get(i)) {
                a -= exp.get(i);
                res += 1L << i;
            }
        }

        if (is_minus) res = -res;
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        return (int) res;
    }
}

/**
 * 9 / 4 -> 2, 1
 * -9 / 4 -> -2, -1
 *
 * 10000 / 1 = 10000
 *
 * 1*b
 * 2*b
 * 3*b
 * 8*b
 * 16*b
 * ...
 * 1024*b = 2^10 (1 >>10) sum += sum 本质上是加法
 * 2048&*b
 * 扣掉一个尽可能大的数的整数倍，尽量减去大头，一直减下去减到没
 * INT_MIN -> 取绝对值就会溢出，先转化成long,再取绝对值就没问题
 *
 * 本题的基本思想就是将divisor不断用左移的方法乘以2来逼近dividend，
 * 然后将dividend减去倍乘之后的divisor，再重复这个过程直至被除数小于除数。
 * 记录这个过程中divisor“倍乘”的总次数，即为答案。
 * 特别注意：
 * 注意可能的负号。提前将被除数和除数取绝对值，符号最后留给商。
 * 对于整形取绝对值，常设的陷阱就是对于INT_MIN，取反之后就会溢出。为了处理起来简单，把变量统统转换为long long类型。
 * Overflow的意思就是结果>INT_MAX
 *
 * x / y = k = (110010) = 2^1 + 2^4 + 2^5  最多只有logk项
 * x - y * k => x - 2^1 * y - 2^4 * y - 2^5 * y
 * 预处理2^0 * y, 2^1 * y, ... 2^30 * y  -> 31项
 * x >= 2^30 * y
 */

