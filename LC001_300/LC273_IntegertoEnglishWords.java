package LC001_300;
import java.util.*;
public class LC273_IntegertoEnglishWords {
    /**
     * Convert a non-negative integer num to its English words representation.
     *
     * Input: num = 123
     * Output: "One Hundred Twenty Three"
     *
     * Input: num = 1234567891
     * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
     * Constraints:
     *
     * 0 <= num <= 2^31 - 1
     * @param num
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        else {
            String res = intToStr(num);
            return res.substring(1);
        }
    }
    // 2^31 = 2 * 2^30 = 2 * (1^3)^3 = 2 * 10^9 = 2 billion
    private String intToStr(int n) {
        String[] digits = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = new String[]{"Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        if (n >= 1000000000) {
            return intToStr(n / 1000000000) + " Billion" + intToStr(n % 1000000000);
        } else if (n >= 1000000) {
            return intToStr(n / 1000000) + " Million" + intToStr(n % 1000000);
        } else if (n >= 1000) {
            return intToStr(n / 1000) + " Thousand" + intToStr(n % 1000);
        } else if (n >= 100) {
            return intToStr(n / 100) + " Hundred" + intToStr(n % 100);
        } else if (n >= 20) {
            return " " + tens[n / 10] + intToStr(n % 10);
        } else if (n >= 1){
            return " " + digits[n];
        } else return "";
    }

    // S2
    // time = O(n), space = O(1)
    String[] less20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] thousands = {"", "Thousand", "Million", "Billion"};
    public String numberToWords2(int num) {
        if (num == 0) return "Zero";

        String res = "";
        int i = 0;

        while (num > 0) {
            if (num % 1000 != 0) {
                res = helper(num % 1000) + thousands[i] + " " + res;
            }
            num /= 1000;
            i++;
        }
        return res.trim();
     }

    private String helper(int num) {
        if (num == 0) return "";
        if (num < 20) return less20[num % 20] + " ";
        else if (num < 100) {
            return tens[num / 10] + " " + helper(num % 10);
        } else {
            return less20[num / 100] + " Hundred " + helper(num % 100);
        }
    }

    // S3
    // time = O(n), space = O(1)
    String[] num0_19 = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] num20_90 = new String[]{"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] num1000 = new String[]{"Billion ", "Million ", "Thousand ", ""};
    public String numberToWords3(int num) {
        StringBuilder sb = new StringBuilder();
        if (num == 0) return "Zero";
        for (int i = (int) 1e9, j = 0; i >= 1; i /= 1000, j++) {
            if (num >= i) {
                sb.append(get(num / i)).append(num1000[j]);
                num %= i;
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private String get(int x) {
        StringBuilder sb = new StringBuilder();
        if (x >= 100) {
            sb.append(num0_19[x / 100]).append(" Hundred ");
            x %= 100;
        }
        if (x >= 20) {
            sb.append(num20_90[x / 10 - 2]).append(" ");
            x %= 10;
            if (x > 0) sb.append(num0_19[x]).append(" ");
        } else if (x > 0) sb.append(num0_19[x]).append(" ");
        return sb.toString();
    }
}