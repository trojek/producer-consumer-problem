import java.util.ArrayList;

// Class Consumer which takes integers from buffer and count arithmetic average of the integers per every consumer
public class Consumer extends Thread {

	Buffer buf;
	int sleepTime, consumerNumber;
	boolean stop = false;
	
	ArrayList<Integer> c1 = new ArrayList<Integer>();
	ArrayList<Integer> c2 = new ArrayList<Integer>();

	public Consumer(int st, int cn) {
		sleepTime = st;
		consumerNumber = cn;
	}
	// Take element one by one and add it into ArrayList
	public void run() {
		for (; !stop;) {
			try {
				int result = buf.get();
				if (result >= 0) {
					if ((result <= 10) && consumerNumber == 1) {
						c1.add(result);
					} else if ((result >= 10) && consumerNumber == 2) {
						c2.add(result);
					}
					sleep(sleepTime);
				}
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
		
		// Count arithmetic avenger of integers assigned to consumers and subtracting, print the result
		double ac1=0;
		if (consumerNumber == 1) ac1 = sumOfArrayList(c1)/c1.size();
		if (consumerNumber == 2) System.out.printf("%.3f", (sumOfArrayList(c2)/c2.size()- ac1));
		
	}

	// Summation of array elements
	double sumOfArrayList(ArrayList<Integer> al) {
		double sum = 0;
		for(Integer d : al)
		    sum += d;
		return sum;
	}
}