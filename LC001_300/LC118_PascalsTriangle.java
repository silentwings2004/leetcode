package LC001_300;
import java.util.*;
public class LC118_PascalsTriangle {
    /**
     * Given an integer numRows, return the first numRows of Pascal's triangle.
     *
     * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
     *
     * Input: numRows = 5
     * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     *
     * Constraints:
     *
     * 1 <= numRows <= 30
     * @param numRows
     * @return
     */
    // S1
    // time = O(n^2), space = O(n)
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> t = new ArrayList<>();
            t.add(1);
            for (int j = 1; j < i; j++) {
                t.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            }
            if (i > 0) t.add(1);
            res.add(t);
        }
        return res;
    }

    // S2
    // time = O(n^2), space = O(n)
    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        int n = numRows;
        for (int i = 0; i < n; i++) {
            List<Integer> q = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                int a = 0, b = 0;
                if (i == 0) {
                    q.add(1);
                    break;
                } else {
                    if (j > 0) a = res.get(i - 1).get(j - 1);
                    if (j < i) b = res.get(i - 1).get(j);
                    q.add(a + b);
                }
            }
            res.add(q);
        }
        return res;
    }
}
