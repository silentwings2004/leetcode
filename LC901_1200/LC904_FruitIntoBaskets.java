package LC901_1200;
import java.util.*;
public class LC904_FruitIntoBaskets {
    /**
     * You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are
     * represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
     *
     * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
     *
     * You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount
     * of fruit each basket can hold.
     * Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree)
     * while moving to the right. The picked fruits must fit in one of your baskets.
     * Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
     * Given the integer array fruits, return the maximum number of fruits you can pick.
     *
     * Input: fruits = [1,2,1]
     * Output: 3
     *
     * Input: fruits = [0,1,2,2]
     * Output: 3
     *
     * Input: fruits = [1,2,3,2,2]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= fruits.length <= 10^5
     * 0 <= fruits[i] < fruits.length
     * @param fruits
     * @return
     */
    // time = O(n), space = O(n)
    public int totalFruit(int[] fruits) {
        int n = fruits.length, res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0, j = 0; i < n; i++) {
            map.put(fruits[i], map.getOrDefault(fruits[i], 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[j], map.get(fruits[j]) - 1);
                if (map.get(fruits[j]) == 0) map.remove(fruits[j]);
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
