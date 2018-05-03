
public class Uebung3 {

	public static void main(String[] args) {
		int n = 1200;
		int m = n;
		
		
		//sequentiell
		Matrix A = new Matrix(Matrix.randomMatrix(n, m));
		Matrix B = new Matrix(Matrix.unitMatrix(n, m));
		Matrix C = new Matrix(Matrix.zeroMatrix(n, m));
		
		double start = System.currentTimeMillis();
		C.multiply(C, A, B);
		double stop = System.currentTimeMillis();
		
		System.out.println("Sequentiell in " + (stop - start));		
		
		
		//parallel
		
		ParallelMatrix Cp = new ParallelMatrix(Matrix.zeroMatrix(n, m));
		
		start = System.currentTimeMillis();
		Cp.multiply(Cp, A, B);
		stop = System.currentTimeMillis();
		
		System.out.println("Parallel in " + (stop - start));
		
		
		//sequentiell transporniert
		
		MatrixTrans Ct = new MatrixTrans(Matrix.zeroMatrix(n, m));
		
		start = System.currentTimeMillis();
		Ct.multiply(Ct, A, B);
		stop = System.currentTimeMillis();
		
		System.out.println("Sequentiell transporniert in " + (stop - start));
		
		
		//parallel transporniert

		ParallelMatrixTrans Ctp = new ParallelMatrixTrans(Matrix.zeroMatrix(n, m));
		
		start = System.currentTimeMillis();
		Ctp.multiply(Ctp, A, B);
		stop = System.currentTimeMillis();
		
		System.out.println("Parallel transporniert in " + (stop - start));
		
		
	}

}
