package main;

import java.util.ArrayList;

import kdtree.MainKDTRee;
import node.Node;

public class VecinoMasCercano {
	static public double[] mejorActual;
	static public double distActual;

	static public void main(String[] args) {
		MainKDTRee mainKDTree = new MainKDTRee();
		ArrayList<double[]> puntos = new ArrayList<double[]>();
		for (int i = 0; i < 10; i++) {
			double[] punto = { i, i };
			puntos.add(punto);
		}
		Node root = mainKDTree.construirKDTree(puntos, 0);
		mejorActual = new double[2];
		mejorActual[0] = 0;
		mejorActual[1] = 0;
		distActual = Double.MAX_VALUE;
		System.out.println("hola");
	}

	private double[] vecinoMasCercano(Node root, double[] q) {
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
		if (node.getPadre() == null) {
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
			if (!node.getLeft().isVisitado() && areaIntersecta(node, q, true)) {
				buscarMejorSolucion(node.getLeft(), q);
			} else if (!node.getRight().isVisitado()
					&& areaIntersecta(node, q, false)) {
				buscarMejorSolucion(node.getRight(), q);
			}
		}
		buscarMejorSolucion(node.getPadre(), q);

	}

	private boolean areaIntersecta(Node node, double[] q, boolean izq) {
		return false;
	}
}
