package LC001_300;
import java.util.*;
public class LC12_IntegertoRoman {
    /**
     * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
     *
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is
     * simply X + II. The number 27 is written as XXVII, which is XX + V + II.
     *
     * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not
     * IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
     * The same principle applies to the number nine, which is written as IX. There are six instances where subtraction
     * is used:
     *
     * I can be placed before V (5) and X (10) to make 4 and 9.
     * X can be placed before L (50) and C (100) to make 40 and 90.
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     * Given an integer, convert it to a roman numeral.
     *
     * Input: num = 1994
     * Output: "MCMXCIV"
     *
     * Constraints:
     *
     * 1 <= num <= 3999
     * @param num
     * @return
     */
    // time = O(1), space = O(1)
    public String intToRoman(int num) {
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strs = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }
}
/**
 * 每1位可能有几个字符
 * 模拟查表
 * 从千位开始看，从高位开始拼接
 * 100，200，300 | 600，700，800 + C
 * 没有规律的几个记录下来
 * 从大到小
 */