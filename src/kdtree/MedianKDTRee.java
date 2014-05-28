package kdtree;

public class MedianKDTRee extends KDTree{

	@Override
	public double getSplitAxis(double[][] puntos, int splitAxis) {
		
		return medianOfMedians(puntos,splitAxis,0,puntos.length-1);
	}
	/**
	 * Aplica algoritmo de "mediana de medianas". Retorna la mediana
	 * @param puntos
	 * @param axis eje
	 * @return mediana
	 */
	private double medianOfMedians(double[][] puntos, int axis,int start,int end){
		switch (end-start){
		case 1: return puntos[0][axis];
		case 2: return (puntos[0][axis]+puntos[1][axis])/2;
		case 3: return medianOfThree(puntos, axis,start);
		case 4: return medianOfFour(puntos, axis,start);
		case 5: return medianOfFive(puntos,axis,start);
		default:
			{
				double [][] medians=new double[puntos.length/5+(puntos.length%5==0 ? 0 : 1)][2];
				
				int i=0;
				
				for(int j=start; j<=end; j+=5){

					medians[i++][axis]=medianOfMedians(puntos, axis,j,j+4);
					
				}
				
				return medianOfMedians(medians, axis,0,medians.length);
			}
		}

	}
	

	private double medianOfThree(double[][] puntos, int axis, int start){
		if(puntos[0][axis] > puntos[2][axis])
			swap(puntos,0,2);
		
		if(puntos[0][axis] > puntos[1][axis])
			swap(puntos,0,1);
		
		if(puntos[1][axis] > puntos[2][axis])
			swap(puntos,1,2);
		
		return puntos[1][axis];
	}
	
	private double medianOfFour(double[][] puntos, int axis, int start){
		
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
	
	private double medianOfFive(double[][] puntos, int axis, int start){

		if(puntos[0][axis]>puntos[1][axis])
			swap(puntos, 0,1);
		
		if(puntos[2][axis]>puntos[3][axis])
			swap(puntos,2,3);
		
		//dejar en indice 0 el menor de los 4 (no es mediana)
		if(puntos[0][axis] > puntos[2][axis])
			swap(puntos,0,2);
		
		//dejar en indice 3 el mayor de los 4 (no es mediana)
		if(puntos[1][axis] > puntos[3][axis])
			swap(puntos,1,3);
		
		if(puntos[2][axis] > puntos[4][axis])
			swap(puntos,1,3);
				
		//dejar en indice 1 el menor de de los segundos 4 (no es mediana)
		if(puntos[1][axis] > puntos[2][axis])
			swap(puntos,1,2);

		//como en el indice 2 se dejo un elemento que es menor que otros 2, 
		//y no es ni el primero ni segundo elemento menor, es mediana
		
		return puntos[2][axis];
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