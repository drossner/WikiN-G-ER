package data.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileInput {
	
	private BufferedReader reader = null;
	private String fileName;
	
	public FileInput(String fileName) {
		setFileName(fileName);
	}
	
	//kann sein das eine Zeile ausgelassen wird, da end vll falsch übergeben wird => überprüfen
	//wenn end = 0 => komplettes File wird geladen
	public String[] loadPartFile(int start, int end){
		ArrayList<String> rc = new ArrayList<String>();
		String line = new String("");
		
		if(end == 0){
			//funzt nicht wenn wir File über 2,1 milliarden zeilen haben
			end = Integer.MAX_VALUE;
		}
		
		try {
			reader = new BufferedReader(new FileReader(fileName));
			
			line = reader.readLine();
						
			for(int i = 0; i<end && line != null; i++){
				if(i>start){
					rc.add(line);
				}
				
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File konnte nicht geöffnet werden!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Read Line hats verkackt !");
			e.printStackTrace();
		}
		
		return rc.toArray(new String[rc.size()]);
	}
	
	//start = 0, end = 0 => komplettes File wird geladen
	public String[] loadCompleteFile(){
		return loadPartFile(0, 0);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
