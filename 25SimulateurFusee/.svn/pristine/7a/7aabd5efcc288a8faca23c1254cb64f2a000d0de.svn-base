package pieces;
/**
 * La classe représente une pièce de fusée
 * @author Thomas Corbeil
 *
 */
public class Capsule extends Pieces {
	private double forceRCS;
	/**
	 * Constructeur prenant en compte le nom de l'image de la pièce, sa masse et sa force RCS
	 * @param nomPiece le nom de la pièce
	 * @param nomImage le nom du fichier de l'image de la pièce
	 * @param nomImageCarre le nom de l'image carrée de la pièce
	 * @param masse la masse de la pièce en kg
	 * @param forceRCS la force en N du RCS de la capsule
	 */
	public Capsule(String nomPiece, String nomImage, String nomImageCarre, double masse, double forceRCS) {
		super(nomPiece, nomImage, nomImageCarre, masse);
		this.forceRCS = forceRCS;
	}
	//Javadoc déja générée
	public int verifierTypePiece() {
		return 1;
	}
	/**
	 * Setter qui assigne une forceRCS à la capsule
	 * @param forceRCS la force du RCS de la capsule en N
	 */
	public void setForceRCS(double forceRCS) {
		this.forceRCS = forceRCS;
	}
	/**
	 * Getter qui retourne la forceRCS de la capsule
	 * @return la force RCS de la capsule en N
	 */
	public double getForceRCS() {
		return forceRCS;
	}
	
}
