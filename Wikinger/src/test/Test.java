package test;

import part.offline.control.LatitudeLongitudeParser;

public class Test {
	
	public static void main(String[] args) {
		LatitudeLongitudeParser p = new LatitudeLongitudeParser();
		
		double[] erg = p.parseLatLon(ParseTestStrings.Moscow);
	
		for (int i = 0; i < erg.length; i++) {
			System.out.println(erg[i]);
		}
		
		System.out.println("");
		
		double[] erg2 = p.parseLatLon(ParseTestStrings.Beijing);
		
		for (int i = 0; i < erg2.length; i++) {
			System.out.println(erg2[i]);
		}
		
		System.out.println("");
		
		double[] erg3 = p.parseLatLon(ParseTestStrings.Morogoro);
		
		for (int i = 0; i < erg3.length; i++) {
			System.out.println(erg3[i]);
		}
	}

}
