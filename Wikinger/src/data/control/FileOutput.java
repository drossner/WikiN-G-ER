package data.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileOutput {

	private Writer writer = null;
	private String fileName;
	private boolean sameFile;
	
	//sameFile = true => append to File, false => new File
	public FileOutput(boolean sameFile, String fileName) {
		setFileName(fileName);
		this.sameFile = sameFile;
	}
	
	public void writeToFile(String[] writeArr){
		try {
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file, sameFile);
			writer = new OutputStreamWriter(fos, "utf-8"); 			
			for(int i = 0; i<writeArr.length; i++){
				writer.write(writeArr[i]);
				writer.write("\n");
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeToFile(StringBuilder builder) {
		try {
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file, sameFile);
			writer = new OutputStreamWriter(fos, "utf-8"); 			
			writer.write(builder.toString());
			writer.write("\n");
			writer.flush();
			
			writer.close();
		} catch (IOException e) {
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
