package main;

import java.util.ArrayList;

import kdtree.MainKDTRee;
import node.Node;

public class MainPrueba {

	static public void main(String[] args) {
		MainKDTRee mainKDTree = new MainKDTRee();
		ArrayList<double[]> puntos = new ArrayList<double[]>();
		for (int i = 0; i < 10; i++) {
			double[] punto = { i, i };
			puntos.add(punto);
		}
		VecinoMasCercano vecinoMasCercano = new VecinoMasCercano();
		Node root = mainKDTree.construirKDTree(puntos, 1);

		for (int i = 0; i < 10; i++) {
			double[] q = { i + 0.9, i + 0.3 };
			double[] puntoMasCercano = vecinoMasCercano.vecinoMasCercano(root,
					q);
			System.out.println("Vecino mas cercano de: [" + q[0] + ", " + q[1]
					+ "] es [" + puntoMasCercano[0] + ", " + puntoMasCercano[1]
					+ "]");
		}

	}

	static public void imprimirArbol(Node node) {

		if (node.getLeft() != null) {
			imprimirArbol(node.getLeft());
		}
		if (node.getRight() != null) {
			imprimirArbol(node.getRight());
		}
		if (node.getLeft() == null && node.getRight() == null) {
			System.out.println("Punto: [" + node.getValue()[0] + ", "
					+ node.getValue()[1] + "]");
		} else {
			System.out.println("Eje: [" + node.getValue()[0] + ", "
					+ node.getValue()[1] + "]");
		}

	}

}
