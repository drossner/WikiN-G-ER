package data.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileInput {
	
	private BufferedReader reader = null;
	private File file;
	
	/**
	 * 
	 * @param fileName Name des Files welches gelesen werden soll.
	 * @throws Exception Exception wenn Datei nicht vorhanden, Datei ein Verzeichnis, Datei nicht readable.
	 */
	public FileInput(String fileName) throws Exception {
		if(!setFile(fileName)){
			System.err.println("Datei nicht vorhanden, Datei ein Verzeichnis, Datei nicht readable");
			throw new Exception();
		}
	}
	
	/**
	 * Lädt von einem File x Zeilen. Bei erneutem Aufruf wird bei der aktuellen Zeile fortgesetzt.
	 * Aufrufe von getLines() und loadCompleteFile() setzen den Stream zurück.
	 * @param range
	 * @return String Array der Größe range. Muss nicht komplett gefüllt sein (range>zeilen)
	 */
	public String[] loadPartFile(int range){
		String[] rc = new String[range];		
		String line = new String("");
		
		// System.out.println(rc.length);		
		
		try {
			
			//Florian: muss nur 1 Objekt erzeugt werden
			if(reader == null){
				reader = new BufferedReader(new FileReader(file));
			}
						
			for(int i = 0; i<range && line != null; i++){
				line = reader.readLine();
				rc[i] = line;		 			
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
	
	/**
	 * Liest ein komplettes File zeilenweise ein. Setzt den Stream fürloadPartFile() zurück.
	 * @return line 0 = index 0 usw.
	 */
	public String[] loadCompleteFile(){
		ArrayList<String> rc = new ArrayList<String>();
		String line = new String("");
		try{
			
			reader = new BufferedReader(new FileReader(file));
			
			line = reader.readLine();
			while(line!=null){
				rc.add(line);
				line = reader.readLine();

			}
		} catch (FileNotFoundException e) {
			System.err.println("File konnte nicht geöffnet werden!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeReader();
		}
		
		return rc.toArray(new String[rc.size()]);
	}
	
	/**
	 * Setzt Stream für loadPartFile() zurück.
	 * @return Anzahl der Zeilen des Dokumentes
	 */
	public long getLines(){
		String line;
		long rc = 0;
		try{
			
			reader = new BufferedReader(new FileReader(file));
			
			line = reader.readLine();
			while(line!=null){
				rc++;
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			System.err.println("File konnte nicht geöffnet werden!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeReader();
		}
		
		return rc;
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
			reader = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
