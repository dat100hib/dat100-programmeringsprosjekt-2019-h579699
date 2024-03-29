package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		// TODO - START

		double min; 
		
		//Setter minimumsverdi = f�rste element i tabellen
		min = da[0];
		
		//G�r gjennom tabellen og sammenligner hvert element med minimumsverdi
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		//Oppretter en ny tabell som inneholder alle breddegradene
		double [] latTab = new double[gpspoints.length];
		for (int i = 0; i < latTab.length; i++) {
			latTab[i] = gpspoints[i].getLatitude();
		}
		
		return latTab;
		
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START
		//Oppretter en ny tabell som inneholder alle lengdegradene
		double [] longTab = new double[gpspoints.length];
		for (int i = 0; i < longTab.length; i++) {
			longTab[i] = gpspoints[i].getLongitude();
		}
		
		return longTab;
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius i meter

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		// TODO - START
		//Bruker Haversine-formelen 
		double lat1 = Math.toRadians(gpspoint1.getLatitude());
		double lat2 = Math.toRadians(gpspoint2.getLatitude());
		double latDif = lat1 - lat2;
		double longDif = Math.toRadians(gpspoint2.getLongitude()) - Math.toRadians(gpspoint1.getLongitude());
		
		double a = Math.pow(Math.sin(latDif/2), 2) + Math.cos(lat1)*Math.cos(lat2)* Math.pow(Math.sin(longDif/2), 2);
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt((1-a)));
		d = R * c;
		return d;

		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
				
		// TODO - START
		//Finner avstanden i sekunder mellom to GPS-punkt
		int secs = gpspoint2.getTime() - gpspoint1.getTime();
		double speed;
		
		//Bruker distance-metoden til � finne distanse
		double distanse = distance(gpspoint1, gpspoint2);
		speed = Math.round(((distanse/secs)*3.6)*100)/100.0; //Gjennomsnittshastighet i km/t
		return speed;

		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		
		int hh = secs/3600;
		int mm = (secs - hh*3600)/60;
		int ss = secs - hh*3600 - mm*60;
		
		//Angir at hh, mm, og ss skal best� av to siffre, dersom de bare inneholder ett, legges det til et 0 foran
		timestr = "  " + (String.format("%02d", hh) + TIMESEP + String.format("%02d", mm) + TIMESEP + String.format("%02d", ss));

		// TODO - START
		return timestr;
		
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START
		//Angir at tekststrengen skal v�re lik TEXTWIDTH, og runder av desimaltallet til to desimaler
		str = String.format("%" + TEXTWIDTH +"s", Math.round(d*100)/100.0);
		return str;

		// TODO - SLUTT
		
	}
}
