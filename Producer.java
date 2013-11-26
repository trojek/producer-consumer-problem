import java.util.Random;

public class Producer extends Thread {

	Buffer buf;
	boolean stop = false;
	int[] tablica;
	int sleepTime =0;
	
	public Producer(int[] t, int st) {
		tablica = t;
		sleepTime = st;
	}

	Random r = new Random();
	public void run() {
		for (int i = 0; i < tablica.length; i++) {
			try {
				boolean result = buf.put(tablica[i]);
				if (result) {
					System.out.println("Producer send:" + tablica[i]);
				}
				sleep(sleepTime);
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
	}
}