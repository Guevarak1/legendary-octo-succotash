package org.example.problems;

import org.example.datastructures.TreeNode;

public class TreeProblems {
    //counting the max edges per subtree. will need global variable
    static int diameter = 0;
    static int maxNodeDepth = 0;

    // first three easies are bottom up recursive dfs approaches
    public static void main(String[] args) {
        TreeNode invertRoot = new TreeNode(4,
                new TreeNode(2,
                        new TreeNode(1), new TreeNode(3)),
                new TreeNode(7,
                        new TreeNode(6), new TreeNode(9)));

//        invertTree(invertRoot);
//        System.out.println(invertRoot);

        TreeNode balancedRoot = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15), new TreeNode(7)));
//        System.out.println(isBalanced(balancedRoot));

        TreeNode diameterRoot = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3));

//        diameterOfBinaryTree(diameterRoot);

        TreeNode maxDepthRoot = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7, new TreeNode(0), null)));

//        System.out.println(maxDepth(maxDepthRoot));

        TreeNode pRoot = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode qRoot = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(isSameTree(pRoot, qRoot));
    }

    //depth first pre-order traversal to recursively swap left and right nodes from current root node
    //Space O(n) time O(n)
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    //depth first search pre-order traversal
    public static boolean isBalanced(TreeNode root) {
        return subtreeLength(root) != -1;
    }

    //Space O(h) time O(n)
    private static int subtreeLength(TreeNode root) {
        //if bottom most node, return 0 depth
        if (root == null) return 0;

        //going all the way to the bottom to collect current depths of subtrees
        int left = subtreeLength(root.left);
        int right = subtreeLength(root.right);

        //if left or right side is greater than 1,
        // return -1 to short circuit response and return false in calling method
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        //else subtrees are balanced and we'll add 1 to increment size of tree node
        return Math.max(left, right) + 1;
    }

    //Space O(h) time O(n)
    //max diameter can or cant pass through root of tree,
    public static int diameterOfBinaryTree(TreeNode root) {
        if (root.left == null && root.right == null) return 0;
        dfsHeight(root);
        return diameter;
    }

    private static int dfsHeight(TreeNode root) {
        //0 because were at the leaf of a tree, no more height to calculate
        if (root == null) return 0;
        //continue to find max left height
        int left = dfsHeight(root.left);
        //continue to find right max height
        int right = dfsHeight(root.right);
        //set diameter to current left + right subtree edges max
        diameter = Math.max(diameter, left + right);

        //add 1 to append tree traverse
        return 1 + Math.max(left, right);
    }

    public static int maxDepth(TreeNode root) {
        depth(root);
        return maxNodeDepth;
    }

    private static int depth(TreeNode root) {
        if (root == null) return 1;
        int left = depth(root.left);
        int right = depth(root.right);

        maxNodeDepth = Math.max(maxNodeDepth, Math.max(left, right));
        return Math.max(left, right) + 1;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
