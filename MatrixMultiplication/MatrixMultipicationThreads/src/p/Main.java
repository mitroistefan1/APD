package p;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
       MatrixMultiplicationEvaluator mme = new MatrixMultiplicationEvaluator(1000);
       
		SinglethreadMatrixMultiplication m = new SinglethreadMatrixMultiplication();
		MultithreadingMatrixMultiplication mm = new MultithreadingMatrixMultiplication();
		mme.evaluate(m);
	

			
	}

}
