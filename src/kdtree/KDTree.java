package kdtree;

import java.util.ArrayList;

import node.Node;

public abstract class KDTree {

	static public final int x = 0;
	static public final int y = 1;

	public Node construirKDTree(ArrayList<double[]> puntos, int splitAxis) {

		if (puntos.size() == 1) {
			return new Node(puntos.get(0), null, null, null);
		}
		// generar eje nuevo
		double newSplitAxis = getSplitAxis(puntos, splitAxis);
		// separar los puntos en dos grupos
		ArrayList<double[]> puntosA = new ArrayList<double[]>();
		ArrayList<double[]> puntosB = new ArrayList<double[]>();
		for (double[] punto : puntos) {
			if (punto[splitAxis] < newSplitAxis) {
				puntosA.add(punto);
			} else {
				puntosB.add(punto);
			}
		}
		if (splitAxis == x) {
			Node nodoA = construirKDTree(puntosA, y);
			Node nodoB = construirKDTree(puntosB, y);
			Node nodoPadre = new Node(new double[] { newSplitAxis, 0 }, null,
					nodoA, nodoB);
			nodoA.setPadre(nodoPadre);
			nodoB.setPadre(nodoPadre);
			return nodoPadre;
		} else {
			Node nodoA = construirKDTree(puntosA, x);
			Node nodoB = construirKDTree(puntosB, x);
			Node nodoPadre = new Node(new double[] {0 , newSplitAxis }, null,
					nodoA, nodoB);
			nodoA.setPadre(nodoPadre);
			nodoB.setPadre(nodoPadre);
			return nodoPadre;
		}
	}

	public abstract double getSplitAxis(ArrayList<double[]> puntos, int splitAxis);

}
