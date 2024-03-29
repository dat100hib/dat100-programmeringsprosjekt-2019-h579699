package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START
		//Lager en for-l�kke for � gjennnoml�pe tabellen som inneholder gpspunktene
		//Finner avstanden mellom to og to gps-punkt og adderer den til totalavstanden
		for(int i = 1; i < gpspoints.length; i++) {
			distance += GPSUtils.distance(gpspoints[i-1], gpspoints[i]);
		}
		return distance;
		// TODO - SLUTT

	}

	// beregn totale h�ydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START
		//Adderer forskjellen i h�ydemetre mellom to og to gpspunkter til totale h�ydemetre
		//Men kun hvis h�ydemetre �ker mellom to gpspunkter
		for(int i = 1; i < gpspoints.length; i++) {
			if (gpspoints[i-1].getElevation() < gpspoints[i].getElevation()) { 
				elevation += gpspoints[i].getElevation() - gpspoints[i-1].getElevation();
			}
		}
		return elevation;

		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		//Finner tidsavstanden mellom siste og f�rste gpspunkt
		return gpspoints[gpspoints.length-1].getTime() - gpspoints[0].getTime();

	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		//Oppretter en tabell for � ta vare p� hastighetene
		double [] hastigheter = new double [gpspoints.length-1];
		
		//Legger til hastigheten mellom to og to gpspunkter, i tabellen
		for (int i = 0; i < hastigheter.length; i++) {
			hastigheter[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
		}
		
		return hastigheter;

		// TODO - SLUTT

	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO - START
		maxspeed = GPSUtils.findMax(speeds());
		return maxspeed;
		
		// TODO - SLUTT
		
	}

	public double averageSpeed() {

		double average = 0;
		
		// TODO - START
		average = (totalDistance()/totalTime())*3.6; //Ganger med 3.6 for � f� km/t
		return average;
		
		// TODO - SLUTT
		
	}
	
	public double [] climbs() {
		double [] stigningsprosenter = new double [gpspoints.length-1];
		
		for (int i = 0; i < stigningsprosenter.length; i++) {
			stigningsprosenter[i] = (gpspoints[i+1].getElevation() - gpspoints[i].getElevation())/GPSUtils.distance(gpspoints[i], gpspoints[i-1]);
			
		}
		
		return stigningsprosenter;
	}	
	
	public double maxClimbs() {
		double maxStigning = GPSUtils.findMax(climbs()); 
		return maxStigning;
	}	
		
		
	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		// TODO - START
		if (speedmph < 10) {
			met = 4.0;
		} else if (speedmph >= 10 && speedmph < 12) {
			met = 6.0;
		} else if (speedmph >= 12 && speedmph < 14) {
			met = 8.0;
		} else if (speedmph >= 14 && speedmph < 16) {
			met = 10.0;
		} else if (speedmph >= 16 && speedmph <= 20) {
			met = 12.0;
		} else if (speedmph > 20){
			met = 16.0;
		}
		
		kcal = met * weight * secs/3600.0; 
		return kcal;

		// TODO - SLUTT
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		
		// TODO - START	
		
		double [] hastigheter = speeds();
		
		//Finner kcal mellom to og to gpspunkter, og adderer det til totalkcal
		for (int i = 0; i < hastigheter.length; i++) {
			int secs = gpspoints[i+1].getTime() - gpspoints[i].getTime();
			double kalorier = kcal(WEIGHT, secs, hastigheter[i]);
			totalkcal+=kalorier;
		}
		
		
		
		return totalkcal;

		// TODO - SLUTT
		
	}
	
	private static double WEIGHT = 80.0;
	
	public double getWEIGHT() {
		return WEIGHT;
	}
	
	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START
		System.out.println("Total time     :" + GPSUtils.formatTime(totalTime()));
		System.out.println("Total distance :" + GPSUtils.formatDouble(totalDistance()));
		System.out.println("Total elevation:" + GPSUtils.formatDouble(totalElevation()));
		System.out.println("Max speed      :" + GPSUtils.formatDouble(maxSpeed()));
		System.out.println("Average speed  :" + GPSUtils.formatDouble(averageSpeed()));
		System.out.println("Energy         :" + GPSUtils.formatDouble(totalKcal(WEIGHT)));
		
		System.out.println("==============================================");
		// TODO - SLUTT
		
	}

}
