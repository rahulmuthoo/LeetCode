package easy;

public class PathSum {
	
	// https://www.youtube.com/watch?v=Jg4E4KZstFE
    public boolean hasPathSum(TreeNode root, int sum) {
        
        if(root == null) return false;
        
        // Leaf case
        if(root.left == null && root.right == null && root.val == sum) return true;
        
        boolean left = hasPathSum(root.left, sum - root.val);
        boolean right = hasPathSum(root.right, sum - root.val);
        
        return left || right;
        
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
