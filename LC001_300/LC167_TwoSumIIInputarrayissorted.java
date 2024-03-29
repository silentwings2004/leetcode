package LC001_300;
import java.util.*;
public class LC167_TwoSumIIInputarrayissorted {
    /**
     * Given an array of integers numbers that is already sorted in ascending order, find two numbers such that they
     * add up to a specific target number.
     *
     * Return the indices of the two numbers (1-indexed) as an integer array answer of size 2, where 1 <= answer[0]
     * < answer[1] <= numbers.length.
     *
     * You may assume that each input would have exactly one solution and you may not use the same element twice.
     *
     * Input: numbers = [2,7,11,15], target = 9
     * Output: [1,2]
     *
     * Constraints:
     *
     * 2 <= numbers.length <= 3 * 10^4
     * -1000 <= numbers[i] <= 1000
     * numbers is sorted in increasing order.
     * -1000 <= target <= 1000
     * Only one valid answer exists.
     * @param numbers
     * @param target
     * @return
     */
    // time = O(n), space = O(1)
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int i = 0, j = n - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] == target) return new int[]{i + 1, j + 1};
            if (numbers[i] + numbers[j] < target) i++;
            else j--;
        }
        return new int[0];
    }
}