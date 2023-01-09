package LC601_900;
import java.util.*;
public class LC872_LeafSimilarTrees {
    /**
     * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value
     * sequence.
     *
     * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
     *
     * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
     *
     * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
     *
     * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
     * Output: true
     *
     * Input: root1 = [1,2,3], root2 = [1,3,2]
     * Output: false
     *
     * Constraints:
     *
     * The number of nodes in each tree will be in the range [1, 200].
     * Both of the given trees will have values in the range [0, 200].
     * @param root1
     * @param root2
     * @return
     */
    // time = O(n), space = O(n)
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        dfs(root1, a);
        dfs(root2, b);

        return a.equals(b);
    }

    private void dfs(TreeNode node, List<Integer> nums) {
        if (node == null) return;
        if (node.left == null && node.right == null) nums.add(node.val);
        dfs(node.left, nums);
        dfs(node.right, nums);
    }
}