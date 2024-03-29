package ecouteurspersonnalises;

import java.util.EventListener;
/**
 * Classe de listener qui indique quand une pi�ce est s�lectionn�e dans la zone d'assemblage et qui retourne son indice
 * @author Thomas Corbeil
 *
 */
public interface SelectionPiecesListener
extends EventListener {
	/**
	 * Listener qui indique quand une pi�ce est s�lectionn�e dans la zone d'assemblage et qui retourne son indice
	 * @param k l'indice de la pi�ce s�lectionn�e
	 */
	public void envoyerPieces(int k); //Remplacer piece par indice de la pi�ce dans le tableau de pi�ces
	
	/**
	 * Listener qui indique quand une pi�ce est retir�e de la zone d'assemblage
	 * @param k l'indice de la zone s�lectionn�e
	 */
	public void enleverPieces(int k);
}
