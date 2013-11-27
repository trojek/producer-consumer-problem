import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		// reading from file and writing data into an ArrayList
		Scanner in = new Scanner(new BufferedInputStream(new FileInputStream(
				args[0])));

		ArrayList<Integer> listNumbers = new ArrayList<Integer>();

		while (in.hasNextInt()) {
			listNumbers.add(in.nextInt());
		}

		// Convert ArrayList into and Array.
		int[] numbers = toIntArray(listNumbers);

		// Create buffer object which keeps numbers from producer
		Buffer buf = new Buffer(Integer.parseInt(args[1]));

		// Create producer which put number into buffer, assign buffer
		Prod p = new Prod(numbers, normalDistribution(100, 10));
		p.buf = buf;
		p.start();

		// Create consumer 1, assign buffer
		Consumer c1 = new Consumer(normalDistribution(150, 200), 1);
		c1.buf = buf;
		c1.start();
		
		// Create consumer 2, assign buffer
		Consumer c2 = new Consumer(normalDistribution(120, 300), 2);
		c2.buf = buf;
		c2.start();

		try {
			// takes 3 minutes 
			Thread.currentThread();
			Thread.sleep(179 * 1000);
			p.stop = true;
			c1.stop = true;
			c2.stop = true;
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}

	// Static method to convert ArrayList into an Array 
	static int[] toIntArray(ArrayList<Integer> integerList) {
		int[] intArray = new int[integerList.size()];
		for (int i = 0; i < integerList.size(); i++) {
			intArray[i] = integerList.get(i);
		}
		return intArray;
	}

	// Static method to generate time in ms based on a normal distribution 
	static int normalDistribution(int m, int sigma) {

		Random r = new Random();
		double g = r.nextGaussian() * sigma + m;
		int prodTime = (int) Math.round(g);
		
		// absolute value
		if (prodTime < 0) prodTime = prodTime * -1;
		
		return prodTime;
	}

}