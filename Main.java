public class Main {

	public static void main(String[] args) {
		Buffer buf = new Buffer();
		
		Producer p = new Producer();
		p.buf = buf;
		p.start();

		Consumer c1 = new Consumer(buf, 300, false, 1);
		c1.buf = buf;
		c1.start();
		
		Consumer c2 = new Consumer(buf, 900, false, 2);
		c2.buf = buf;
		c2.start();

		try {
			Thread.currentThread().sleep(10 * 1000);
			p.stop = true;
			c1.stop = true;
			c2.stop = true;
		} catch (InterruptedException e) {
		}
		System.out.println("main finished");
	}
}