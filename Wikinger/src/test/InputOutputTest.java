package test;

import data.control.FileInput;
import data.control.FileOutput;

public class InputOutputTest {
	
	public static void main(String[] args) {
		FileOutput out = new FileOutput(false, "test.txt");
		String[] temp = { "a", "b", "c", "d", "e", "f", "g",
				"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z" };
		out.writeToFile(temp);
		
		FileInput in;
		in = null;
		
		try {
			in = new FileInput("test.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Zeilen insgesamt: "+in.getLines());
		temp = in.loadPartFile(13);
		for(int i = 0; i<temp.length;i++){
			System.out.print(temp[i]);
		}
		System.out.println("\n__________________________________ : " + temp.length);
		
		temp = in.loadPartFile(13);
		for(int i = 0; i<temp.length;i++){
			System.out.print(temp[i]);
		}
		System.out.println("\n__________________________________ : " + temp.length);
		
		temp = in.loadCompleteFile();
		for(int i = 0; i<temp.length;i++){
			System.out.print(temp[i]);
		}
		System.out.println("\n__________________________________ : " + temp.length);
		
		out = new FileOutput(true, "test.txt");
		out.writeToFile(temp);
		}

}
