import java.util.Random;

public class Producer extends Thread {

	Buffer buf;
	boolean stop = false;

	Random r = new Random();
	int sleepTime=r.nextInt(111-90) + 90;
	
	public void run() {
		for (int i = 0; !stop;) {
			try {
				boolean result = buf.put(i);
				if (result) {
					System.out.println("Producer send:" + i);
					i++;
				}
				sleep(sleepTime);
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
	}
}