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
		
		System.out.println("");
		
		double[] erg4 = p.parseLatLon(ParseTestStrings.Paris);
		
		for (int i = 0; i < erg4.length; i++) {
			System.out.println(erg4[i]);
		}
		
		System.out.println("");
		
		double[] erg5 = p.parseLatLon(ParseTestStrings.Coord1);
		
		for (int i = 0; i < erg5.length; i++) {
			System.out.println(erg5[i]);
		}
		
		System.out.println("");
		
		double[] erg6 = p.parseLatLon(ParseTestStrings.Coord2);
		
		for (int i = 0; i < erg6.length; i++) {
			System.out.println(erg6[i]);
		}
		
		System.out.println("");
		
		double[] erg7 = p.parseLatLon(ParseTestStrings.Coord3);
		
		for (int i = 0; i < erg7.length; i++) {
			System.out.println(erg7[i]);
		}
		
		System.out.println("");
		
		double[] erg8 = p.parseLatLon(ParseTestStrings.Coord4);
		
		for (int i = 0; i < erg8.length; i++) {
			System.out.println(erg8[i]);
		}

		System.out.println("Astudillo :)");
		
		double[] erg9 = p.parseLatLon(ParseTestStrings.Astudillo);
		
		for (int i = 0; i < erg9.length; i++) {
			System.out.println(erg9[i]);
		}
		
	}

}
