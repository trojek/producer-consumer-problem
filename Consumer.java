public class Consumer extends Thread {

	Buffer buf;
	int sleepTime, consumerNumber;
	boolean stop;

	public Consumer(Buffer b, int st, boolean s, int cn) {
		buf = b;
		sleepTime = st;
		stop = s;
		consumerNumber = cn;
	}
	
	public void run() {
		for (; !stop;) {
			try {
				if (buf.get() >= 10) {
					int result = buf.get();
					if (result >= 0) {
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