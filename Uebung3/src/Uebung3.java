
public class Uebung3 {

	public static void main(String[] args) {
		int n = 6000;
		int m = n;

		Matrix A = new Matrix(Matrix.randomMatrix(n, m));
		Matrix B = new Matrix(Matrix.unitMatrix(n, m));
		Matrix C = new Matrix(Matrix.zeroMatrix(n, m));

		//track time
		double start = 0, stop = 0;
		
		//stream
/*		StreamMatrix Cs = new StreamMatrix(Matrix.zeroMatrix(n,m));
		start = System.currentTimeMillis();
		Cs.multiply(Cs,A,B);
		stop = System.currentTimeMillis();

		System.out.println("Stream transporniert in " + (stop - start));
*/
		// Stream parallel
		StreamMatrixParallel Csp = new StreamMatrixParallel(Matrix.zeroMatrix(n,m));
		start = System.currentTimeMillis();
		Csp.multiply(Csp,A,B);
		stop = System.currentTimeMillis();
		System.out.println("Stream parallel transporniert in " + (stop - start));

		//sequentiell
/*		 start = System.currentTimeMillis();
		C.multiply(C, A, B);
		stop = System.currentTimeMillis();
		
		System.out.println("Sequentiell in " + (stop - start));		
*/

		//parallel
		
/*		ParallelMatrix Cp = new ParallelMatrix(Matrix.zeroMatrix(n, m));
		
		start = System.currentTimeMillis();
		Cp.multiply(Cp, A, B);
		stop = System.currentTimeMillis();
		
		System.out.println("Parallel in " + (stop - start));
	*/	
		
		//sequentiell transporniert
		
	/*	MatrixTrans Ct = new MatrixTrans(Matrix.zeroMatrix(n, m));
		
		start = System.currentTimeMillis();
		Ct.multiply(Ct, A, B);
		stop = System.currentTimeMillis();
		
		System.out.println("Sequentiell transporniert in " + (stop - start));
		*/
		
		//parallel transporniert

		ParallelMatrixTrans Ctp = new ParallelMatrixTrans(Matrix.zeroMatrix(n, m));
		
		start = System.currentTimeMillis();
		Ctp.multiply(Ctp, A, B);
		stop = System.currentTimeMillis();
		
		System.out.println("Parallel transporniert in " + (stop - start));
				
	}

}
