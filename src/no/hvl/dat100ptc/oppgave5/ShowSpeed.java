package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN +2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	public void showSpeedProfile(int ybase, int N) {
		
		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));
				
		// TODO - START
		
		int xPos = MARGIN;
		int bredde = 2;
		
		double [] hastigheter = gpscomputer.speeds();
		
		for (int i = 0; i < hastigheter.length; i++) {
			if(i%2 == 0) {
				setColor(0, 0, 255);               //Bytter farge på annenhver søyle
			} else {
				setColor(0, 150, 255);
			}
			
			int hoeyde = (int)(hastigheter[i]+0.5);
			if (hoeyde < 0) {
				hoeyde = 0;
			}
			
			fillRectangle(xPos, ybase-hoeyde*timescaling, bredde, hoeyde*timescaling);
			xPos+= bredde;
		}
		
		int gjHastighet = (int)(gpscomputer.averageSpeed()+0.5);
		setColor(0, 255, 0);
		fillRectangle(MARGIN, ybase-gjHastighet*timescaling, hastigheter.length*bredde, 1);
	
		// TODO - SLUTT
	}
}
