package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {

		// TODO - START
		gpspoints = new GPSPoint[n];
		antall = 0;
		// TODO - SLUTT
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {
		// TODO - START
		
		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			return true;
		} else {
			System.out.println("false");
			return false;
		}

		// TODO - SLUTT
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		// TODO - START
		int tid = GPSDataConverter.toSeconds(time);
		double breddegrad = parseDouble(latitude);
		double lengdegrad = parseDouble(longitude);
		double hoeyde = parseDouble(elevation);
		
		GPSPoint gpspoint = new GPSPoint(tid, breddegrad, lengdegrad, hoeyde);
		return insertGPS(gpspoint);

		// TODO - SLUTT
		
	}

	public void print() {

		System.out.println("====== Konvertert GPS Data - START ======");

		// TODO - START
		
		for (var i = 0; i < antall; i++) {
			System.out.print(gpspoints[i].toString());
		}
		// TODO - SLUTT
		
		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
