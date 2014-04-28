package Controller;

import Model.Model;
/**
 * Classe che aggiorna lo stato del Model.
 * In ordine quindi: 
 * 1) creera' la nuova foresta di AlberoMosse per il giocatore del turno
 * 2) pulira' la nuova foresta di AlberoMosse
 * 3) evidenziera' le caselle dei pezzi con AlberoMosse significativo
 * @author molivato
 *
 */
public class Update {
	
	/**
	 * Funzione che esegue le operazioni dell'update dello stato del Model
	 * @param mod Model di riferimento del gioco a cui aggiornare lo stato
	 */
	public Update(Model mod){

		//System.out.println("Sto facendo l'update del model...");
		
		//1)Rigenero gli alberi delle mosse per i pezzi di tutti e due i giocatori
		new CreaFAM(mod);
		
		//2)Devo pulire la foresta di alberi lasciando solo quelli a valore più alto
		new PulisciFAM(mod);
		
		//3)Evidenzio le caselle e le pedine che generano gli alberi delle mangiate più significative
		new EvidenziaFAM(mod);
				
		//System.out.println("Concluso l'update del model...");
	}	
}
