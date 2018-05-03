import java.util.concurrent.*;

//Uebung2 with usage of Executors and Tasks
//not faster than normal threads, but sexier
public class Uebung4 {
	@SuppressWarnings("unchecked")
	
	public static void main(String[] args) {		
		int taskCount = 100; // thread counter
		// thread pool with working threads, to do tasks (like one thread for each core)
		ExecutorService pool = Executors.newFixedThreadPool(4);
		// future to manage task		
		Future<Double>[] future = new Future[taskCount];
		for (int i = 0; i < taskCount; i++) {
			future[i] = pool.submit(new Sum5(i));
		}
		// collect results
		double sum = 0.0;
		for (int i = 0; i < taskCount; i++) {
			// get value of task
			double s = 0.0;
			try {
				// get() is blocked until task is done
				s = future[i].get();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sum += s;
		}
		System.out.println("global sum = " + sum);
		// shutdown pool to close threads
		pool.shutdown();
	}

}

// implements callable to use class in thread pools
// Sum5 is a task
class Sum5 implements Callable<Double> {

	int j;
	double sum;

	public Sum5(int i) {
		this.j = i;
	}

	// Thread pool calls this method
	public Double call() {
		System.out.println("Task " + j + " called");
		for (int i = 0; i < 1000; i++) {
			sum += Math.cos(i + j);
		}
		System.out.println("Task " + j + " sum = " + sum);
		return sum;
	}

}
