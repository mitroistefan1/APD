package p;

public class MatrixMultiplicationEvaluator {

	private MatrixGenerator matrixGenerator;
	private double[][] matrix1;
	private double[][] matrix2;

	public MatrixMultiplicationEvaluator(int size) {
		matrixGenerator = new MatrixGenerator(size);
		matrix1 = matrixGenerator.generateMatrix();
		matrix2 = matrixGenerator.generateMatrix();

	}

	public void evaluate(MatrixMultiplication m) {

		m.multiplication(matrix1, matrix2);

	}
}
