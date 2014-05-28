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
		case 1: return puntos[start][axis];
		case 2: return (puntos[start][axis]+puntos[start+1][axis])/2;
		case 3: return medianOfThree(puntos, axis,start);
		case 4: return medianOfFour(puntos, axis,start);
		case 5: return medianOfFive(puntos,axis,start);
		default:
			{
				double [][] medians=new double[puntos.length/5+(puntos.length%5==0 ? 0 : 1)][2];
				
				int i=0;
				
				for(int j=start; j<=end; j+=5){

					medians[i++][axis]=medianOfMedians(puntos, axis,j,Math.min(j+4, end));
					
				}
				
				return medianOfMedians(medians, axis,0,medians.length);
			}
		}

	}
	

	private double medianOfThree(double[][] puntos, int axis, int start){
		if(puntos[start][axis] > puntos[start+2][axis])
			swap(puntos,start,start+2);
		
		if(puntos[start][axis] > puntos[start+1][axis])
			swap(puntos,start,start+1);
		
		if(puntos[start+1][axis] > puntos[start+2][axis])
			swap(puntos,start+1,start+2);
		
		return puntos[start+1][axis];
	}
	
	private double medianOfFour(double[][] puntos, int axis, int start){
		
		if(puntos[start][axis]>puntos[start+1][axis])
			swap(puntos,start,start+1);
		
		if(puntos[start+2][axis]>puntos[start+3][axis])
			swap(puntos,start+2,start+3);
		
		if(puntos[start][axis]>puntos[start+2][axis])
			swap(puntos,start,start+2);
		
		if(puntos[start+1][axis]>puntos[start+3][axis])
			swap(puntos,start+1,start+3);
		
		return (puntos[start+1][axis]+puntos[start+2][axis])/2;
		
	}
	
	private double medianOfFive(double[][] puntos, int axis, int start){

		if(puntos[start][axis]>puntos[start+1][axis])
			swap(puntos, start,start+1);
		
		if(puntos[start+2][axis]>puntos[start+3][axis])
			swap(puntos,start+2,start+3);
		
		//dejar en indice 0 el menor de los 4 (no es mediana)
		if(puntos[start][axis] > puntos[start+2][axis])
			swap(puntos,start,start+2);
		
		//dejar en indice 3 el mayor de los 4 (no es mediana)
		if(puntos[start+1][axis] > puntos[start+3][axis])
			swap(puntos,start+1,start+3);
		
		if(puntos[start+2][axis] > puntos[start+4][axis])
			swap(puntos,start+1,start+3);
				
		//dejar en indice 1 el menor de de los segundos 4 (no es mediana)
		if(puntos[start+1][axis] > puntos[start+2][axis])
			swap(puntos,start+1,start+2);

		//como en el indice 2 se dejo un elemento que es menor que otros 2, 
		//y no es ni el primero ni segundo elemento menor, es mediana
		
		return puntos[start+2][axis];
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
