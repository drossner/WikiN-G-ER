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
	
	//kann sein das eine Zeile ausgelassen wird, da end vll falsch übergeben wird => überprüfen; überprüft mit InputOutputTest
	//wenn end = 0 => komplettes File wird geladen
	
	//Daniel: Arbeite mit long! || in der for-Schleife i gleich auf start initialisieren? || Ich habe mal die ArrayList durch ein Array ersetzt, wir geben ja eines zurück und die Länge
	//wissen wir auch!
	public String[] loadPartFile(int start, int end){
		String[] rc = new String[Math.abs(end-start)];		//Math.abs wenn end = 0 und start = 5000
		String line = new String("");
		
		if(end == 0){
			//funzt nicht wenn wir File über 2,1 milliarden zeilen haben
			end = Integer.MAX_VALUE;
		}
		
		try {
			
			//Florian: muss nur 1 Objekt erzeugt werden
			if(reader == null){
				reader = new BufferedReader(new FileReader(fileName));
			}
			
			line = reader.readLine();
						
			for(long i = 0; i<=end && line != null; i++){
				if(i>=start){
					//rc.add(line);					//wenn du meines machst muss hier sowas hin wie: rc[i-start] = line;
					rc[(int) (i-start)] = line;		//vll doch kein long bei i 
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
		
		return rc;
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
