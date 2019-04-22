package interfaces;

import java.awt.Graphics2D;
/**
 * Cette interface rend un objet dessinable dans le monde des pixels
 * @author Thomas Corbeil
 *
 */
public interface DessinablePixel {
	/**
	 * Méthode qui permet de dessiner un objet dans le monde des pixels
	 * @param g2d le contexte graphique dans lequel on veut dessiner l'objet
	 * @param x la position x du coin supérieur gauche de l'objet
	 * @param y la position y du coin supérieur gauche de l'objet
	 * @param largeur la largeur de l'objet à dessiner
	 * @param hauteur la hauteur de l'objet à dessiner
	 * @param rotation la rotation en degrés par rapport au centre de l'objet à dessiner
	 * @param centreRotationX la position x du centre de la rotation
	 * @param centreRotationY la position Y du centre de la rotation
	 */
	public void dessinerPixel(Graphics2D g2d, int x, int y, int largeur, int hauteur, double rotation, double centreRotationX, double centreRotationY);


}
