
public class ParallelMatrixTrans extends ParallelMatrix {

	public ParallelMatrixTrans(int n, int m) {
		super(n, m);
	}

	public ParallelMatrixTrans(double[][] mat) {
		super(mat);
	}
	
	@Override
	public void multiply(Matrix C, Matrix B, Matrix A) {
		B = B.transponseMatrix();
		Thread[] threads = new Thread[A.matrix.length];

		for (int i = 0; i < A.matrix.length; i++) {
			threads[i] = new RowMultTrans(C, A, B, i);
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

class RowMultTrans extends Thread {

	Matrix C, A, B;
	int i;

	public RowMultTrans(Matrix C, Matrix A, Matrix B, int i) {
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
				sum += A.matrix[i][k] * B.matrix[j][k];
			}
			C.matrix[i][j] = sum;
		}
	}

}