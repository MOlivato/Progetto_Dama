package AI;

import Model.AlberoMosse;
import Model.Model;
import Model.Mossa;
import Model.Posizione;

/**
 * Classe che recupera dalla foresta di alberomosse tutte le possibili mosse effettuabili.
 * Salva tutte le mosse detro la tabella di scelta 
 * @author molivato
 *
 */
public class CreatoreMosse{
	private Model mod;
	
	/**
	 * 
	 * @param m Model usato per la creazione della tabella di scelta e 
	 * quindi per l'estrazione delle posibili mosse
	 */
	public CreatoreMosse(Model m){
		mod = m;
	}
	
	/**
	 * Funzione che trasforma la foresta in una sequenza di mosse,
	 * aggiungendo qeust'ultime alla tabella di scelta
	 * @param tabsc la tabella di scelta in cui saranno salvate le mosse possibili
	 */
	public void creaMosse(TabellaScelta tabsc){
		System.out.println("Inizio a creare le mosse...\n"+tabsc);
		//Scorro la foresta dei miei alberi e per ogni pezzo recupero l'albero mosse
		Posizione temp = null;
		
		for(int i=0;i<mod.getDamiera().getDim();i++)
			for(int j=0;j<mod.getDamiera().getDim();j++){
				temp = new Posizione(i,j);
				if(mod.getDamiera().getCasella(temp).isEvidenziata()){
					if(mod.getDamiera().getPezzo(temp).getAlberoMosse().getValue()!=0){
						System.out.println("L'albero scelto per recuperare le mosse è: "+mod.getDamiera().getPezzo(temp).getAlberoMosse());
						recuperaMosse(mod.getDamiera().getPezzo(temp).getAlberoMosse(), new Mossa(), tabsc);
					}
				}
			
			}
		
		//Se ci sono mosse di dame le segnalo nel descrittore mossa
		for(int j=0;j<tabsc.getLength();j++)
			if(tabsc.getDescrittoreMossa(j)!=null)
				if(mod.getDamiera().getPezzo(tabsc.getDescrittoreMossa(j).getMossa().getStartPos()).isDama())
					tabsc.getDescrittoreMossa(j).setIsdama(true);
		
		//Pulisco la tabella da campi null
		tabsc.cleanTab();
			
		System.out.println("Finito di creare le mosse...\n"+tabsc);
	}
	
	
	/**
	 * Funzione che dato un alberomosse aggiunge alla tabella di scelta
	 * tutte le mosse possibili contenute in esso
	 * @param am alberomosse da analizzare
	 * @param m mossa che si sta recuperando
	 * @param tabsc tabella di scelta
	 */
	public void recuperaMosse(AlberoMosse am, Mossa m, TabellaScelta tabsc){
		
		//Se l'albero da analizzare non è null
		if(am!=null){
			//Aggiungo la mia posizione alla mossa
			m.addNewPos(am.getPosizione());
		
			//Se l'albero mosse è di una mossa di arrivo di mangiata ho concluso la mossa
			if(am.isSAMNull()){
				m.cleanVett();
				//Ho finito di creare la mossa per questo ramo dell'albero
				tabsc.addDescrittoreMossa(new DescrittoreMossa(m));
			}else{
				//Scorro i sottoalberi e quelli non null recupero le varie possibili mosse
				for(int i=0;i<4;i++){
					if(am.getSAM(i) != null)
						recuperaMosse(am.getSAM(i), new Mossa(m), tabsc);
				}
			}
		}
	}
	
}
