package memoriaSecundaria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainPrueba {

	static public void main(String[] args) throws IOException {
		ExternalMemoryKDTree externalKDTree = new ExternalMemoryKDTree();
		ArrayList<double[]> puntos = new ArrayList<double[]>();
		ArrayList<double[]> puntosQ = new ArrayList<double[]>();
		int n = 6;
		int maxRange = 1;
		Random r = new Random();
		for (int i = 0; i < n; i++) {
			double[] punto = new double[2];
			punto[0] = maxRange * r.nextDouble();
			punto[1] = maxRange * r.nextDouble();
			puntos.add(punto);
		}
		for (int i = 0; i < 10; i++) {
			double[] punto = new double[2];
			punto[0] = maxRange * r.nextDouble();
			punto[1] = maxRange * r.nextDouble();
			puntosQ.add(punto);
		}
		VecinoMasCercanoMemoriaSecundaria vecinoMasCercano = new VecinoMasCercanoMemoriaSecundaria();
		MemoryNode.setMemoryManager(new MemoryManager(10, "C:/Users/Jose/workspace/Analisis de Algoritmos/Tarea2GIT/Tarea2Algoritmos/src/memoriaSecundaria/ws"));
		MemoryNode root = externalKDTree.construirKDTree(puntos, 1);
		root.getLeft();
		for (double[] q : puntos) {
			double[] puntoMasCercano = vecinoMasCercano.vecinoMasCercano(root,
					q);
			double[] puntoMasCercanoReal = puntoMasCercano(puntos, q);
			 System.out.println("Vecino mas cercano prueba: [" +
			 puntoMasCercano[0] + ", " + puntoMasCercano[1] +
			 "] Vecino mas cercano real: [" + puntoMasCercanoReal[0] + ", " +
			 puntoMasCercanoReal[1]);
		}
		
		imprimir(root);
		System.out.println("");
		imprimir(root);
		
		/*if (puntoMasCercano[0] != puntoMasCercanoReal[0]
		|| puntoMasCercano[1] != puntoMasCercanoReal[1]) {
	System.out.println("error");
}
System.out.println("Comparaciones arbol: "
		+ vecinoMasCercano.getComparaciones()
		+ "Comparaciones Original: " + n);*/
	}

	static public void imprimir(MemoryNode memoryNode) throws IOException{
			
		if(memoryNode == null) return;
		System.out.println(memoryNode.getX() + " " + memoryNode.getY());
		if(memoryNode.getLeft() != null){
			imprimir(memoryNode.getLeft());
		}
		if(memoryNode.getRight() != null){
			imprimir(memoryNode.getRight());
		}
		
	}
	
	static public double[] puntoMasCercano(ArrayList<double[]> puntos,
			double[] q) {
		double[] puntoMasCerca = new double[2];
		double distMejor = Double.MAX_VALUE;
		for (double[] punto : puntos) {
			double distNueva = Math.sqrt((Math.pow((q[0] - punto[0]), 2) + Math
					.pow((q[1] - punto[1]), 2)));
			if (distNueva < distMejor) {
				distMejor = distNueva;
				puntoMasCerca = punto;
			}
		}
		return puntoMasCerca;
	}
}
