import java.util.Random;

public class Producer extends Thread {

	Buffer buf;
	boolean stop = false;
	int[] tablica = new int[1000];
	
	public Producer(int[] t) {
		tablica = t;
		
	}

	Random r = new Random();
	int sleepTime = r.nextInt(111 - 90) + 90;
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