package LC901_1200;
import java.util.*;
public class LC919_CompleteBinaryTreeInserter {
    /**
     * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled,
     * and all nodes are as far left as possible.
     *
     * Design an algorithm to insert a new node to a complete binary tree keeping it complete after the insertion.
     *
     * Implement the CBTInserter class:
     *
     * CBTInserter(TreeNode root) Initializes the data structure with the root of the complete binary tree.
     * int insert(int v) Inserts a TreeNode into the tree with value Node.val == val so that the tree remains complete,
     * and returns the value of the parent of the inserted TreeNode.
     * TreeNode get_root() Returns the root node of the tree.
     *
     * Input
     * ["CBTInserter", "insert", "insert", "get_root"]
     * [[[1, 2]], [3], [4], []]
     * Output
     * [null, 1, 2, [1, 2, 3, 4]]
     *
     * Constraints:
     *
     * The number of nodes in the tree will be in the range [1, 1000].
     * 0 <= Node.val <= 5000
     * root is a complete binary tree.
     * 0 <= val <= 5000
     * At most 104 calls will be made to insert and get_root.
     */
    TreeNode root;
    List<TreeNode> h;
    public LC919_CompleteBinaryTreeInserter(TreeNode root) {
        h = new ArrayList<>();
        this.root = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            h.add(cur);
            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
        }
    }

    public int insert(int val) {
        TreeNode t = new TreeNode(val);
        h.add(t);
        int k = h.size();
        int p = k / 2;
        if (p * 2 == k) h.get(p - 1).left = t;
        else h.get(p - 1).right = t;
        return h.get(p - 1).val;
    }

    public TreeNode get_root() {
        return root;
    }
}
