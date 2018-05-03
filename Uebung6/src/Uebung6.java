import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

//calculate pi using Monte Carlo Calculation
//with single stream and parallel streams
//spoiler: with parallel streams the time is 1/2
public class Uebung6 {

	public static void main(String[] args) {		
		
		long count = 2_000_000_000; //count of random points
		//create DoubleStream with *count* random numbers
		
		//sequentiell
		
		long start = System.currentTimeMillis();
		
		DoubleStream doubles = ThreadLocalRandom.current().doubles(count);
		//filter all values in circle and count them
		long inCircle = doubles.filter(x -> {
			double y = ThreadLocalRandom.current().nextDouble(); //random value between 0 and 1
			//if x*x + y*y < 1,value is in circle
			return x*x + y*y < 1;
		}).count();
		double pi = (double)inCircle / (double) count * 4.0;
		
		System.out.println("sequentiell calculated PI = " + pi + " in " + (System.currentTimeMillis() - start) + "ms");
		
		
		//parallel
		
		start = System.currentTimeMillis();
		
		doubles = ThreadLocalRandom.current().doubles(count).parallel(); //create parallel stream
		//filter all values in circle and count them
		inCircle = doubles.filter(x -> {
			double y = ThreadLocalRandom.current().nextDouble(); //random value between 0 and 1
			//if x*x + y*y < 1,value is in circle
			return x*x + y*y < 1;
		}).count();
		pi = (double)inCircle / (double) count * 4.0;
		
		System.out.println("Parallel calculated PI = " + pi + " in " + (System.currentTimeMillis() - start) + "ms");
	}
}
