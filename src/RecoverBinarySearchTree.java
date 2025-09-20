public class RecoverBinarySearchTree {
    private TreeNode wrongNode1, wrongNode2, predNode;
    private boolean foundBoth = false;

    public void recoverTree(TreeNode root) {
        findTwoSwapped(root);
        swap(this.wrongNode1, this.wrongNode2);
    }

    private void findTwoSwapped(TreeNode root) {
        if (root == null || this.foundBoth) return;
        findTwoSwapped(root.left);
        checkSwapped(root);
        findTwoSwapped(root.right);
    }

    private void checkSwapped(TreeNode root) {
        if (this.predNode != null && root.val < this.predNode.val) {
            this.wrongNode2 = root;
            if (this.wrongNode1 == null) this.wrongNode1 = this.predNode;
            else this.foundBoth = true;
        }
        this.predNode = root;
    }

    private void swap(TreeNode nodeToSwap1, TreeNode nodeToSwap2) {
        int tmp = nodeToSwap1.val;
        nodeToSwap1.val = nodeToSwap2.val;
        nodeToSwap2.val = tmp;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
