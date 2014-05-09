package test;

public class StringBufferTest {

	public static void main(String[] args) {
		StringBuffer test = new StringBuffer();
		long start;
		long end;

		start = System.currentTimeMillis();
		for (int i = 0; i < 200000; i++) {
			for (int j = 0; j < 1000; j++) {
				test.append("test");
			}

			test.delete(0, test.length());
		}
		end = System.currentTimeMillis();
		System.out.println("Delete: " + (end - start));
		
		start = System.currentTimeMillis();
		for (int i = 0; i < 200000; i++) {
			for (int j = 0; j < 1000; j++) {
				test.append("test");
			}

			test = new StringBuffer(test.length());
		}
		end = System.currentTimeMillis();
		System.out.println("New: " + (end - start));
	
	}

}
