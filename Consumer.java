import java.util.ArrayList;

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

	public void run() {
		for (; !stop;) {
			try {
				int result = buf.get();
				if (result >= 0) {
					if ((result <= 10) && consumerNumber == 1) {
						System.out.println("Consumer " + consumerNumber + " get:" + result);
						c1.add(result);
					} else if ((result >= 10) && consumerNumber == 2) {
						System.out.println("Consumer " + consumerNumber	+ " get:" + result);
						c2.add(result);
					}
					sleep(sleepTime);
				}
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
		
		if (consumerNumber == 1) System.out.println("C2: " + sumOfArrayList(c1)/c1.size());
		if (consumerNumber == 2) System.out.println("C2: " + sumOfArrayList(c2)/c2.size());
		
	}

	double sumOfArrayList(ArrayList<Integer> al) {
		double sum = 0;
		for(Integer d : al)
		    sum += d;
		return sum;
	}
}