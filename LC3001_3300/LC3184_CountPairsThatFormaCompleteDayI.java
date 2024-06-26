package LC3001_3300;

public class LC3184_CountPairsThatFormaCompleteDayI {
    /**
     * Given an integer array hours representing times in hours, return an integer denoting the number of pairs i, j
     * where i < j and hours[i] + hours[j] forms a complete day.
     *
     * A complete day is defined as a time duration that is an exact multiple of 24 hours.
     *
     * For example, 1 day is 24 hours, 2 days is 48 hours, 3 days is 72 hours, and so on.
     *
     * Input: hours = [12,12,30,24,24]
     *
     * Output: 2
     *
     * Input: hours = [72,48,24,3]
     *
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= hours.length <= 100
     * 1 <= hours[i] <= 10^9
     * @param hours
     * @return
     */
    // time = O(n^2), space = O(1)
    public int countCompleteDayPairs(int[] hours) {
        int n = hours.length, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int t = hours[i] + hours[j];
                if (t % 24 == 0) res++;
            }
        }
        return res;
    }
}