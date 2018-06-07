import java.util.Arrays;
import java.util.stream.Stream;

public class StreamMatrixParallel extends Matrix {
	private int indexA = 0;
	Thread[] threads;

	public StreamMatrixParallel(int n, int m) {
		super(n, m);
	}

	public StreamMatrixParallel(double[][] mat) {
		super(mat);
	}

	@Override
	public void multiply(Matrix RES, Matrix A, Matrix B) {
		indexA = 0;
		threads = new Thread[A.matrix.length];
		// valentin calc
		Matrix btrans = B.transponseMatrix();
		Arrays.stream(A.matrix).forEach(rowa -> {
			threads[indexA] = new StreamMatrixParallelThread(rowa, btrans, RES, indexA);
			threads[indexA].start();
			indexA++;
		});
		//join all threads
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class StreamMatrixParallelThread extends Thread {

	double[] a;
	Matrix b;
	int indexA;
	Matrix result;

	public StreamMatrixParallelThread(double[] a, Matrix b, Matrix result, int indexA) {
		this.a = a;
		this.b = b;
		this.result = result;
		this.indexA = indexA;
	}

	@Override
	public void run() {
		Arrays.stream(b.matrix).forEach((rowb) -> {
			double res = 0.0;
			for (int column = 0; column < a.length; column++) {
				res += a[column] * b.matrix[indexA][column];
				result.matrix[indexA][column] = res;
			}			
		});
	}
}
