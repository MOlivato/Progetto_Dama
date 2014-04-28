package AI;

import Model.Model;

/**
 * Classe che dato un Model sceglie le possibili mosse da eseguire
 */
public class ScegliMosse{
	private Model mod;
	
	/**
	 * Classe che dato un Model scelglie le possibili mosse da eseguire
	 * @param m Model che rappresenta la struttura dati della partita attuale
	 */
	//Classe che sceglie una o più possibili mosse da fare
	public ScegliMosse(Model m){
		mod = m;
	}
	
	
	/**
	 * Funzione che crea la tabella di scelta e restituisce 
	 * la tabella di scelta contenente solo le mosse ritenute ottime 
	 * @return <code>ts</code> la tabella di scelta contenente solo le mosse ottime
	 */
	//Funzione che crea una tabella di scelta e restituisce le mosse possibili
	public TabellaScelta scegli(){
		
		TabellaScelta ts = new TabellaScelta();
		
		CreatoreMosse CrMss = new CreatoreMosse(mod);
		RaffinaScelta rs = new RaffinaScelta(mod);		
		
		//Aggiungo le mosse possibili alla tabella di scelta
		CrMss.creaMosse(ts);
		//Raffino la tabella di scelta eliminando le mosse non migliori
		rs.raffina(ts);
		
		//Cerco di muovere le pedine più avanzate
		//if(!ts.haveSomeDama())
			ts.cleanMinRowPedinaTab();
		//else
			//ts.cleanMaxRowTabella();
		
		//Pulisco il vettore dalle mosse non valide
		ts.cleanTab();
		
		return ts;
	}
	

}
