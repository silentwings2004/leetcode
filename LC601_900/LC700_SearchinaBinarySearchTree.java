package LC601_900;

public class LC700_SearchinaBinarySearchTree {
    /**
     * You are given the root of a binary search tree (BST) and an integer val.
     *
     * Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such
     * a node does not exist, return null.
     *
     * Input: root = [4,2,7,1,3], val = 2
     * Output: [2,1,3]
     *
     * Input: root = [4,2,7,1,3], val = 5
     * Output: []
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 5000].
     * 1 <= Node.val <= 10^7
     * root is a binary search tree.
     * 1 <= val <= 10^7
     * @param root
     * @param val
     * @return
     */
    // time = O(n), space = O(n)
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return root;
        if (root.val == val) return root;
        if (root.val > val) return searchBST(root.left, val);
        return searchBST(root.right, val);
    }
}