package kdtree;

import node.Node;

public abstract class KDTree {
	
	static public int x=0;
	static public int y=1;

	public Node construirKDTree(double[][] puntos, int splitAxis) {

		if (puntos.length == 1) {
			return new Node(puntos[0], null, null);
		}
		// generar eje nuevo
		double newSplitAxis = getSplitAxis(puntos, splitAxis);
		// separar los puntos en dos grupos
		double[][] puntosA = new double[puntos.length][2];
		double[][] puntosB = new double[puntos.length][2];
		int countA = 0;
		int countB = 0;
		for (double[] punto : puntos) {
			
			if (punto[splitAxis] > newSplitAxis) {
				puntosA[countA] = punto;
				countA++;
			} else {
				puntosB[countB] = punto;
				countB++;
			}
		}
		if (splitAxis == x){
			return new Node(new double[]{newSplitAxis,0}, construirKDTree(puntosA, y),
					construirKDTree(puntosB, y));
		}
		return new Node(new double[]{0, newSplitAxis}, construirKDTree(puntosA, x),
				construirKDTree(puntosB, x));
	}

	public abstract double getSplitAxis(double[][] puntos, int splitAxis);

}
