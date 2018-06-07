import java.util.Arrays;
import java.util.stream.Stream;

public class StreamMatrix extends Matrix {
    private int indexA = 0;
    private int indexB = 0;
    Stream<double[]> bStream ;
    public StreamMatrix(int n, int m) {
        super(n, m);
    }

    public StreamMatrix(double[][] mat) {
        super(mat);
    }

    @Override
    public void multiply(Matrix RES, Matrix A, Matrix B) {
        indexA = 0;
        indexB = 0;
        final Matrix btrans = B.transponseMatrix();
        Stream<double[]> aStream = Arrays.stream(A.matrix);
        aStream.forEach(xrowa -> {
                bStream = Arrays.stream(btrans.matrix);
                bStream.forEach((xrowb) -> {
                multrows(xrowa, xrowb, RES, indexA, indexB);
                indexB++;
            });
                indexA++;
                indexB=0;
        });

    }

    public void multrows(double[] a, double[] b, Matrix result, int indexA, int indexB) {
        double res = 0.0;

        for (int i = 0; i < a.length; i++) {
            res += a[i] * b[i];
        }
        result.matrix[indexA][indexB] = res;
    }
}
