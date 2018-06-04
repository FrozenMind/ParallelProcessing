import java.util.concurrent.*;

//usage of BlockingQueue with multiple threads
//producers create new values to put into blockingqueue
//consumer take values from queue and summand them
public class Uebung5 {

	public Uebung5() {

	}

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue blockingQueue = new LinkedBlockingQueue();
		//with ArrayBlockingQueue producer get paused sometimes and need to wait till consumer take items to make space
		//BlockingQueue blockingQueue = new ArrayBlockingQueue(100);
		ExecutorService pool = Executors.newCachedThreadPool();
		int count = 20; // max thread pool count
		int i = 0; // for loop parameter
		int max = 1000; // number of items to put into queue
		Future[] future = new Future[count];

		// 1/3 of threads are producer
		for (i = 0; i < count / 3; i++) {
			future[i] = pool.submit(new Producer(i, max, blockingQueue));
		}
		// 2/3 of threads are consumer
		for (; i < count; i++) {
			future[i] = pool.submit(new Consumer(i, max, blockingQueue));
		}
		// make sure of producers are finish
		for (i = 0; i < count / 3; i++) {
			try {
				future[i].get(); // wait for threads to end tasks
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// fill one LastItem for each Thread count, to make sure every thread can read a
		// lastItem and shutdown
		for (int j = i; j < count; j++) {
			blockingQueue.put(new LastItem(0.0));
		}
		// wait for all threads to finish
		for (; i < count; i++) {
			try {
				future[i].get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		pool.shutdown();
		System.out.println("BlockingQueue: " + blockingQueue);
	}

}

// item that gets send between threads
class Item {
	double d;

	public Item(double d) {
		this.d = d;
	}
}

class LastItem extends Item {
	public LastItem(double d) {
		super(d);
	}
}

// callable = task interface
// producer create values
class Producer implements Callable<Long> {

	long res = 0;
	int i, max;
	BlockingQueue blockingQueue;

	public Producer(int i, int max, BlockingQueue bq) {
		this.i = i;
		this.max = max;
		this.blockingQueue = bq;
	}

	@Override
	public Long call() throws Exception {
		System.out.println("Producer " + this.i + " started...");

		for (int j = 0; j < max; j++) {
			Item item = new Item((double) Math.sin(i + j));
			this.blockingQueue.put(item);
		}

		System.out.println("Producer " + this.i + " stopped.");
		return res;
	}

}

// consumer read values and summand them
class Consumer implements Callable<Long> {

	long res = 0;
	int i, max;
	BlockingQueue blockingQueue;

	public Consumer(int i, int max, BlockingQueue bq) {
		this.i = i; // id
		this.max = max;
		this.blockingQueue = bq;
	}

	@Override
	public Long call() throws Exception {
		System.out.println("Consumer " + this.i + " started...");

		double sum = 0.0;
		for (int j = 0;; j++) {
			// if item is last item, dont wait for new items
			Item item = (Item) this.blockingQueue.take();
			if (item instanceof LastItem) {
				System.out.println("Consumer " + this.i + " detected LastItem.");
				break;
			}
			sum += item.d;
		}

		System.out.println("Consumer " + this.i + " stopped.");
		return res;
	}
}
