package interfaces;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
/**
 * Cette interface rend un objet dessinable
 * @author Thomas Corbeil
 *
 */
public interface Dessinable {
	/**
	 * Méthode qui dessine l'objet dans un monde défini avec des unités réelles
	 * @param g2d le contexte graphique dans lequel on veut dessiner l'objet
	 * @param mat la matrice de transformation passant du monde réel au monde des pixels
	 * @param rotation la rotation en degrés par rapport au centre de l'objet à dessiner
	 * @param x la position x de la pièce en m
	 * @param y la position y de la pièce en m
	 * @param centreRotX la position du centre de la rotation x de la pièce en m
	 * @param centreRotY la position du centre de la rotation y de la pièce en m
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat, double rotation, double x, double y, double centreRotX, double centreRotY);

}
