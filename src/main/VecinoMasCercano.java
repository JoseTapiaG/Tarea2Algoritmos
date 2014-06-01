package main;

import node.Node;

public class VecinoMasCercano {
	private double[] mejorActual;
	private double distActual;

	public double[] vecinoMasCercano(Node root, double[] q) {
		Node nodoDondeEstaQ = encontrarHojaDondeEstaQ(root, q);
		buscarMejorSolucion(nodoDondeEstaQ, q);
		return mejorActual;
	}

	private Node encontrarHojaDondeEstaQ(Node node, double[] q) {
		node.setVisitado(true);
		if (node.getLeft() == null || node.getRight() == null) {
			double distNueva = Math.sqrt((Math.pow((q[0] - node.getValue()[0]),
					2) + Math.pow((q[1] - node.getValue()[1]), 2)));
			distActual = distNueva;
			mejorActual = node.getValue();
			return node;
		} else {
			if ((node.getValue()[0] == 0 && q[1] > node.getValue()[1])
					|| (node.getValue()[1] == 0 && q[0] > node.getValue()[0])) {
				return encontrarHojaDondeEstaQ(node.getRight(), q);
			} else {
				return encontrarHojaDondeEstaQ(node.getLeft(), q);
			}
		}
	}

	private void buscarMejorSolucion(Node node, double[] q) {
		
		if(node == null){
			return;
		}
		
		if (node.getPadre() == null && node.getRight().isVisitado() && node.getLeft().isVisitado()) {
			return;
		}
		// es hoja
		if (node.getLeft() == null && node.getRight() == null) {
			double distNueva = Math.sqrt((Math.pow((q[0] - node.getValue()[0]),
					2) + Math.pow((q[1] - node.getValue()[1]), 2)));
			if (distNueva < distActual) {
				distActual = distNueva;
				mejorActual = node.getValue();
			}
		} else {
			if (!node.getLeft().isVisitado() && areaIntersecta(node, q)) {
				node.getLeft().setVisitado(true);
				buscarMejorSolucion(node.getLeft(), q);
			} else if (!node.getRight().isVisitado()
					&& areaIntersecta(node, q)) {
				node.getRight().setVisitado(true);
				buscarMejorSolucion(node.getRight(), q);
			}
		}
		buscarMejorSolucion(node.getPadre(), q);

	}

	private boolean areaIntersecta(Node node, double[] q) {
		double distNueva = Math
				.sqrt((Math.pow((q[0] - node.getValue()[0]), 2) + Math.pow(
						(q[1] - node.getValue()[1]), 2)));
		return distNueva < distActual;
	}
}
