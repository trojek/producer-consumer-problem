import java.awt.List;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		// reading from file and wrriting data into an array
		Scanner in = new Scanner(new BufferedInputStream(new FileInputStream(
				args[0])));

		ArrayList<Integer> listNumbers = new ArrayList<Integer>();

		while (in.hasNextInt()) {
			listNumbers.add(in.nextInt());
		}

		int[] numbers = toIntArray(listNumbers);
		
		// Create buffer object which keeps numbers from producer
		Buffer buf = new Buffer(Integer.parseInt(args[1]));

		Producer p = new Producer(numbers);
		p.buf = buf;
		p.start();

		Consumer c1 = new Consumer(buf, 300, false, 1);
		c1.buf = buf;
		c1.start();

		Consumer c2 = new Consumer(buf, 250, false, 2);
		c2.buf = buf;
		c2.start();

		try {
			// trwa tak długo
			Thread.currentThread().sleep(5 * 1000);
			p.stop = true;
			c1.stop = true;
			c2.stop = true;
		} catch (InterruptedException e) {
		}
		System.out.println("koniec");
	}

	static int[] toIntArray(ArrayList<Integer> integerList) {
		int[] intArray = new int[integerList.size()];
		for (int i = 0; i < integerList.size(); i++) {
			intArray[i] = integerList.get(i);
		}
		return intArray;
	}

}