package kdtree;

public class MainKDTRee extends KDTree {

	@Override
	public double getSplitAxis(double[][] puntos, int splitAxis) {

		double min, max;
		double newAxis;

		if (splitAxis == y) {
			// eje y
			min = puntos[0][1];
			max = puntos[0][1];
			for (double[] punto : puntos) {
				if (punto[1] > max) {
					max = punto[1];
				}
				if (punto[1] < min) {
					min = punto[1];
				}
			}
			newAxis = (max + min)/2.0;
		} else {
			// eje x
			min = puntos[0][0];
			max = puntos[0][0];
			for (double[] punto : puntos) {
				if (punto[0] > max) {
					max = punto[0];
				}
				if (punto[0] < min) {
					min = punto[0];
				}
			}
			newAxis = (max + min)/2.0;
		}
		return newAxis;
	}

}
