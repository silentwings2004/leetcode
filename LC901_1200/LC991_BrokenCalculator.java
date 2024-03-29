package LC901_1200;
import java.util.*;
public class LC991_BrokenCalculator {
    /**
     * On a broken calculator that has a number showing on its display, we can perform two operations:
     *
     * Double: Multiply the number on the display by 2, or;
     * Decrement: Subtract 1 from the number on the display.
     * Initially, the calculator is displaying the number X.
     *
     * Return the minimum number of operations needed to display the number Y.
     *
     * Input: X = 2, Y = 3
     * Output: 2
     *
     * Note:
     *
     * 1 <= X <= 10^9
     * 1 <= Y <= 10^9
     *
     * @param startValue
     * @param target
     * @return
     */
    // time = O(log(target)), space = O(1)
    public int brokenCalc(int startValue, int target) {
        int x = startValue, y = target;
        int res = 0;
        while (y > x) {
            if (y % 2 == 1) y++;
            else y /= 2;
            res++;
        }
        return res + x - y;
    }
}
/**
 * 首先，当X>Y时，容易判断出我们只有对X持续地减一才能实现Y。
 * 当X<Y时，我们也容易判断出，如果Y是奇数，那么最后一步操作一定是-1（否则x2是得不到奇数的），所以我们只需要递归考虑brokenCalc(X,Y+1)即可。
 * 那么如果Y是偶数呢？我们将任意从X变换成Y的过程，可以分为两个大类：第一类是最后若干步是-1，第二类是最后若干步是x2。这两类方法可以分别写作：
 * (a) X ... [x2 x2 ... x2][-1 -1 ... -1] = Y
 * (b) X ... [-1 -1 ... -1][x2 x2 ... x2] = Y
 * 注意，我们并不关心“最后若干步”具体是有多少步。所以上面的两种方法就可以代表任意从X到Y的变换过程。
 * 其中，第一种方法最后的若干步-1一定是偶数次，假设是2k。
 * 我们容易看出第一种方法其实是不高效的，
 * 因为通过简单的分解就可以看出X ... [x2 x2 ... x2][-1 -1 ... -1]{2k个}等效于X ... [x2 x2 ...][-1 -1 ... -1]{k个} x2。
 * 而前者最后需要1+2k步，而后者只需要k+1步。
 * 所以结论是，当Y>X并且Y是偶数的时候，方法(a)是不高效的，不如等效的方法(b)。
 * 也就是说，从X到Y最高效的变化方法，最后一步应该是x2而不是-1.因此下一步我们只需要递归考虑brokenCalc(X,Y/2)即可。
 */
