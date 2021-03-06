package easy;

public class InvertBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub



	}

	public TreeNode invertTree(TreeNode root) {

		// 268 ms
		if(root == null) return null;

		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;

		invertTree(root.left);
		invertTree(root.right);

		return root;

		/*if(root == null) return null;
        TreeNode tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);
        return root;*/
	}

}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}