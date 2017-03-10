package p;

import java.util.Random;

public class MatrixGenerator {

	private int matrixSize;

	public MatrixGenerator(int matrixSize) {
		this.matrixSize = matrixSize;
	}

	public double[][] generateMatrix() {

		double[][] mat = new double[matrixSize][matrixSize];
		Random rand = new Random();

		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++) {
				mat[i][j] = rand.nextInt(10);
			}
		}
		return mat;
	}
}
