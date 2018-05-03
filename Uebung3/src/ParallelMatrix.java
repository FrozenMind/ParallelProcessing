
public class ParallelMatrix extends Matrix {

	public ParallelMatrix(int n, int m) {
		super(n, m);

	}

	public ParallelMatrix(double[][] mat) {
		super(mat);

	}

	@Override
	public void multiply(Matrix C, Matrix B, Matrix A) {
		Thread[] threads = new Thread[A.matrix.length];

		for (int i = 0; i < A.matrix.length; i++) {
			threads[i] = new RowMult(C, A, B, i);
			threads[i].start();
		}
		for (int i = 0; i < A.matrix.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class RowMult extends Thread {

	Matrix C, A, B;
	int i;

	public RowMult(Matrix C, Matrix A, Matrix B, int i) {
		this.C = C;
		this.A = A;
		this.B = B;
		this.i = i;
	}

	@Override
	public void run() {
		// column of B
		for (int j = 0; j < B.matrix[0].length; j++) {
			double sum = 0.0;
			// rows of B
			for (int k = 0; k < B.matrix.length; k++) {
				sum += A.matrix[i][k] * B.matrix[k][j];
			}
			C.matrix[i][j] = sum;
		}
	}

}