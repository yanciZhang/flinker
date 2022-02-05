package com.yanci.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }
* @param <T>
 */
public class BinaryTree<T extends Comparable<T>> {

	protected Node<T> root;

	public boolean addNode(T data) {

		if (root == null) {
			root = new Node<T>(data);
			return true;
		}
		root = addSubNode(root, data);
		return true;
	}

	private Node<T> addSubNode(Node<T> current, T data) {
		if(current == null) {
			return new Node<T>(data);
		}
		if (data.compareTo(current.data) < 0) {
			current.left = addSubNode(current.left, data);
		} else if (data.compareTo(current.data) > 0) {
			current.right = addSubNode(current.right, data);
		} else {
			current.data = data;
		}
		return current;
	}
	
	/**
	 * 前序
	 */
	public void preOrder() {
		preOrderSub(root);
	}
	
	private void preOrderSub(Node<T> node) {
		if(node == null) {
			return;
		}
		System.out.print(node.data + "-");
		preOrderSub(node.left);
		preOrderSub(node.right);
	}
	
	/**
	 * 中序
	 */
	public void inOrder(){
		inOrderSub(root);
	}
	
	private void inOrderSub(Node<T> node) {
		if(node == null) {
			return;
		}
		inOrderSub(node.left);
		System.out.print(node.data + "-");
		inOrderSub(node.right);
	}
	
	/**
	 * 后序
	 */
	public void postOrder() {
		postOrderSub(root);
	}
	
	private void postOrderSub(Node<T> node) {
		if(node == null) {
			return;
		}
		postOrderSub(node.left);
		postOrderSub(node.right);
		System.out.print(node.data + "-");
	}

	/**
	 * 层序
	 */
	public void levelOrder() {
		if(root == null) {
			return;
		}
		
		Queue<Node<T>> q = new LinkedList<>();
		q.offer(root);
		while(!q.isEmpty()) {
			Node<T> node = q.poll();
			if(node == null) {
				continue;
			}
			System.out.print(node.data + "-");
			if(node.left != null) {
				q.add(node.left);
			}
			if(node.right != null) {
				q.add(node.right);
			}
			
		}
	}
	
	public T remove(T v) {
		if(root == null) {
			return null;
		}
		Node<T> t = removeSub(null, root, v, null);
		if(t == null) {
			return null;
		}
		return t.data;
	}
	
	private Node<T> removeSub(Node<T> parent, Node<T> current, T target, LeftPosition lp) {
		if(current == null) {
			return null;
		}
		if (target.compareTo(current.data) < 0) {
			return removeSub(current, current.left, target, LeftPosition.LEFT);
		} else if (target.compareTo(current.data) > 0) {
			return removeSub(current, current.right, target, LeftPosition.RIGHT);
		} else {
			// 相等
			// 叶子节点
			if (current.left == null && current.right == null) {
				if(lp == null) {
					// root
					root = null;
				}else if(LeftPosition.LEFT.equals(lp)) {
					parent.left = null;
				}else if(LeftPosition.RIGHT.equals(lp)) {
					parent.right = null;
				}
				return current;
			}
			// 只有一个子树
			if(current.left == null || current.right == null) {
				if(lp == null) {
					// root
					root = current.left == null ? current.right : current.left;
				}else if(LeftPosition.LEFT.equals(lp)) {
					parent.left = current.left == null ? current.right : current.left;
				}else if(LeftPosition.RIGHT.equals(lp)) {
					parent.right = current.left == null ? current.right : current.left;
				}
				return current;
			}
			// 有两个子树，取右子树，最小的替换current位置，并删除右子树替换的那个节点
			Node<T> rightMin = getMin(current.right);
			// 删除右子树的rightMin
			removeSub(null, current, rightMin.data, null);
			if(lp == null) {
				// root
				rightMin.left = root.left;
				rightMin.right = root.right;
				root = rightMin;
			}else if(LeftPosition.LEFT.equals(lp)) {
				rightMin.left = current.left;
				rightMin.right = current.right;
				parent.left = rightMin;
			}else if(LeftPosition.RIGHT.equals(lp)) {
				rightMin.left = current.left;
				rightMin.right = current.right;
				parent.right = rightMin;
			}
			return current;
		}
	}
	
	public T getMin() {
		Node<T> n = getMin(root);
		if(n == null) {
			return null;
		}
		return n.data;
	}
	
	protected Node<T> getMin(Node<T> node) {
		if(node == null) {
			return node;
		}
		if(node.left != null) {
			return getMin(node.left);
		}
		return node;
	}
	
	public T findNode(T target){
		Node<T> n = findNode(root, target);
		if(n == null) {
			return null;
		}
		return n.data;
	}
	
	private Node<T> findNode(Node<T> current, T target) {
		if(current == null) {
			return null;
		}
		if (target.compareTo(current.data) < 0) {
			return findNode(current.left, target);
		} else if (target.compareTo(current.data) > 0) {
			return findNode(current.right, target);
		} else {
			return current;
		}
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BinaryTree [root=").append(root).append("]");
		return builder.toString();
	}


	protected static class Node<T> {

		protected T data;

		protected Node<T> left;

		protected Node<T> right;

		public Node(T data) {
			this.data = data;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node<T> getLeft() {
			return left;
		}

		public void setLeft(Node<T> left) {
			this.left = left;
		}

		public Node<T> getRight() {
			return right;
		}

		public void setRight(Node<T> right) {
			this.right = right;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [data=").append(data).append(", left=")
					.append(left).append(", right=").append(right).append("]");
			return builder.toString();
		}

	}
	
	protected static enum LeftPosition {
		LEFT, RIGHT;
	}

}
