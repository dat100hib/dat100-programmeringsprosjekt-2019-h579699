package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

		playRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 
		
		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
		
		// TODO - START
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat)); 
		
		return ystep;

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {
		
		// TODO - START		
		setColor(0, 255, 0);
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		
		for (int i = 1; i < gpspoints.length; i++) {		
			int x = (int)((gpspoints[i-1].getLongitude()-minlon)*xstep()+0.5)+MARGIN;
			int y = (int)((gpspoints[i-1].getLatitude()-minlat)*ystep()+0.5)-MARGIN*3;
			
			int x2 = (int)((gpspoints[i].getLongitude()-minlon)*xstep()+0.5)+MARGIN;
			int y2 = (int)((gpspoints[i].getLatitude()-minlat)*ystep()+0.5)-MARGIN*3; //Ganget med 3 pga. VM løypen
			
			drawLine(x, (ybase-y)/2, x2, (ybase - y2)/2);
			fillCircle(x, (ybase - y)/2, 2);      //Delt på 2 for å skalere
			
			if (i == gpspoints.length-1) {         //Sluttpunkt
				setColor(255, 0, 0);
				fillCircle(x2, (ybase - y2)/2, 5);
			} else if (i == 1) {                  //Startpunkt
				setColor(0, 0, 255);
				fillCircle(x, (ybase - y)/2, 5);
				setColor(0, 255, 0);
			}
		}
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		int xPos = 10;
		int yPos = 12;
		
		drawString ("Total time     :" + GPSUtils.formatTime(gpscomputer.totalTime()), xPos, yPos); 
		drawString ("Total distance :" + GPSUtils.formatDouble(gpscomputer.totalDistance()) + " km", xPos, yPos*2); 
		drawString ("Total elevation:" + GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m", xPos, yPos*3); 
		drawString ("Max speed      :" + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t", xPos, yPos*4); 
		drawString ("Average speed  :" + GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t", xPos, yPos*5); 
		drawString ("Energy         :" + GPSUtils.formatDouble(gpscomputer.totalKcal(gpscomputer.getWEIGHT())) + " kcal", xPos, yPos*6); 
		// TODO - SLUTT;
	}

	public void playRoute(int ybase) {

		// TODO - START
		setColor(0, 0, 255);
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		
		int startX = (int)((gpspoints[0].getLongitude()-minlon)*xstep()+0.5)+MARGIN;
		int startY = (int)((gpspoints[0].getLatitude()-minlat)*ystep()+0.5)-MARGIN*3;
		
		int id = fillCircle(startX, (ybase-startY)/2, 3);
				
		for (int i = 1; i < gpspoints.length; i++) {		
			int x = (int)((gpspoints[i].getLongitude()-minlon)*xstep()+0.5)+MARGIN;
			int y = (int)((gpspoints[i].getLatitude()-minlat)*ystep()+0.5)-MARGIN*3;
			
			moveCircle(id, x, (ybase-y)/2);
			pause(50);
		}
		
		
		
		// TODO - SLUTT
	}

}
