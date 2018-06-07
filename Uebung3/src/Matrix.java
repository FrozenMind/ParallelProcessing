
public class Matrix {

	int rows, columns;
	double[][] matrix;

	// creates an zero matrix n x m
	public Matrix(int n, int m) {
		this.rows = n;
		this.columns = m;
		this.matrix = zeroMatrix(n, m);
	}

	public Matrix(double[][] mat) {
		this.matrix = mat;
		this.rows = mat.length;
		this.columns = mat[0].length;
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
		double[][] mat = new double[this.columns][this.rows];
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
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

		double Bn = ((Matrix) B).rows;
		double Bm = ((Matrix) B).columns;
		if (this.rows != Bn || this.columns != Bm)
			return false;

		double[][] Bmatrix = ((Matrix) B).matrix;

		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				if (this.matrix[i][j] != Bmatrix[i][j])
					return false;
			}
		}
		return true;
	}

	// log matrix
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				sb.append(this.matrix[i][j] + "\t");
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}
