
public class Uebung2 {

	public static void main(String[] args) {
		int threadCount = 100;
		double sumsum = 0;
		Sum[] ths = new Sum[threadCount];
		for (int j = 0; j < threadCount; j++) {
			ths[j] = new Sum(j);
			ths[j].start();
		}
		for (int j = 0; j < threadCount; j++) {
			try {
				ths[j].join();
				sumsum += ths[j].sum;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Gobal Sum: " + sumsum);
	}

}

class Sum extends Thread {

	int j = 0;
	double sum = 0.0;

	public Sum(int j) {
		System.out.println("Thread " + j + " started");
		this.j = j;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			sum += Math.cos(i + j);
		}
		System.out.println("sum of thread " + this.j + " = " + sum);
	}

}
