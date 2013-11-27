// Class Producer which takes integer from input and put in into a buffer
public class Producer extends Thread {

	Buffer buf;
	boolean stop = false;
	int[] tablica;
	int sleepTime =0;
	
	public Producer(int[] t, int st) {
		tablica = t;
		sleepTime = st;
	}

	public void run() {
		for (int i = 0; i < tablica.length; i++) {
			try {
				boolean result = buf.put(tablica[i]);
				if (result) {
				}
				sleep(sleepTime);
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
	}
}