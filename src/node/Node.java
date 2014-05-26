package node;

public class Node {

	private double[] value;
	private Node left, right;
	
	public Node(double[] value, Node left, Node right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public double[] getValue() {
		return value;
	}
	public void setValue(double[] value) {
		this.value = value;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
}
