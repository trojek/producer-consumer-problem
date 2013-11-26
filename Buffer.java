public class Buffer {

	int BUF_SIZE = 10;
	
	public Buffer(int size) {
		BUF_SIZE = size;
	}
	
	int[] buf = new int[BUF_SIZE];
	int count = 0;

	boolean put(int element)
	{
		if (count == BUF_SIZE)
			return false;
		buf[count] = element;
		count++;
		return true;
	}

	int get()
	{
		if (count == 0) {
			return -1;
		}
		
		int element = buf[0];

		for (int i = 0; i < count - 1; i++)
			buf[i] = buf[i + 1];
		count--;

		return element;
	}
}