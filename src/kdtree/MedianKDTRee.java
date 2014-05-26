package kdtree;

public class MedianKDTRee extends KDTree{

	@Override
	public double getSplitAxis(double[][] puntos, int splitAxis) {
		
		return medianOfMedians(puntos,splitAxis);
	}
	/**
	 * Aplica algoritmo de "mediana de medianas". Retorna la mediana
	 * @param puntos
	 * @param axis eje
	 * @return mediana
	 */
	private double medianOfMedians(double[][] puntos, int axis){
		switch (puntos.length){
		case 1: return puntos[0][axis];
		case 2: return (puntos[0][axis]+puntos[1][axis])/2;
		case 3: return medianOfThree(puntos, axis);
		case 4: return medianOfFour(puntos, axis);
		case 5: return medianOfFive(puntos,axis);
		default: return 0;
			//TODO
		}

	}
	
	private double[][][] splitMaxLength(double[][] puntos, int maxLength){
		//TODO
		return new double[1][1][1];
	}
	

	private double medianOfThree(double[][] puntos, int axis){
		if(puntos[0][axis] > puntos[2][axis])
			swap(puntos,0,2);
		
		if(puntos[0][axis] > puntos[1][axis])
			swap(puntos,0,1);
		
		if(puntos[1][axis] > puntos[2][axis])
			swap(puntos,1,2);
		
		return puntos[1][axis];
	}
	
	private double medianOfFour(double[][] puntos, int axis){
		
		if(puntos[0][axis]>puntos[1][axis])
			swap(puntos,0,1);
		
		if(puntos[2][axis]>puntos[3][axis])
			swap(puntos,2,3);
		
		if(puntos[0][axis]>puntos[2][axis])
			swap(puntos,0,2);
		
		if(puntos[1][axis]>puntos[3][axis])
			swap(puntos,1,3);
		
		return (puntos[1][axis]+puntos[2][axis])/2;
		
	}
	
	private double medianOfFive(double[][] puntos, int axis){

		if(puntos[0][axis]>puntos[1][axis])
			swap(puntos, 0,1);
		
		if(puntos[2][axis]>puntos[3][axis])
			swap(puntos,2,3);
		
		//dejar en indice 0 el menor de los 4 (que no es mediana)
		if(puntos[0][axis] > puntos[2][axis])
			swap(puntos,0,2);
		
		//dejar en indice 4 el mayor de los 4
		if(puntos[2][axis] > puntos[4][axis])
			swap(puntos,2,4);
		
		//encontrar mediana de 3
		double[][] others=new double[3][2];
		others[0]=puntos[1];
		others[1]=puntos[2];
		others[2]=puntos[4];
		
		return medianOfThree(others,axis);
		
	}
	
	/**
	 * Intercambia los valores del indice i por el de los valores en indice j, en arreglo puntos
	 * @param puntos
	 * @param i
	 * @param j
	 */
	private void swap(double[][] puntos, int i, int j){
		double[] aux=puntos[i];
		puntos[i]=puntos[j];
		puntos[j]=aux;
	}
}