
public class Uebung1 {

	static double sumsum;

	public static void main(String[] args) {
		int threadCount = 100;
		Thread[] ths = new Thread[threadCount];
		for (int j = 0; j < threadCount; j++) {
			ths[j] = new Thread(new Sum1(j));
			ths[j].start();
		}
		for (int j = 0; j < threadCount; j++) {
			try {
				ths[j].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Gobal Sum: " + Uebung1.sumsum);
	}

}

class Sum1 implements Runnable {

	int j = 0;

	public Sum1(int j) {
		System.out.println("Thread " + j + " started");
		this.j = j;
	}

	@Override
	public void run() {
		double sum = 0.0;
		for (int i = 0; i < 1000; i++) {

			sum += Math.cos(i + j);
		}
		synchronized (Uebung1.class) {
			Uebung1.sumsum += sum;
		}
		System.out.println("sum of thread " + this.j + " = " + sum);
	}

}
