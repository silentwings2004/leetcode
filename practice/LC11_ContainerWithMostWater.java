package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ContainerWithMostWater
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 11. Container With Most Water
 */
public class LC11_ContainerWithMostWater {
    /**
     * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical
     * lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
     * with x-axis forms a container, such that the container contains the most water.
     *
     * Note: You may not slant the container and n is at least 2.
     *
     *
     *
     *
     *
     * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water
     * (blue section) the container can contain is 49.
     *
     *
     * Example:
     *
     * Input: [1,8,6,2,5,4,8,3,7]
     * Output: 49
     * @param height
     * @return
     */
    // S1
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while(i < j){
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]):
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }

    // S2
    public int maxArea2(int[] height) {
        
    }
}
