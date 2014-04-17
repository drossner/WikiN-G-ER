package data.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileInputReader {
	
	private BufferedReader reader = null;
	private String fileName;
	
	public FileInputReader(String fileName) {
		setFileName(fileName);
	}
	
	public String[] loadPartFile(int start, int end){
		ArrayList<String> rc = new ArrayList<String>();
		String line = new String("");
		
		try {
			reader = new BufferedReader(new FileReader(fileName));
			
			line = reader.readLine();
			
			while(line != null){
				rc.add(line);
				line = reader.readLine();
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File konnte nicht geöffnet werden!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rc.toArray(new String[rc.size()]);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
