package LC1201_1500;

public class LC1360_NumberofDaysBetweenTwoDates {
    /**
     * Write a program to count the number of days between two dates.
     *
     * The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.
     *
     * Input: date1 = "2019-06-29", date2 = "2019-06-30"
     * Output: 1
     *
     * Input: date1 = "2020-01-15", date2 = "2019-12-31"
     * Output: 15
     *
     * Constraints:
     *
     * The given dates are valid dates between the years 1971 and 2100.
     * @param date1
     * @param date2
     * @return
     */
    // time = O(1), space = O(1)
    public int daysBetweenDates(String date1, String date2) {
        return Math.abs(helper(date1) - helper(date2));
    }

    private int helper(String s) {
        String[] strs = s.split("-");
        int year = Integer.parseInt(strs[0]);
        int month = Integer.parseInt(strs[1]);
        int day = Integer.parseInt(strs[2]);

        int res = 0;
        int[] months = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int i = 1971; i < year; i++) res += isLeapYear(i) ? 366 : 365;
        for (int i = 1; i < month; i++) res += months[i - 1];
        res += day;
        if (isLeapYear(year) && month > 2) res++;
        return res;
    }

    private boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }
}
