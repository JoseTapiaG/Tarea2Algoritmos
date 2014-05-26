package node;

public class Node {

	private double[] value;
	private Node left, right, padre;
	private boolean horizontal;

	public Node(double[] value, Node padre, Node left, Node right,
			boolean horizontal) {
		this.value = value;
		this.padre = padre;
		this.right = right;
		this.left = left;
		this.horizontal = horizontal;
	}

	public double[] getValue() {
		return value;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public Node getPadre() {
		return padre;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

}
