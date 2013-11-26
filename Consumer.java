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
		
		double ac1=0,ac2=0;
		if (consumerNumber == 1) ac1 = sumOfArrayList(c1)/c1.size();
		if (consumerNumber == 2) System.out.println("wynik: " + (sumOfArrayList(c2)/c2.size()- ac1));
		
	}

	double sumOfArrayList(ArrayList<Integer> al) {
		double sum = 0;
		for(Integer d : al)
		    sum += d;
		return sum;
	}
}