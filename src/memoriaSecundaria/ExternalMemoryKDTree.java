package memoriaSecundaria;

import java.io.IOException;
import java.util.ArrayList;

public class ExternalMemoryKDTree{

	static public final int x = 0;
	static public final int y = 1;
	
	public MemoryNode construirKDTree(ArrayList<double[]> puntos, int splitAxis) throws IOException{
		if (puntos.size() == 1) {
			return new MemoryNode(puntos.get(0)[0] , puntos.get(0)[1], 'y', -1, -1);
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
			MemoryNode nodoA = construirKDTree(puntosA, y);
			MemoryNode nodoB = construirKDTree(puntosB, y);
			MemoryNode nodoPadre = new MemoryNode(newSplitAxis, 0 , 'n',
					nodoB.getPos(), nodoA.getPos());
			nodoA.setPadrePos(nodoPadre.getPos());
			nodoB.setPadrePos(nodoPadre.getPos());
			return nodoPadre;
		} else {
			MemoryNode nodoA = construirKDTree(puntosA, x);
			MemoryNode nodoB = construirKDTree(puntosB, x);
			MemoryNode nodoPadre = new MemoryNode(0 , newSplitAxis , 'n',
					nodoB.getPos(), nodoA.getPos());
			nodoA.setPadrePos(nodoPadre.getPos());
			nodoB.setPadrePos(nodoPadre.getPos());
			return nodoPadre;
		}
	}

	public double getSplitAxis(ArrayList<double[]> puntos, int splitAxis) {

		double min, max;
		double newAxis;

		min = puntos.get(0)[splitAxis];
		max = puntos.get(0)[splitAxis];
		for (double[] punto : puntos) {
			if (punto[splitAxis] > max) {
				max = punto[splitAxis];
			}
			if (punto[splitAxis] < min) {
				min = punto[splitAxis];
			}
		}
		newAxis = (max + min)/2.0;
		return newAxis;
	}
	
}
