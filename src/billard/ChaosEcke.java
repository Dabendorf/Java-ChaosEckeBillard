package billard;

import java.awt.geom.Point2D;

/**
 * Dies ist die Rechenklasse des Chaoseckeprogramms. Sie nimmt die Groesse des Feldes entgegen und
 * rechnet die Anzahl der Randberuehrungen sowie das Loch, in welchem die Kugel verschwindet aus.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class ChaosEcke {
	
	private double[] size = new double[2];
	private int contacts = 0;
	private Point2D.Double startVect;
	private Point2D.Double dirVect;
	private boolean done = false;
	private Point2D.Double[] corners = new Point2D.Double[4];
	
	/**
	 * Diese Methode nimmt alle Werte entgegen und und berechnet solange neue Wand-Kollisionspunkte, bis die Kugel eingelocht ist.
	 * @param width Breite des Feldes.
	 * @param length Hoehe des Feldes.
	 * @param startX x-Koordinate des Startwertes.
	 * @param startY y-Koordinate des Startwertes.
	 * @param dirX x-Wert des Richtungsvektors.
	 * @param dirY y-Wert des Richtungsvektors.
	 * @return Gibt zurueck, wie viele Wandberuehrungen bis zum Einlochen stattgefunden haben.
	 */
	public int berechne(double width, double length, double startX, double startY, double dirX, double dirY) {
		if(width<1 || length<1 || startX<0 || startY<0) {
			return -1;
		} else if(startX>width || startY>length) {
			return -3;
		}
		
		startVect = new Point2D.Double(startX, startY);
		dirVect = new Point2D.Double(dirX, dirY);
		size[0] = width;
		size[1] = length;
		corners[0] = new Point2D.Double(0,0);
		corners[1] = new Point2D.Double(width,0);
		corners[2] = new Point2D.Double(0,length);
		corners[3] = new Point2D.Double(width,length);
		
		while(!done) {
			double[] point = calcCollis(findWall());
			startVect = new Point2D.Double(point[0], point[1]);
			System.out.println(startVect);
			for(Point2D.Double c:corners) {
				if(epsilonEquals(startVect, c)) {
					done = true;
				}
			}
			contacts++;
			System.out.println(contacts);
			if(contacts>1000000) {
				return -2;
			}
		}
		return contacts;
	}
	
	/**
	 * Diese Methode berechnet, welches die naechste Wand ist, die beruehrt wird.
	 * Dies ist stets die Wand, die den kleinsten Wert r, der groesser als 0 ist hat.
	 * @return Gibt die Nummer der getroffenen Wand zurueck.
	 */
	private int findWall() {
		/**
		 * Wall x=0: 3
		 * Wall x=width: 1
		 * Wall y=0: 0
		 * Wall y=length: 2
		 */
		double[] r = new double[4];
		r[0] = (0-startVect.y)/dirVect.y;
		r[1] = (size[0]-startVect.x)/dirVect.x;
		r[2] = (size[1]-startVect.y)/dirVect.y;
		r[3] = (0-startVect.x)/dirVect.x;
		
		double sol = Double.MAX_VALUE;
		int index = -1;
		for(int i=0;i<r.length;i++) {
			if(r[i]<sol && r[i]>0) {
				sol = r[i];
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * Diese Methode fuehrt eine Kollision aus, berechnet den neuen Stuetzvektor und veraendert den Richtungsvektor.
	 * @param wallNum Die Nummer der Wand, mit der kollidiert wird.
	 * @return Gibt den Startvektor zurueck.
	 */
	private double[] calcCollis(int wallNum) {
		double[] arr = new double[2];
		switch(wallNum) {
		case 0:
			arr[0] = startVect.x + ((0-startVect.y)/dirVect.y)*dirVect.x;
			arr[1] = 0;
			dirVect.y = -dirVect.y;
			break;
		case 1:
			arr[0] = size[0];
			arr[1] = startVect.y + ((size[0]-startVect.x)/dirVect.x)*dirVect.y;
			dirVect.x = -dirVect.x;
			break;
		case 2:
			arr[0] = startVect.x + ((size[1]-startVect.y)/dirVect.y)*dirVect.x;
			arr[1] = size[1];
			dirVect.y = -dirVect.y;
			break;
		case 3:
			arr[0] = 0;
			arr[1] = startVect.y + ((0-startVect.x)/dirVect.x)*dirVect.y;
			dirVect.x = -dirVect.x;
			break;
		default:break;
		}
		return arr;
	}
	
	/**
	 * Diese Methode vergleicht zwei Punkte miteinander.
	 * @param p0 Punkt 1.
	 * @param p1 Punkt 2.
	 * @return Gibt zurueck, ob die beiden Punkte gleich sind.
	 */
	private boolean epsilonEquals(Point2D p0, Point2D p1) {
	    double epsilon = 1e-2;
	    return epsilonEquals(p0, p1, epsilon);
	}
	
	/**
	 * Diese Methode vergleicht zwei Punkte mit Fehlertoleranz miteinander.
	 * @param p0 Punkt 1.
	 * @param p1 Punkt 2.
	 * @param epsilon Fehlertoleranz.
	 * @return Gibt zurueck, ob die beiden Punkte gleich sind.
	 */
	private boolean epsilonEquals(Point2D p0, Point2D p1, double epsilon) {
	    return epsilonEquals(p0.getX(), p1.getX(), epsilon) && epsilonEquals(p0.getY(), p1.getY(), epsilon);
	}
	
	/**
	 * Diese Methode vergleicht zwei Double-Werte mit Fehlertoleranz miteinander.
	 * @param x Double-Wert 1
	 * @param y Double-Wert 2
	 * @param epsilon Fehlertoleranz.
	 * @return Gibt zurueck, ob die beiden Double gleich sind.
	 */
	private boolean epsilonEquals(double x, double y, double epsilon) {
	    return Math.abs(x - y) <= epsilon * Math.abs(x);
	}
}