package pieces;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import vecteur.Vecteur;
/**
 * La classe g�re les diverses pi�ces d'une fus�e et contient toutes les pi�ces existantes
 * @author Thomas Corbeil
 *
 */
public class EnsemblePieces {
	private Pieces[] listeCompletePieces = new Pieces[9];
	private Image[] tabFeuUS = new Image[3];
	private Image[] tabFeuRUS = new Image[3];
	private Image[] tabFeuPRV = new Image[3];
	private Capsule capsule, capsuleTemp;
	private Reservoir reservoir, reservoirTemp;
	private Moteur moteur, moteurTemp;
	private Vecteur position = new Vecteur(0, 0);
	private Vecteur vitesse = new Vecteur(0, 0);
	private Vecteur acceleration = new Vecteur(0, 0);
	private Vecteur positionOrbitale = new Vecteur(0, 0);
	private Vecteur vitesseOrbitale = new Vecteur(0, 0);
	private Vecteur accelerationOrbitale = new Vecteur(0, 0);
	private final double CONSTANTE_MONDE = 10;
	private final double MASSE_CAP_US = 500.0, MASSE_CAP_RUS = 750.0, MASSE_CAP_PRV = 1000.0;
	private final double RCS_CAP_US = 10000.0, RCS_CAP_RUS = 20000.0, RCS_CAP_PRV = 30000.0;
	private final double MASSE_RES_US = 100.0, MASSE_RES_RUS = 200.0, MASSE_RES_PRV = 300.0;
	private final double CARB_RES_US = 1000.0, CARB_RES_RUS = 2000.0, CARB_RES_PRV = 3000.0;
	private final double MASSE_MOT_US = 250.0, MASSE_MOT_RUS = 350.0, MASSE_MOT_PRV = 450.0;
	private final double POUSSEE_MOT_US = 100000.0, POUSSEE_MOT_RUS = 150000.0, POUSSEE_MOT_PRV = 200000.0;
	private final double CONSOMM_CARB_US = 10, CONSOMM_CARB_RUS = 18, CONSOMM_CARB_PRV = 26;
	
	/**
	 * Constructeur qui cr�e l'ensemble de pi�ces et qui initialise les diverses pi�ces de la fus�e
	 */
	public EnsemblePieces() {
		
		initialiserTableauCompletPieces();

		capsule = (Capsule) listeCompletePieces[0];
		reservoir = (Reservoir) listeCompletePieces[3];
		moteur = (Moteur) listeCompletePieces[6];
	}
	/**
	 * M�thode qui fait consommer une certaine quantit� de carburant � la fus�e
	 * @param deltaT le temps en secondes
	 * @param pourcentagePoussee le pourcentage de pouss�e s�lectionn�
	 */
	public void consommerCarburant(double deltaT, double pourcentagePoussee) {
		double consommationCarburant = moteur.getConsommationCarburant();
		reservoir.setCarburantRestant(reservoir.getCarburantRestant() - ((consommationCarburant * deltaT) * pourcentagePoussee));
	}
	/**
	 * M�thode qui indique si la capsule est pr�sente dans la fus�e
	 * @return un bool�en indiquant si la capsule est pr�sente ou non
	 */
	public boolean capsulePresente() {
		if(capsuleTemp != null) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * M�thode qui indique si le r�servoir est pr�sent dans la fus�e
	 * @return un bool�en indiquant si le r�servoir est pr�sent ou non
	 */
	public boolean reservoirPresent() {
		if(reservoirTemp != null) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * M�thode qui indique si le moteur est pr�sent dans la fus�e
	 * @return un bool�en indiquant si le moteur est pr�sent ou non
	 */
	public boolean moteurPresent() {
		if(moteurTemp != null) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * M�thode qui indique si le r�servoir de la fus�e est vide
	 * @return un bool�en indiquant si le r�servoir est vide ou non
	 */
	public boolean reservoirVide() {
		if((reservoir.getCarburantRestant()) > 0) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * M�thode qui retourne un tableau contenant toutes les pi�ces existantes pour construire la fus�e
	 * @return un tableau contenant toutes les pi�ces existantes pour construire la fus�e
	 */
	public Pieces[] getListeCompletPieces() {
		return listeCompletePieces;
	}
	/**
	 * M�thode qui retourne la masse totale de la fus�e sans le carburant
	 * @return la masse totale de la fus�e sans le carburant
	 */
	public double getMasseAVide() {
		double masseFusee = 0;
		
		if(capsulePresente()) {
			masseFusee += capsule.getMasse();
		}
		
		if(reservoirPresent()) {
			masseFusee += reservoir.getMasse();
		}
		
		if(moteurPresent()) {
			masseFusee += moteur.getMasse();
		}
		
		return masseFusee;
	}
	/**
	 * M�thode qui retourne la masse totale de la fus�e avec le carburant
	 * @return la masse totale de la fus�e avec le carburant
	 */
	public double getMasseAvecCarburant() {
		if((capsulePresente() && reservoirPresent()) && moteurPresent()) {
			return (getMasseAVide() + (reservoir.getMasseAvecCarburant()));
		} else {
			return 0.0;
		}
	}
	/**
	 * M�thode qui retourne la pouss�e de la fus�e
	 * @return la pouss�e de la fus�e en N
	 */
	public double getPousseeFusee() {
		if(moteurPresent()) {
			return (moteur.getPoussee());
		} else {
			return 0.0;
		}
	}
	/**
	 * M�thode qui retourne la pouss�e RCS de la fus�e
	 * @return la pouss�e RCS de la fus�e en N
	 */
	public double getPousseeRCS() {
		if(capsulePresente()) {
			return (capsule.getForceRCS());
		} else {
			return 0.0;
		}
	}
	/**
	 * M�thode qui retourne la quantit� de carburant maximale de la fus�e
	 * @return la quantit� de carburant maximale de la fus�e en L
	 */
	public double getCarburant() {
		if(reservoirPresent()) {
			return (reservoir.getCarburant());
		} else {
			return 0.0;
		}
	}
	/**
	 * M�thode qui retourne la quantit� de carburant restante de la fus�e
	 * @return la quantit� de carburant restante de la fus�e en L
	 */
	public double getCarburantRestant() {
		if(reservoirPresent()) {
			return (reservoir.getCarburantRestant());
		} else {
			return 0.0;
		}
	}
	/**
	 * M�thode qui retourne le vecteur position de la fus�e pendant le d�collage
	 * @return le vecteur position de la fus�e pendant le d�collage
	 */
	public Vecteur getPosition() {
		return position;
	}
	/**
	 * M�thode qui d�finit le vecteur position de la fus�e pendant le d�collage
	 * @param position le vecteur position de la fus�e pendant le d�collage
	 */
	public void setPosition(Vecteur position) {
		this.position = position;
	}
	/**
	 * M�thode qui retourne le vecteur vitesse de la fus�e pendant le d�collage
	 * @return le vecteur vitesse de la fus�e pendant le d�collage
	 */
	public Vecteur getVitesse() {
		return vitesse;
	}
	/**
	 * M�thode qui d�finit le vecteur vitesse de la fus�e pendant le d�collage
	 * @param vitesse le vecteur vitesse de la fus�e pendant le d�collage
	 */
	public void setVitesse(Vecteur vitesse) {
		this.vitesse = vitesse;
	}
	/**
	 * M�thode qui retourne le vecteur acc�l�ration de la fus�e pendant le d�collage
	 * @return le vecteur acc�l�ration de la fus�e pendant le d�collage
	 */
	public Vecteur getAcceleration() {
		return acceleration;
	}
	/**
	 * M�thode qui d�finit le vecteur acc�l�ration de la fus�e pendant le d�collage
	 * @param acceleration le vecteur acc�l�ration de la fus�e pendant le d�collage
	 */
	public void setAcceleration(Vecteur acceleration) {
		this.acceleration = acceleration;
	}
	/**
	 * M�thode qui retourne la capsule de la fus�e
	 * @return un objet de type capsule d�finissant la capsule de la fus�e
	 */
	public Capsule getCapsule() {
		return capsule;
	}
	/**
	 * M�thode qui d�finit la capsule de la fus�e
	 * @param capsule un objet de type capsule d�finissant la capsule de la fus�e
	 */
	public void setCapsule(Capsule capsule) {
		if(capsule == null) {
			capsuleTemp = null;
			this.capsule = (Capsule) listeCompletePieces[0];
		} else {
			this.capsule = capsule;
		}
	}
	/**
	 * M�thode qui retourne le r�servoir de la fus�e
	 * @return un objet de type r�servoir d�finissant le r�servoir de la fus�e
	 */
	public Reservoir getReservoir() {
		return reservoir;
	}
	/**
	 * M�thode qui d�finit le r�servoir de la fus�e
	 * @param reservoir un objet de type r�servoir d�finissant le r�servoir de la fus�e
	 */
	public void setReservoir(Reservoir reservoir) {
		if(reservoir == null) {
			reservoirTemp = null;
			this.reservoir = (Reservoir) listeCompletePieces[3];
		} else {
			this.reservoir = reservoir;
		}
	}
	/**
	 * M�thode qui retourne le moteur de la fus�e
	 * @return un objet de type moteur d�finissant le moteur de la fus�e
	 */
	public Moteur getMoteur() {
		return moteur;
	}
	/**
	 * M�thode qui d�finit le moteur de la fus�e
	 * @param moteur un objet de type moteur d�finissant le moteur de la fus�e
	 */
	public void setMoteur(Moteur moteur) {
		if(moteur == null) {
			moteurTemp = null;
			this.moteur = (Moteur) listeCompletePieces[6];
		} else {
			this.moteur = moteur;
		}
	}
	/**
	 * M�thode qui retourne la longueur de la fus�e
	 * @return la longueur de la fus�e en m
	 */
	public double longueurFusee() {
		return ((capsule.getHauteur() + reservoir.getHauteur() + moteur.getHauteur())/CONSTANTE_MONDE);
	}
	/**
	 * M�thode qui retourne la largeur de la fus�e
	 * @return la largeur de la fus�e en m
	 */
	public double largeurFusee() {
		return (capsule.getLargeur()/CONSTANTE_MONDE);
	}
	/**
	 * M�thode qui retourne le vecteur positionOrbitale de la fus�e pendant l'orbite
	 * @return le vecteur positionOrbitale de la fus�e pendant l'orbite
	 */
	public Vecteur getPositionOrbitale() {
		return positionOrbitale;
	}
	/**
	 * M�thode qui d�finit le vecteur positionOrbitale de la fus�e pendant l'orbite
	 * @param positionOrbitale le vecteur position de la fus�e pendant l'orbite
	 */
	public void setPositionOrbitale(Vecteur positionOrbitale) {
		this.positionOrbitale = positionOrbitale;
	}
	/**
	 * M�thode qui retourne le vecteur vitesseOrbitale de la fus�e pendant l'orbite
	 * @return le vecteur vitesse de la fus�e pendant l'orbite
	 */
	public Vecteur getVitesseOrbital() {
		return vitesseOrbitale;
	}
	/**
	 * M�thode qui d�finit le vecteur vitesseOrbitale de la fus�e pendant l'orbite
	 * @param vitesseOrbitale le vecteur vitesse de la fus�e pendant l'orbite
	 */
	public void setVitesseOrbital(Vecteur vitesseOrbitale) {
		this.vitesseOrbitale = vitesseOrbitale;
	}
	/**
	 * M�thode qui retourne le vecteur accelerationOrbitale de la fus�e pendant l'orbite
	 * @return le vecteur acc�l�ration de la fus�e pendant l'orbite
	 */
	public Vecteur getAccelerationOrbitale() {
		return accelerationOrbitale;
	}
	/**
	 * M�thode qui d�finit le vecteur accelerationOrbitale de la fus�e pendant l'orbite
	 * @param accelerationOrbitale le vecteur acc�l�ration de la fus�e pendant l'orbite
	 */
	public void setAccelerationOrbitale(Vecteur accelerationOrbitale) {
		this.accelerationOrbitale = accelerationOrbitale;
	}
	/**
	 * M�thode qui permet de s�lectionner une pi�ce quelconque � l'aide de son indice
	 * @param indice l'indice de la pi�ce dans le tableau contenant toutes les pi�ces
	 */
	public void selectionnerPiece(int indice) {
		if(indice < 3) {
			capsule = (Capsule) listeCompletePieces[indice];
			capsuleTemp = capsule;
		} else {
			if(indice < 6) {
				reservoir = (Reservoir) listeCompletePieces[indice];
				reservoirTemp = reservoir;
			} else {
				moteur = (Moteur) listeCompletePieces[indice];
				moteurTemp = moteur;
			}
		}
	} 
	/**
	 * M�thode qui r�initialise les valeurs de la classe aux valeurs initiales
	 */
	public void reinitialiserValeurs() {
		capsuleTemp = null;
		reservoirTemp = null;
		moteurTemp = null;
		capsule = (Capsule) listeCompletePieces[0];
		reservoir = (Reservoir) listeCompletePieces[3];
		moteur = (Moteur) listeCompletePieces[6];
		position = new Vecteur(0, 0);
		vitesse = new Vecteur(0, 0);
		acceleration = new Vecteur(0, 0);
		positionOrbitale = new Vecteur(0, 0);
		vitesseOrbitale = new Vecteur(0, 0);
		accelerationOrbitale = new Vecteur(0, 0);
		initialiserTableauCompletPieces();
	}
	/**
	 * M�thode qui initialise le tableau contenant toutes les pieces dans la classe
	 */
	private void initialiserTableauCompletPieces() {
		initialiserTabFeu(tabFeuUS, "US_FIRE");
		initialiserTabFeu(tabFeuRUS, "RUS_FIRE");
		initialiserTabFeu(tabFeuPRV, "PRV_FIRE");
		listeCompletePieces[0] = new Capsule("Capsule Mercury", "USCapsule.png", "USCapsuleSquare.png", MASSE_CAP_US, RCS_CAP_US);
		listeCompletePieces[1] = new Capsule("Capsule Vostok", "RUSCapsule.png", "RUSCapsuleSquare.png", MASSE_CAP_RUS, RCS_CAP_RUS);
		listeCompletePieces[2] = new Capsule("Capsule SpaceX Dragon", "PRVCapsule.png", "PRVCapsuleSquare.png", MASSE_CAP_PRV, RCS_CAP_PRV);
		listeCompletePieces[3] = new Reservoir("R�servoir Redstone I", "USTank.png", "USTankSquare.png", MASSE_RES_US, CARB_RES_US);
		listeCompletePieces[4] = new Reservoir("R�servoir Semyorka R-7", "RUSTank.png", "RUSTankSquare.png", MASSE_RES_RUS, CARB_RES_RUS);
		listeCompletePieces[5] = new Reservoir("R�servoir Falcon 9","PRVTank.png" ,"PRVTankSquare.png", MASSE_RES_PRV, CARB_RES_PRV);
		listeCompletePieces[6] = new Moteur("Moteur Rocketdyne A-7", "USEngine.png", "USEngineSquare.png", MASSE_MOT_US, POUSSEE_MOT_US, CONSOMM_CARB_US, tabFeuUS);
		listeCompletePieces[7] = new Moteur("Moteur RD-107A x3", "RUSEngine.png", "RUSEngineSquare.png", MASSE_MOT_RUS, POUSSEE_MOT_RUS, CONSOMM_CARB_RUS, tabFeuRUS);
		listeCompletePieces[8] = new Moteur("Moteur Merlin", "PRVEngine.png", "PRVEngineSquare.png", MASSE_MOT_PRV, POUSSEE_MOT_PRV, CONSOMM_CARB_PRV, tabFeuPRV);
	
	}
	/**
	 * M�thode qui initialise un tableau de feu avec les trois images ayant le pr�fixe entr�
	 * @param tabFeu le tableau des images de feu que l'on veut initialiser
	 * @param prefixe le pr�fixe des images que l'on veut mettre dans le tableau
	 */
	private void initialiserTabFeu(Image[] tabFeu, String prefixe) {
		for(int k = 0; k < 3; k++) {
			URL urlImage = EnsemblePieces.class.getResource("/" + prefixe + k + ".png");

			if(urlImage == null) {
				System.out.println("L'image " + prefixe + k +" est introuvable");
			}

			try {
				tabFeu[k] = ImageIO.read(urlImage);
			} catch (IOException e) {
				System.out.println("Il est impossible de lire le fichier" + prefixe + k);
			}
		}
	}
}
