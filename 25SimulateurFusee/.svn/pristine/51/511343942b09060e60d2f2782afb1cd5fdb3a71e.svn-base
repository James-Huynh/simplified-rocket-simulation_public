package ballondessinable;

import java.awt.Color;
import java.awt.Graphics2D;

import pieces.Capsule;

/**
 * La classe BallonCapsule dérivant de BallonGenerique gère un ballon informatif à l'intention des capsules 
 * @author James Huynh
 *
 */
public class BallonCapsule extends BallonGenerique {

	private double forceRCS;
	private String txtRCS;
	private int positionXInitialeTxt, positionYInitialeTxt, positionYTxt, distanceInterTexte;

	/**
	 * Le constructeur pour les ballons spécifiques aux capsules 
	 * @param capsule La capsule associée 
	 * @param longueurBallon La longueur du ballon
	 * @param hauteurBallon La hauteur de ballon
	 */
	public BallonCapsule(Capsule capsule, int longueurBallon, int hauteurBallon) {
		super(capsule.getNomPiece(), capsule.getMasse());
		this.forceRCS = capsule.getForceRCS();
		this.distanceInterTexte = getDistanceInterTexte();

		creerTexteCapsule();
	}

	/**
	 * Méthode pour dessiner un ballon adapté pour une capsule
	 */
	public void dessinerBallon(Graphics2D g2d, int x, int y, int longueur, int hauteur) {
		super.dessinerBallon(g2d, x, y, longueur, hauteur);	
		
		positionXInitialeTxt = getPositionXInitialeTxt();
		positionYInitialeTxt = getPositionYInitialeTxt();
		
		Color couleurInitiale = g2d.getColor();	
		
		g2d.setColor(getCouleurTexte());
		positionYTxt = positionYInitialeTxt;
		g2d.drawString(getTxtNom(), positionXInitialeTxt, positionYTxt);
		positionYTxt += distanceInterTexte;
		g2d.drawString(getTxtMasse(), positionXInitialeTxt, positionYTxt);
		positionYTxt += distanceInterTexte;
		g2d.drawString(txtRCS, positionXInitialeTxt, positionYTxt);
		
		g2d.setColor(couleurInitiale);
	}

	/**
	 * Méthode pour créer le texte spécifique aux capsules
	 */
	private void creerTexteCapsule() {
		creerTexte();
		txtRCS = ("Force RCS (N): " + forceRCS);
	}

}
