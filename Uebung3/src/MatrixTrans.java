
public class MatrixTrans extends Matrix {

	public MatrixTrans(int n, int m) {
		super(n, m);
	}

	public MatrixTrans(double[][] mat) {
		super(mat);
	}

	@Override
	public void multiply(Matrix RES, Matrix A, Matrix B) {
		B = B.transponseMatrix();
		// rows of A
		for (int i = 0; i < A.matrix.length; i++) {
			// column of B
			for (int j = 0; j < B.matrix[0].length; j++) {
				double sum = 0.0;
				// rows of B
				for (int k = 0; k < B.matrix.length; k++) {
					sum += A.matrix[i][k] * B.matrix[j][k];
				}
				RES.matrix[i][j] = sum;
			}
		}
	}

}
