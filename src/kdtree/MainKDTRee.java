package kdtree;

import java.util.ArrayList;

public class MainKDTRee extends KDTree {

	@Override
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
