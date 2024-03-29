package LC601_900;
import java.util.*;
public class LC863_AllNodesDistanceKinBinaryTree {
    /**
     * We are given a binary tree (with root node root), a target node, and an integer value k.
     * <p>
     * Return a list of the values of all nodes that have a distance k from the target node.  The answer can be returned
     * in any order.
     * <p>
     * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
     * <p>
     * Output: [7,4,1]
     * <p>
     * Note:
     * <p>
     * The given tree is non-empty.
     * Each node in the tree has unique values 0 <= node.val <= 500.
     * The target node is a node in the tree.
     * 0 <= k <= 1000.
     *
     * @param root
     * @param target
     * @param K
     * @return
     */
    // time = O(n), space = O(n)
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();

        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        buildMap(map, root, null);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);

        HashSet<TreeNode> set = new HashSet<>();
        set.add(target);

        int step = 0;
        while (!queue.isEmpty()) {
            if (step == K) break;
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (map.containsKey(cur) && set.add(map.get(cur))) queue.offer(map.get(cur));
                if (cur.left != null && set.add(cur.left)) queue.offer(cur.left);
                if (cur.right != null && set.add(cur.right)) queue.offer(cur.right);
            }
            step++;
        }

        while (!queue.isEmpty()) res.add(queue.poll().val);
        return res;
    }

    private void buildMap(HashMap<TreeNode, TreeNode> map, TreeNode cur, TreeNode parent) {
        if (cur == null) return;
        if (parent != null) map.put(cur, parent);
        if (cur.left != null) buildMap(map, cur.left, cur);
        if (cur.right != null) buildMap(map, cur.right, cur);
    }

    // S2: dfs
    // time = O(n), space = O(n)
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();
        dfs(root, target, k, res);
        return res;
    }

    private int dfs(TreeNode node, TreeNode target, int k, List<Integer> res) {
        if (node == null) return -1;
        if (node == target) {
            fetch(node, k, res);
            return 0;
        }

        int depth1 = dfs(node.left, target, k, res);
        if (depth1 != -1) {
            if (depth1 == k - 1) res.add(node.val);
            else fetch(node.right, k - depth1 - 2, res); // depth1 < k - 1 => k - depth1 - 2 >= 0!
            return depth1 + 1;
        }

        int depth2 = dfs(node.right, target, k, res);
        if (depth2 != -1) {
            if (depth2 == k - 1) res.add(node.val);
            else fetch(node.left, k - depth2 - 2, res);
            return depth2 + 1;
        }
        return -1;
    }

    private void fetch(TreeNode node, int k, List<Integer> res) {
        if (node == null) return;
        if (k == 0) {
            res.add(node.val);
            return;
        }
        fetch(node.left, k - 1, res);
        fetch(node.right, k - 1, res);
    }

    // S3
    // time = O(n), space = O(n)
    class Solution {
        List<Integer> res;
        HashMap<TreeNode, List<TreeNode>> map;

        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            res = new ArrayList<>();
            map = new HashMap<>();

            dfs1(root);
            dfs2(target, null, k);
            return res;
        }

        private void dfs1(TreeNode node) {
            if (node == null) return;

            if (node.left != null) {
                map.putIfAbsent(node, new ArrayList<>());
                map.putIfAbsent(node.left, new ArrayList<>());
                map.get(node).add(node.left);
                map.get(node.left).add(node);
                dfs1(node.left);
            }

            if (node.right != null) {
                map.putIfAbsent(node, new ArrayList<>());
                map.putIfAbsent(node.right, new ArrayList<>());
                map.get(node).add(node.right);
                map.get(node.right).add(node);
                dfs1(node.right);
            }
        }

        private void dfs2(TreeNode node, TreeNode parent, int k) {
            if (k == 0) res.add(node.val);
            else {
                for (TreeNode next : map.getOrDefault(node, new ArrayList<>())) {
                    if (next != parent) {
                        dfs2(next, node, k - 1);
                    }
                }
            }
        }
    }
}
/**
 * 本题的关键点是，任何两个节点AB之间的路径，都可以想象成有一个“拐点”O，其中OA是沿左子树向下的路径，OB是沿右子树向下的路径。
 * 我们可以递归处理每一个节点node，设想它是这个拐点，A是target并位于其中一个分支，那么如何在另一个分支中找到B？
 * 显然，假设我们能得到target到node->left之间的距离是t，那么我们只需要从node->right出发往下走k-2-t步，所抵达的节点就都是符合要求的B点。
 * 同理，如果target位于node->right分支，类似的处理。
 * 需要单独处理的情况就是node==target，此时我们找的就是从node开始往下走k步到达的节点。
 * 注意，DFS(node)函数的一个副产品就是可以返回target到达node的距离（假设target在node的下方），这样就可以避免再多写一个递归函数。
 * 本题和543.Diameter-of-Binary-Tree的套路是一样的。也就是说，对于树里面任何两点之间的距离，优先去想它的拐点。
 * S2: DFS
 * 任意2个结点之间的路径，只要是一棵树，我们看它的一个拐点
 */