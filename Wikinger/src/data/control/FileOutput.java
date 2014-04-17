package data.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutput {

	private BufferedWriter writer = null;
	private String fileName;
	private boolean sameFile;
	
	//sameFile = true => append to File, false => new File
	public FileOutput(boolean sameFile, String fileName) {
		setFileName(fileName);
		this.sameFile = sameFile;
	}
	
	public void writeToFile(String[] writeArr){
		try {
			writer = new BufferedWriter(new FileWriter(new File(fileName), sameFile));
			
			for(int i = 0; i<writeArr.length; i++){
				writer.write(writeArr[i]);
				writer.newLine();
			}
			
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
