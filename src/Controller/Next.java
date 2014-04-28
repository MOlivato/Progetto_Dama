package Controller;

import Model.Model;
/**
 * Classe che setta il Model per il turno successivo.
 * @author molivato
 *
 */
public class Next {
	
	/**
	 * Funzione che segnala nel Model il nuovo turno riaggiornandone le caratteristiche
	 * @param mod Model da far passare al nuovo turno
	 */
	public Next(Model mod){
		//Passo il turno al giocatore successivo
		mod.nextTurno();
		
		//Aggiorno il model
		new Update(mod);
	}

}
