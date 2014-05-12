package test;

public class ParseTestStrings {
	
	public static String Moscow = "|image_map             = Moscow in Russia (special marker).svg|latd                  = 55|latm                  = 45|lats                  = 06|longd                 = 37|longm                 = 37|longs                 = 04|image_coa             = Coat of Arms of Moscow.svg";

	public static String Morogoro = "|coor_type              =|latd=6 |latm=49 |lats= |latNS=S|longd=37 |longm=40 |longs= |longEW=E|coordinates_display=d|postal_code_type       =";
	
	public static String Paris = "|city motto = ''[[Fluctuat nec mergitur]]''<br />(Latin: It is tossed by the waves, but does not sink)|latitude = 48.8567|longitude = 2.3508|time zone = [[Central European Time|CET]] <small>(UTC +1)</small>";
	
	
	public static String Coord1 = "|blah |tut {{coord|44.4|-111.1|type:city_region:US|display=inline,title}}";
	
	public static String Coord2 = "|blah |tut {{coord|43.651234|S|79.383333|W|display=title}} ";
	
	public static String Coord3 = "|blah |tut {{coord|43|29|N|79|23|W|display=title}}";
	
	public static String Coord4 = "|blah |tut|latd=|latm= |lats= |latNS=N |longd=|longm=|longs=|longEW=E {{Coord|52|39|N|2|53|W|region:GB_type:city|display=title}} ";
	
	public static String Coord5 = "|blah |tut {{coord|43|29|4.5|N|79|23|0.5|W|display=title}} ";
	
	public static String Villarluengo = "|elevationmax = 470|lat_deg = 47 | lat_min = 51 | lat_sec=0 | lat_hem = S|lon_deg = 12 | lon_min = 8  | lon_sec=0 | lon_hem = W|postal_code = 83001-83028";
	
	public static String Beijing = "|coor_type              =|latd=39 |latm=54 |lats=50 |latNS=N|longd=116 |longm=23 |longs=30 |longEW=E|postal_code_type       = [[Postal code of China|Postal code]]|postal_code            = '''1000'''00–'''1026'''29|area_code              = [[Telephone numbers in China|10]]";
}
