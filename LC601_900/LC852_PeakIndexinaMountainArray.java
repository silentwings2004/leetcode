package LC601_900;

public class LC852_PeakIndexinaMountainArray {
    /**
     * Let's call an array arr a mountain if the following properties hold:
     *
     * arr.length >= 3
     * There exists some i with 0 < i < arr.length - 1 such that:
     * arr[0] < arr[1] < ... arr[i-1] < arr[i]
     * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
     * Given an integer array arr that is guaranteed to be a mountain, return any i such that
     * arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
     *
     * Input: arr = [0,1,0]
     * Output: 1
     *
     * Constraints:
     *
     * 3 <= arr.length <= 10^4
     * 0 <= arr[i] <= 10^6
     * arr is guaranteed to be a mountain array.
     * Follow up: Finding the O(n) is straightforward, could you find an O(log(n)) solution?
     * @param arr
     * @return
     */
    // time = O(logn), space = O(1)
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int l = 1, r = n - 2;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (arr[mid - 1] < arr[mid]) l = mid;
            else r = mid - 1;
        }
        return r;
    }
}