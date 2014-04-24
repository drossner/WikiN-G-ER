package data.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileInput {
	
	private BufferedReader reader = null;
	//private String fileName;
	private File file;
	
	public FileInput(String fileName) throws Exception {
		if(!setFile(fileName)){
			System.err.println("Datei nicht vorhanden, Datei ein Verzeichnis, Datei nicht readable");
			throw new Exception();
		}
	}
	
	//kann sein das eine Zeile ausgelassen wird, da end vll falsch übergeben wird => überprüfen; überprüft mit InputOutputTest
	//wenn end = 0 => komplettes File wird geladen
	
	//Daniel: Arbeite mit long! || in der for-Schleife i gleich auf start initialisieren? || Ich habe mal die ArrayList durch ein Array ersetzt, wir geben ja eines zurück und die Länge
	//wissen wir auch!
	public String[] loadPartFile(long start, long end){
		String[] rc = new String[(int)(end-start)];		//Math.abs wenn end = 0 und start = 5000; vll doch unnötig :D
		String line = new String("");
		
		if(end < 0){
			//funzt nicht wenn wir File über 63^2 Zeilen haben
			end = Long.MAX_VALUE;
		}
		
		try {
			
			//Florian: muss nur 1 Objekt erzeugt werden
			if(reader == null){
				reader = new BufferedReader(new FileReader(file));
			}
			
			line = reader.readLine();
						
			for(long i = 0; i<=end && line != null; i++){
				if(i>=start){
					rc[(int) (i-start)] = line;		 
				}
				
				line = reader.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("File konnte nicht geöffnet werden!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Read Line Fehler!");
			e.printStackTrace();
		}
		
		return rc;
	}
	
	//start = 0, end < 0 => komplettes File wird geladen
	public String[] loadCompleteFile(){
		return loadPartFile(0, -1);
	}
	

	public String getFileName() {
		return file.getName();
	}

	public boolean setFile(String fileName) {
		this.file = new File(fileName);
		if(file.exists() && file.canRead() && file.isFile()){
			return true;
		}
		return false;
	}
	
	protected void finalize() throws Throwable{
		closeReader();
		super.finalize();
	}

	private void closeReader() {
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
