package part.offline.control;

public class LatitudeLongitudeParser {
	
	
	
	public double[] parseLatLon(String text){
		double[] latlon = new double[2];
		double[] latDeg = new double[3];
		double[] lonDeg = new double[3];
		
		boolean success;
		
		success = parseText(text, "|latitude", latlon, 0) && parseText(text, "|longitude", latlon, 1);
		
		if(success) return latlon; // die Suche nach der ersten Variante war erfolgreich, hier wichtig: suchen ob die Angaben |latitude und |latNS zusammen auftreten
		
		success = parseText(text, "|latd", latDeg, 0) && parseText(text, "|longd", lonDeg, 0);
		
		if(success){
			
			success = parseText(text, "|latm", latDeg, 1) || parseText(text, "|longm", lonDeg, 1);
			
			if(success){
				
				success = parseText(text, "|lats", latDeg, 2) || parseText(text, "|longs", lonDeg, 2);
				
			}
			
		} else {
			return null; // weder die Suche nach |latitude / |longitude noch |latd / longd war erfolgreich
		}
		
		boolean south = isSouth(text);
		boolean west = isWest(text);
		
		//Umrechnung von Grad in Dezimal
		latlon[0] = latDeg[0]+(latDeg[1]*60.0+latDeg[2])/3600.0;
		latlon[1] = lonDeg[0]+(lonDeg[1]*60.0+lonDeg[2])/3600.0;
		
		//Vorzeichenwechsel da Basis-System immer N/E sein soll
		if(south) latlon[0] = latlon[0]* (-1.0);
		if(west) latlon[1] = latlon[1]* (-1.0);
		
		
		
		return latlon;
		
		
		
	}
	
	//longEW=E/W
	private boolean isWest(String text) {
		// TODO Auto-generated method stub
		return false;
	}

	//latNS=S/N
	private boolean isSouth(String text) {
		// TODO Auto-generated method stub
		return false;
	}


	private boolean parseText(String text, String criterion, double[] a, int index){
		int start = -1;
		int end = -1;
		
		
		
	}
	

}
