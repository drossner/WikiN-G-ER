package part.offline.control;

public class LatitudeLongitudeParser {
	
	
	/**
	 * Gibt Latitude und Longitude in dezimaler und auf N/E normalisierter Form zurück.
	 * @param Wikitext
	 * @return index=0 -> latitude / index=1 -> longitude / null -> Fehler, keine Koordinaten vorhanden
	 */
	public double[] parseLatLon(String text){
		double[] latlon = new double[2];
		double[] latDeg = new double[3];
		double[] lonDeg = new double[3];
		
		boolean success;
		
		text = text.toLowerCase();
		text = text.replaceAll("\\s", "");
	
		
		success = parseText(text, "|latitude=", latlon, 0) && parseText(text, "|longitude=", latlon, 1);
		
		
		if(success) {  // die Suche nach der ersten Variante war erfolgreich, negative stehen für süd bzw west
			if(latlon[0] < 0) latlon[0] =  latlon[0] * (-1.0);
			if(latlon[1] < 0) latlon[1] =  latlon[1] * (-1.0); 
			
			return latlon;
		}
		
		success = parseText(text, "|latd=", latDeg, 0) && parseText(text, "|longd=", lonDeg, 0);
		
		if(success){
			
			success = parseText(text, "|latm=", latDeg, 1) | parseText(text, "|longm=", lonDeg, 1);
			
			if(success){
				
				success = parseText(text, "|lats=", latDeg, 2) | parseText(text, "|longs=", lonDeg, 2);
			}
			
		} else {
			return null; // weder die Suche nach |latitude / |longitude noch |latd / longd war erfolgreich
		}
		
		
		//Umrechnung von Grad in Dezimal
		latlon[0] = latDeg[0]+(latDeg[1]*60.0+latDeg[2])/3600.0;
		latlon[1] = lonDeg[0]+(lonDeg[1]*60.0+lonDeg[2])/3600.0;
		
		
		return normalize(latlon, text);
		
		
		
	}
	
	private double[] normalize(double[] latlon, String text) {
		boolean south = isSouth(text);
		boolean west = isWest(text);
			
		//Vorzeichenwechsel da Basis-System immer N/E sein soll
		if(south) latlon[0] = latlon[0]* (-1.0);
		if(west) latlon[1] = latlon[1]* (-1.0);
		
		return latlon;
	}

	//longEW=E/W
	private boolean isWest(String text) {
		int start = text.indexOf("|longew=");
		if(start == -1) return false;
		
		start += 8;
		
		//int end = text.indexOf('|', start);
		
	//	if(end == -1) end = text.indexOf('}', start);
		
		String sub = text.substring(start, start+1);
		
		
		if(sub.equals("w")) return true;
		
		return false;
		
	}

	//latNS=S/N
	private boolean isSouth(String text) {
		int start = text.indexOf("|latns=");
		if(start == -1) return false;
		
		start += 7;
		
		//int end = text.indexOf('|', start);
		
		//if(end == -1) end = text.indexOf('}', start);
		
		String sub = text.substring(start, start+1);
		
		if(sub.equals("s")) return true;
		
		return false;
	}


	private boolean parseText(String text, String criterion, double[] a, int index){
		int start = text.indexOf(criterion);
		if(start == -1) return false;
		
		start += criterion.length();
		try{
			char current;
			current = text.charAt(start);
			StringBuffer buf = new StringBuffer(12);
			
		
			while(Character.isDigit(current) || current == '.'){
				buf.append(current);
				current = text.charAt(++start);
			}
			
			if(buf.length() == 0){
				a[index] = 0.0;
			} else {
				double rc = Double.parseDouble(buf.toString());
				a[index] = rc;
			}
			
		} catch(Exception e){
			return false;
		}
		
		return true;
	}
	

}
