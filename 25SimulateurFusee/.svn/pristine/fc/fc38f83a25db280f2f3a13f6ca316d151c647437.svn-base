package zonededessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;
/**
 * 
 * @author Thomas Corbeil
 * Méthode qui crée un panel avec une minimap indiquant l'altitude
 */
public class ZoneDessinMiniMap extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1517718536825343401L;
	private double hauteurPanel;
	private double largeurPanel;
	private double longueurLigne;
	private double pourcentageAltitude;
	private boolean premiereFois = true;
	private final double ALTITUDE_MAXIMALE = 100000;
	private final double DISTANCE_REBORD_VERTICAL = 20;
	private final double DISTANCE_REBORD_HORIZONTAL = 20;
	private final double RAYON_POINT = 5;
	private final double DISTANCE_STRING = 5;

	/**
	 * Create the panel.
	 */
	public ZoneDessinMiniMap() {
		setBackground(Color.black); 
	}
	
	/**
	 * le paintComponent pour dessiner la minimap
	 * Graphics g le contexte graphique
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;	
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(premiereFois) {
			initialiserValeurs();
			premiereFois = false;
		}
		
		g2d.setColor(Color.gray);
		Line2D.Double ligneGrise = new Line2D.Double(largeurPanel/2, DISTANCE_REBORD_VERTICAL, largeurPanel/2, hauteurPanel - DISTANCE_REBORD_VERTICAL);
		g2d.draw(ligneGrise);
		Line2D.Double ligneRebordSup = new Line2D.Double(DISTANCE_REBORD_HORIZONTAL, DISTANCE_REBORD_VERTICAL, largeurPanel - DISTANCE_REBORD_HORIZONTAL, DISTANCE_REBORD_VERTICAL);
		g2d.draw(ligneRebordSup);
		longueurLigne = (hauteurPanel - (DISTANCE_REBORD_VERTICAL * 2)) * pourcentageAltitude;
		g2d.setColor(Color.RED); 
		Line2D.Double ligneRouge = new Line2D.Double(largeurPanel/2, hauteurPanel - DISTANCE_REBORD_VERTICAL - longueurLigne, largeurPanel/2, hauteurPanel - DISTANCE_REBORD_VERTICAL);
		g2d.draw(ligneRouge);
		Line2D.Double ligneRebordInf = new Line2D.Double(DISTANCE_REBORD_HORIZONTAL, hauteurPanel - DISTANCE_REBORD_VERTICAL, largeurPanel - DISTANCE_REBORD_HORIZONTAL, hauteurPanel - DISTANCE_REBORD_VERTICAL);
		g2d.draw(ligneRebordInf);
		g2d.setColor(Color.GREEN); 
		Ellipse2D.Double cercleFusee = new Ellipse2D.Double(largeurPanel/2 - RAYON_POINT/2, hauteurPanel - DISTANCE_REBORD_VERTICAL - longueurLigne - RAYON_POINT/2, RAYON_POINT, RAYON_POINT);
		g2d.fill(cercleFusee);
		String altitudeSup = "100 000 m";
		String altitudeInf = "0 m";
		g2d.drawString(altitudeSup,(int) (DISTANCE_REBORD_HORIZONTAL),(int) (DISTANCE_REBORD_VERTICAL - DISTANCE_STRING));
		g2d.drawString(altitudeInf,(int) (DISTANCE_REBORD_HORIZONTAL),(int) (hauteurPanel - DISTANCE_REBORD_VERTICAL/2 + DISTANCE_STRING));
		
	}
	/**
	 * Méthode qui initialise les valeurs de la zone de dessin
	 */
	private void initialiserValeurs() {
		hauteurPanel = getHeight();
		largeurPanel = getWidth();
		pourcentageAltitude = 0;
	}
	/**
	 * Méthode qui met à jour les valeurs de la zone de dessin
	 * @param altitude l'altitude de la fusée
	 */
	public void mettreAJourValeurs(double altitude) {
		pourcentageAltitude = (altitude/ALTITUDE_MAXIMALE);
		longueurLigne =  (hauteurPanel - (DISTANCE_REBORD_VERTICAL * 2)) * (pourcentageAltitude);
		repaint();
	}
	/**
	 * Méthode qui réinitialise les valeurs de la zone de dessin
	 */
	public void reinitialiserValeurs() {
		initialiserValeurs();
		premiereFois = false;
	}

}
