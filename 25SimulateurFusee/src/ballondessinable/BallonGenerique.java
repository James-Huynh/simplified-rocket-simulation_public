package ballondessinable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import interfaces.DessinableBallon;

/**
 * La classe BallonGenerique repr�sente un ballon g�n�ral � l'intention d'une pi�ce de la fus�e
 * @author James Huynh
 *
 */
public class BallonGenerique implements DessinableBallon {
	private Color couleurBallon, couleurTexte, couleurContour = Color.BLACK;
	private double masse;
	private int distanceInterTexte = 20, positionXInitialeTxt, positionYInitialeTxt;
	private Rectangle2D.Double rectangleBallon;
	private String nomPiece, txtNom, txtMasse;

	/**
	 * Le constructeur pour un ballon g�n�rique
	 * @param nom Le nom appropri� � la pi�ce
	 * @param masse La masse appropri�e � la pi�ce
	 */
	public BallonGenerique (String nom, double masse) {
		this.nomPiece = nom;
		this.masse = masse;
	}
	
	//JavaDoc d�j� g�n�r�e
	public void dessinerBallon(Graphics2D g2d, int x, int y, int longueur, int hauteur) {
		couleurBallon = new Color(255, 255, 0, 200);
		couleurTexte = new Color(70, 0, 165, 255);
		rectangleBallon = new Rectangle2D.Double(x, y, longueur, hauteur);
		positionXInitialeTxt = x + 5;
		positionYInitialeTxt = y + 15;

		g2d.setColor(couleurBallon);

		g2d.fill(rectangleBallon);
		g2d.setColor(couleurContour);
		g2d.setStroke(new BasicStroke(1));
		g2d.draw(rectangleBallon);
	}

	/**
	 * M�thode qui initialise le texte g�n�rique du ballon
	 */
	public void creerTexte() {
		txtNom = "Nom: " + nomPiece;
		txtMasse = "Masse (kg): " + masse;
	}

	/**
	 * M�thode qui retourne le texte du nom de la pi�ce
	 * @return Le nom de la pi�ce
	 */
	public String getTxtNom() {
		return txtNom;
	}

	/**
	 * M�thode qui retourne le texte de la masse
	 * @return Le texte pour la masse
	 */
	public String getTxtMasse() {
		return txtMasse;
	}

	/**
	 * M�thode qui retourne la distance entre les lignes des textes
	 * @return La distance entre les textes
	 */
	public int getDistanceInterTexte() {
		return distanceInterTexte;
	}

	/**
	 * M�thode qui retourne la couleur du texte du ballon 
	 * @return La couleur du texte
	 */
	public Color getCouleurTexte() {
		return couleurTexte;
	}

	/**
	 * M�thode qui retourne la position des textes sur l'axe des abscisses
	 * @return la position (pixel) en x
	 */
	public int getPositionXInitialeTxt() {
		return positionXInitialeTxt;
	}

	/**
	 * M�thode qui retourne la position des textes sur l'axe des ordonn�es
	 * @return la position (pixel) en y
	 */
	public int getPositionYInitialeTxt() {
		return positionYInitialeTxt;
	}

}
