
public class Matrix {

	int n, m;
	double[][] matrix;

	// n = rows, m = columns
	public Matrix(int n, int m) {
		this.n = n;
		this.m = m;
		this.matrix = zeroMatrix(n, m);
	}

	public Matrix(double[][] mat) {
		this.matrix = mat;
		this.n = mat.length;
		this.m = mat[0].length;
	}

	// creates a zero matrix
	public static double[][] zeroMatrix(int n, int m) {
		double[][] mat = new double[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				mat[i][j] = 0.0;
			}
		}
		return mat;
	}

	// creates a unit matrix
	public static double[][] unitMatrix(int n, int m) {
		double[][] mat = new double[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == j)
					mat[i][j] = 1.0;
				else
					mat[i][j] = 0.0;
			}
		}
		return mat;
	}

	// creates a random matrix
	public static double[][] randomMatrix(int n, int m) {
		double[][] mat = new double[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				mat[i][j] = Math.random();
			}
		}
		return mat;
	}
	
	//transponse matrix
	public Matrix transponseMatrix(){
		double[][] mat = new double[this.m][this.n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				mat[j][i] = this.matrix[i][j];
			}
		}
		return new Matrix(mat);
	}

	// RES = A*B
	public void multiply(Matrix RES, Matrix A, Matrix B) {
		// rows of A
		for (int i = 0; i < A.matrix.length; i++) {
			// column of B
			for (int j = 0; j < B.matrix[0].length; j++) {
				double sum = 0.0;
				// rows of B
				for (int k = 0; k < B.matrix.length; k++) {
					sum += A.matrix[i][k] * B.matrix[k][j];
				}
				RES.matrix[i][j] = sum;
			}
		}
	}

	// check 2 matrix for equality
	public boolean equals(Object B) {
		if (!(B instanceof Matrix)) {
			return false;
		}

		double Bn = ((Matrix) B).n;
		double Bm = ((Matrix) B).m;
		if (this.n != Bn || this.m != Bm)
			return false;

		double[][] Bmatrix = ((Matrix) B).matrix;

		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.m; j++) {
				if (this.matrix[i][j] != Bmatrix[i][j])
					return false;
			}
		}
		return true;
	}

	// log matrix
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.m; j++) {
				sb.append(this.matrix[i][j] + "\t");
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}
