package main;

import java.util.ArrayList;

import kdtree.MainKDTRee;
import node.Node;

public class VecinoMasCercano {

	static public void main(String[] args) {
		MainKDTRee mainKDTree = new MainKDTRee();
		ArrayList<double[]> puntos = new ArrayList<double[]>();
		for (int i = 0; i < 10; i++) {
			double[] punto = {i, i};
			puntos.add(punto);
		}
		Node root = mainKDTree.construirKDTree(puntos, 0);
		System.out.println("hola");
	}

	public int[] vecinoMasCercano(Node root, int[] q) {

		// recorrer arbol como si se fuese a insertar el punto
		// se identifica H cuya region tiene a q
		// mejor actual p y hoja H
		// distancia actual entre p y q
		return new int[2];
	}

}
