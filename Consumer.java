public class Consumer extends Thread {

	Buffer buf;
	int sleepTime, consumerNumber;
	boolean stop = false;

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
						System.out.println("Consumer " + consumerNumber
								+ " get:" + result);
					} else if ((result >= 10) && consumerNumber == 2) {
						System.out.println("Consumer " + consumerNumber
								+ " get:" + result);
					}
					sleep(sleepTime);
				}
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
	}
}