package com.yanci.tree;

/**
 * AVL
 */
public class BalancedBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	@Override
	public boolean addNode(T data) {
		root = addSubNode((Node<T>)root, data);
		return true;
	}

	/**
	 * 1、插入位置当前节点左孩子的左子树，当前节点右旋
	 * 2、插入位置当前节点右孩子的右子树，当前节点左旋
	 * 3、插入位置当前节点右孩子的左子树，当前节点右孩子右旋，当前左旋
	 * 3、插入位置当前节点左孩子的右子树，当前节点左孩子左旋，当前右旋
	 * 
	* @throws
	 */
	private Node<T> addSubNode(Node<T> current, T data) {
		if(current == null) {
			return new Node<T>(data);
		}
		int cr = data.compareTo(current.data);
		if(cr < 0) {
			// 插入左子树
			current.left = addSubNode((Node<T>)current.left, data);
			// 判断高度
			if(height((Node<T>)current.left) - height((Node<T>)current.right) == 2) {
				if(data.compareTo(current.left.data) < 0) {
					// 插入在current的左孩子的左子树下 LL   右旋
					current = rightRotate(current);
				}else {
					// 插入在current的左孩子的右子树下 LR   左孩子左旋 + 右旋
					current = leftRightRotate(current);
				}
			}
		}else if(cr > 0) {
			current.right = addSubNode((Node<T>)current.right, data);
			// 判断高度
			if(height((Node<T>)current.right) - height((Node<T>)current.left) == 2) {
				if(data.compareTo(current.right.data) < 0) {
					// 插入在current的右孩子的左子树下 RL   右孩子右旋 + 左旋
					current = righeLeftRotate(current);
				}else {
					// 插入在current的右孩子的右子树下 RR   左旋
					current = leftRotate(current);
				}
			}
		}
		current.hight = Math.max(height((Node<T>)current.left), height((Node<T>)current.right)) + 1;
		
		return current;
	}
	
	public T remove(T data) {
		root = removeSub((Node<T>)root, data);
		return data;
	}
	
	
	
	private Node<T> removeSub(Node<T> current, T data) {
		if(current == null) {
			return null;
		}
		int cr = data.compareTo(current.data);
		if(cr < 0) {
			current.left = removeSub((Node<T>)current.left, data);
		}else if(cr > 0) {
			current.right = removeSub((Node<T>)current.right, data);
		}else {
			// 相等
			// 没有叶子节点 或者只有一个
			if (current.left == null || current.right == null) {
				current = current.left != null ? (Node<T>)current.left : (Node<T>)current.right;
			}else {
				// 两个叶子节点
				Node<T> min = (Node<T>) getMin(current.right);
				min.right = removeSub((Node<T>)current.right, min.data);
				min.left = current.left;
				current = min;
			}
		}
		if(current != null) {
			current.hight = Math.max(height((Node<T>)current.left), height((Node<T>)current.right)) + 1;
			// 判断高度
			if(height((Node<T>)current.left) - height((Node<T>)current.right) == 2) {
				current = rightRotate(current);
			} else if(height((Node<T>)current.left) - height((Node<T>)current.right) == -2){
				current = leftRotate(current);
			}
		}
		
		return current;
	}

	private Node<T> leftRightRotate(Node<T> node) {
		node.left = leftRotate((Node<T>)node.left);
		return rightRotate(node);
	}
	
	private Node<T> righeLeftRotate(Node<T> node) {
		node.right = rightRotate((Node<T>)node.right);
		return leftRotate(node);
	}
	
	private Node<T> leftRotate(Node<T> node) {
		Node<T> r = (Node<T>) node.right;
		node.right = node.right.left;
		r.left = node;
		node.hight = Math.max(height((Node<T>)node.left), height((Node<T>)node.right)) + 1;
		r.hight = Math.max(height((Node<T>)r.left), height((Node<T>)r.right)) + 1;
		return r;
	}
	
	
	private Node<T> rightRotate(Node<T> node) {
		Node<T> l = (Node<T>) node.left;
		node.left = node.left.right;
		l.right = node;
		node.hight = Math.max(height((Node<T>)node.left), height((Node<T>)node.right)) + 1;
		l.hight = Math.max(height((Node<T>)l.left), height((Node<T>)l.right)) + 1;
		return (Node<T>) l;
	}
	
	private int height(Node<T> node) {
		return node == null ? 0 : node.hight;
	}
	
	protected static class Node<T> extends com.yanci.tree.BinaryTree.Node<T> {

		protected int hight;
		
		protected Node(T data) {
			super(data);
			this.hight = 1;
		}

		protected int getHight() {
			return hight;
		}

		protected void setHight(int hight) {
			this.hight = hight;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [hight=").append(hight).append(", toString()=")
					.append(super.toString()).append("]");
			return builder.toString();
		}
		
	}
	
}
