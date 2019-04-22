package ecouteurspersonnalises;

import java.util.EventListener;
/**
 * Classe de listener qui indique quand passer au vol orbital
 * @author Thomas Corbeil
 *
 */
public interface PasserAuVolOrbitalListener extends EventListener {
	/**
	 * Listener qui indique quand passer au vol orbital
	 */
	public void passerAuVolOrbital();
	
}
