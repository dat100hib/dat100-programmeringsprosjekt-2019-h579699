package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class GPSDataConverter {

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
    // skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26 
	
	private static int TIME_STARTINDEX = 11; // startindex for tidspunkt i timestr

	public static int toSeconds(String timestr) {
		
		int secs;
		
		// TODO
		// OPPGAVE - START
		int hr =  parseInt(timestr.substring(TIME_STARTINDEX, TIME_STARTINDEX+2));   //Kutter ut den delen av tekststrengen timestr som viser antall timer
		int min = parseInt(timestr.substring(TIME_STARTINDEX+3, TIME_STARTINDEX+5)); //Tilsvarende for min
		int sec = parseInt(timestr.substring(TIME_STARTINDEX+6, TIME_STARTINDEX+8)); //Tilsvarende for sec
		
		secs = hr*60*60 + min*60 + sec;
		return secs;

		// OPPGAVE - SLUTT
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;

		// TODO - START ;
		int time = toSeconds(timeStr);
		double latitude = parseDouble(latitudeStr);
		double longitude = parseDouble(longitudeStr);
		double elevation = parseDouble(elevationStr);
		
		gpspoint = new GPSPoint(time, latitude, longitude, elevation);
		return gpspoint;

		// OPPGAVE - SLUTT ;
	    
	}
	
}
