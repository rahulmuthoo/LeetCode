package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostorderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTreePostorderTraversal Obj = new BinaryTreePostorderTraversal();
	}



	// https://www.youtube.com/watch?v=qT65HltK2uE
	// Time and space - O(n)
	public List<Integer> postorderTraversal1(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		Stack<TreeNode> stack2 = new Stack<TreeNode>();

		if(root== null) return result;

		stack1.push(root);

		while(!stack1.isEmpty())
		{
			TreeNode node = stack1.pop();
			stack2.push(node);

			if(node.left != null) stack1.push(node.left);
			if(node.right != null) stack1.push(node.right);
		}

		while(!stack2.isEmpty())
		{
			TreeNode node = stack2.pop();
			result.add(node.val);
		}

		return result;
	}

	// Using single stack - derived from preOrder
	// https://leetcode.com/discuss/9736/accepted-code-with-explaination-does-anyone-have-better-idea
	public List<Integer> postorderTraversal2(TreeNode root) 
	{
		List<Integer> result = new ArrayList<Integer>();
		if(root == null) return result;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		/* Pop all items one by one. Do following for every popped item
			       a) print it
			       b) push its right child
			       c) push its left child
		        Note that right child is pushed first so that left is processed first 
		 */

		while(!stack.isEmpty())
		{
			TreeNode node = stack.pop();
			result.add(node.val);			
			if(node.left != null) stack.push(node.left);
			if(node.right != null) stack.push(node.right);
		}

		Collections.reverse(result);

		return result;
	}

	// http://www.programcreek.com/2012/12/leetcode-solution-of-iterative-binary-tree-postorder-traversal-in-java/
	// It's tricky. Try doing it on pen and paper to understand it properly
	public List<Integer> postorderTraversal(TreeNode root) 
	{
		List<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if(root== null) return result;
		stack.push(root);

		TreeNode prev = null;

		while(!stack.empty())
		{
			TreeNode curr = stack.peek();

			// If the current node is the left or right child of the previous node, 
			// then keep going down the tree and add left/right node to stack
			// If you have reached the leaf, process it and pop stack
			// prev == null is the situation for the root node
			if(prev == null || prev.left == curr || prev.right == curr)
			{
				if(curr.left != null) stack.push(curr.left);			
				else if(curr.right != null) stack.push(curr.right);

				// Leaf node reached
				// When there are no children for current node, i.e., the current node is a leaf, pop it from the stack. 
				else
				{
					stack.pop();
					result.add(curr.val);
				}

			}

			// This is when we are retracting or going up. We need to check if there is something on the right. 
			// because of second part in here- <left><right><root>
			// Go up the tree from left node. Need to check if there is a right child
			// if yes, push it to stack otherwise, process parent and pop stack
			else if(curr.left == prev)
			{
				if(curr.right != null)
				{
					stack.push(curr.right);
				}
				else
				{
					stack.pop();
					result.add(curr.val);
				}
			}

			// This is when we are retracting or going up
			// Now, I am done with left and right in here- <left><right><root>. I can process the root.
			// go up the tree from right node 
			// after coming back from right node, process parent node and pop stack.
			else if(curr.right == prev)
			{
				stack.pop();
				result.add(curr.val);
			}

			prev = curr;
		}

		return result;
	}

	public void IterativePostorder(TreeNode root){
		/*
		 * 	1.1 Create an empty stack
			2.1 Do following while root is not NULL
    			a) Push root's right child and then root to stack.
    			b) Set root as root's left child.
			2.2 Pop an item from stack and set it as root.
    			a) If the popped item has a right child and the right child 
       				is at top of stack, then remove the right child from stack,
       				push the root back and set root as root's right child.
    			b) Else print root's data and set root as NULL.
			2.3 Repeat steps 2.1 and 2.2 while stack is not empty.
		 */
		Stack<TreeNode> s= new Stack<TreeNode>();

		while(true)
		{
			if(root!=null)
			{
				s.push(root.right);
				s.push(root);
				root = root.left;
				continue;
			}
			else
			{
				if(s.size()!=0)
				{
					TreeNode curr= s.pop();
					if(s.size()!=0)
					{
						if(curr.right == s.peek())
						{
							root=s.pop();
							s.push(curr);
							continue;
						}
					}
					System.out.print(curr.val+" ,");
					root=null;
				}
				
				else
				{
					break;
				}
			}
		}
	}

}
