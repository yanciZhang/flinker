package com.yanci.tree;

public class BinaryTreeTest {
	
	public static void main(String[] args) {
		BalancedBinaryTree<String> tree = new BalancedBinaryTree<>();
		tree.addNode("50");
		tree.addNode("40");
		tree.addNode("60");
		tree.addNode("20");
		tree.addNode("70");
		//	  50
		//   / \
		//  40  60
		// /     \
		//20      70
		tree.inOrder();
		System.out.println();
		tree.addNode("66");
		//	  50
		//   / \
		//  40  66
		// /    / \
		//20   60   70
		tree.inOrder();
		tree.remove("66");
		System.out.println();
		tree.levelOrder();
		tree.remove("60");
		System.out.println();
		tree.levelOrder();
		tree.remove("70");
		System.out.println();
		tree.levelOrder();
	}
	
	public static void test1() {
		BinaryTree<String> tree = new BinaryTree<>();
		tree.addNode("D");
		tree.addNode("B");
		tree.addNode("A");
		tree.addNode("C");
		tree.addNode("E");
		tree.addNode("F");
		//    D
		//   / \
		//  B   E
		// / \   \
		//A   C   F
		
		tree.preOrder();
		System.out.println();
		tree.inOrder();
		System.out.println();
		tree.postOrder();
		System.out.println();
		tree.levelOrder();
		System.out.println();
		System.out.println("---find---");
		System.out.println(tree.findNode("C"));
		System.out.println("---getMin---");
		System.out.println(tree.getMin());
		System.out.println("---remove---");
		System.out.println(tree.remove("D"));
		tree.levelOrder();
	}

}
