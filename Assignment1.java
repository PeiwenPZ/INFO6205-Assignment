import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.*;

//TreeNode class
class Solution {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Question1 111. Minimum Depth of Binary Tree

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // this is a leaf
        if (root.left == null) {
            return 1 + minDepth(root.right);
        } else if (root.right == null) {
            return 1 + minDepth(root.left);
        }

        int left = minDepth(root.left) + 1;
        int right = minDepth(root.right) + 1;
        return Math.min(left, right);
    }

    // Question2 222. Count Complete Tree Nodes

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.right) + countNodes(root.left);
    }

    // Question3 515. Find Largest Value in Each Tree Row
    public static List<Integer> largestValues(TreeNode root) {
        // BFS level order traversal
        // edge case
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // queue : 5 3 9
        // ans: 1 3

        // not the end of the tree
        while (!queue.isEmpty()) {
            int size = queue.size();
            int largest = Integer.MIN_VALUE;

            // one layer
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.remove();
                largest = Math.max(largest, curr.val);

                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            // add to answer
            ans.add(largest);
        }

        return ans;
    }

    // question4 Leaf-Similar Trees
    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // DFS find the leaf of root1 root2 accordingly post order

        // base case
        if (root1 == null || root2 == null) {
            return root1.val == root2.val;
        }
        List<Integer> tree1 = new ArrayList<>();
        List<Integer> tree2 = new ArrayList<>();
        dfs(root1, tree1);
        dfs(root2, tree2);

        if (tree1.size() != tree2.size()) {
            return false;
        }

        for (int i = 0; i < Math.min(tree1.size(), tree2.size()); i++) {
            if (tree1.get(i) != tree2.get(i)) {
                return false;
            }
        }

        return true;

    }

    public static void dfs(TreeNode root, List<Integer> tree1) {
        // base case
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            tree1.add(root.val);
        }

        dfs(root.left, tree1);
        dfs(root.right, tree1);
    }

    // Question 5 1302. Deepest Leaves Sum
    public static int deepestLeavesSum(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int ans = 0;
        // iterate each layer
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans = 0;

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.remove();
                ans += curr.val;

                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
        }
        return ans;
    }

    // main class
    public static void main(String[] args) {

        // 1
        // / \
        // 2 3
        // / \
        // 4 5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Question1 Answer
        int result1 = minDepth(root);
        System.out.println(result1);

        // Question2 Answer
        int result2 = countNodes(root);
        System.out.println(result2);

        // Question3 Answer

        List<Integer> result3 = largestValues(root);
        System.out.println(result3);

        // Question4 Answer
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);

        boolean result4 = leafSimilar(root, root2);
        System.out.println(result4);

        // Question5 Answer
        int result5 = deepestLeavesSum(root2);
        System.out.println(result5);

    }
}