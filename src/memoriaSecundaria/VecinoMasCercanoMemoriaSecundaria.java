package memoriaSecundaria;

import java.io.IOException;

public class VecinoMasCercanoMemoriaSecundaria {
	private double[] mejorActual;
	private double distActual;
	private int comparaciones;

	public double[] vecinoMasCercano(MemoryNode root, double[] q) throws IOException {
		comparaciones = 0;
		mejorActual = new double[2];
		MemoryNode nodoDondeEstaQ = encontrarHojaDondeEstaQ(root, q);
		buscarMejorSolucion(nodoDondeEstaQ, q);
		return mejorActual;
	}

	private MemoryNode encontrarHojaDondeEstaQ(MemoryNode node, double[] q) throws IOException {
		comparaciones++;
		node.setVisitado(1);
		if (node.getLeft() == null || node.getRight() == null) {
			double distNueva = Math
					.sqrt((Math.pow((q[0] - node.getX()), 2) + Math.pow(
							(q[1] - node.getY()), 2)));
			distActual = distNueva;
			mejorActual[0] = node.getX();
			mejorActual[1] = node.getY();
			return node;
		} else {
			if ((node.getX() == 0 && q[1] > node.getY())
					|| (node.getY() == 0 && q[0] > node.getX())) {
				return encontrarHojaDondeEstaQ(node.getRight(), q);
			} else {
				return encontrarHojaDondeEstaQ(node.getLeft(), q);
			}
		}
	}

	private void buscarMejorSolucion(MemoryNode node, double[] q) throws IOException {
		comparaciones++;
		if (node == null) {
			return;
		}

		if (node.getPadre() == null && node.getRight().getVisitado() == 1
				&& node.getLeft().getVisitado() == 1) {
			return;
		}
		// es hoja
		if (node.getLeft() == null && node.getRight() == null) {
			double distNueva = Math.sqrt((Math.pow((q[0] - node.getX()),
					2) + Math.pow((q[1] - node.getY()), 2)));
			if (distNueva < distActual) {
				distActual = distNueva;
				mejorActual[0] = node.getX();
				mejorActual[1] = node.getY();
			}
		} else {
			if (node.getLeft().getVisitado() == 0 && areaIntersecta(node, q, true)) {
				node.getLeft().setVisitado(1);
				buscarMejorSolucion(node.getLeft(), q);
			} else if (node.getRight().getVisitado() == 0
					&& areaIntersecta(node, q, false)) {
				node.getRight().setVisitado(1);
				buscarMejorSolucion(node.getRight(), q);
			}
		}
		buscarMejorSolucion(node.getPadre(), q);

	}

	public int getComparaciones() {
		return comparaciones;
	}

	private boolean areaIntersecta(MemoryNode node, double[] q, boolean izq) {
		double distNueva = 0;
		if (node.getX() == 0) {
			distNueva = node.getY() - q[1];
		} else {
			distNueva = node.getX() - q[0];
		}
		if (distNueva < 0) {
			distNueva = distNueva * -1;
		}
		return distNueva < distActual;
	}
}
