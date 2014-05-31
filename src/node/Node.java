package node;

public class Node {

	private double[] value;
	private Node left, right, padre;
	private boolean visitado;
	
	public Node(double[] value, Node padre, Node left, Node right) {
		this.value = value;
		this.padre = padre;
		this.right = right;
		this.left = left;
		visitado = false;
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

	public void setPadre(Node padre) {
		this.padre = padre;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
	
	
}
