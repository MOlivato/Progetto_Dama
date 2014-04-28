package AI;

import Model.Model;

/**
 * Classe che ha in compito di raffinare la tabella di scelta
 * per arrivare, se e' possibile, ad una sola scelta nella tabella
 * 
 * @author molivato
 *
 */
public class RaffinaScelta {
	private Simulator simulatore;
	//Questa classe serve a raffinare la scelta tra le mosse possibili avvalendosi del simulatore
	
	/**
	 * Costruttore che prende un oggetto della classe model
	 * che utilizza negli algoritmi dei selezione della scelta
	 * @param mod model a cui si riferiscono gli algoritmi di raffinamento
	 */
	public RaffinaScelta(Model mod){
		simulatore = new Simulator(mod);		
	}	
	
	/**
	 * Funzione che effettivamente raffina la tabella di scelta
	 * secondo una serie precisa di operazioni di selezione
	 * @param ts tabella di scelta da raffinare, ovvero cerca di eliminare le mosse "non ottime"
	 */
	public void raffina(TabellaScelta ts){
		
		System.out.println("Sto raffinando la tabella di scelta...");
		
		//Ogni mossa nella tabella è simulata e quindi ne sono aggiornati i valori
		for(int i=0;i<ts.getLength();i++)
			simulatore.simulate(ts.getDescrittoreMossa(i));
		
		//Pulisce le mosse che non sono una vittoria(se c'è una vittoria)
		ts.cleanNotWin();
		
		//Prima pulisco la tabella per numero di dame
		ts.cleanNDValueTabella(ts.getMossaMinNDMax());
		
		//Poi pulisco la tabella per il numero di mangiate
		ts.cleanValueTabella(ts.getMossaMinVMax());
		
		System.out.println("Ho pulito la tabella dalle mosse a valori non minimi: \n"+ts);
				
		//Pulisco le mosse che non minimizzano il numero di alberi della foresta nemica
		ts.cleanNumEF(ts.getMossaMinNEF());
		
		//System.out.println("Ho pulito la tabella dal num di alberi nemici non minimo: \n"+ts);
				
		//Pulisce le mosse che non trasformano una pedina in una dama
		ts.cleanNotChangedToDama();
		
		//Pulisce le mosse delle dame che non si stanno avvicinando a
		ts.cleanNotDamaAvvicinando();
		
		//Pulisco il vettore dalle mosse non valide
		ts.cleanTab();
		
		System.out.println("Finito di raffinare la tabella di scelta... \n"+ts);
	}
	
	
	
}
