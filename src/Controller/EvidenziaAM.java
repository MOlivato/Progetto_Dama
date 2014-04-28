package Controller;

import Model.AlberoMosse;
import Model.Model;

/**
 * Classe utilizzate per evidenziare gli albero mosse delle pedine movibili
 * riferite al giocatore che può muovere in questo turno
 * @author molivato
 *
 */
public class EvidenziaAM {
	
	public EvidenziaAM(){}
	
	/**
	 * 
	 * @param mod Model di pedine da evidenziare
	 * @param am AlberoMosse del pezzo da evidenziare
	 */
	//Classe che evidenzia un AlberoMosse
	public EvidenziaAM(Model mod, AlberoMosse am){
		if(am!=null)
			mod.getDamiera().getCasella(am.getPosizione()).setEvidenziata(true);
	} 
	
	/**
	 * Funzione che evidenzia progressivamente i rami dell'albero AlberoMosse
	 * a seconda della scelta dell'utente. I rami dell'albero mosse sono chiamati 
	 * subAlberoMosse
	 * @param mod Model del pezzo da evidenziare
	 * @param am AlberoMosse del pezzo da evidenziare
	 */
	public void EvidenziaSAM(Model mod, AlberoMosse am){
		if(am!=null)
			if(!am.isSAMNull()){
				//System.out.println("Il sotto albero non è NULL! ");
				for(int i=0;i<am.getLengthSAM();i++)
					if(am.getSAM(i) != null)
						new EvidenziaAM(mod, am);
			}else{
				//System.out.println("Il sotto albero è NULL! ");
				new EvidenziaAM(mod, am);
			}
	}
	
	/**
	 * Funzione che disevidenzia ricorsivamente un AlberoMosse
	 * lasciando evidenziata solo la radice.
	 * @param mod Model del pezzo da disevidenziare
	 * @param am AlberoMosse del pezzo da disevidenziare
	 * @param rec Parametro a true se la funzione è in uno stato ricorsivo
	 */
	public void recDisevidSAM(Model mod, AlberoMosse am, boolean rec){
		mod.getDamiera().getCasella(am.getPosizione()).setClicked(false);
		if(am!=null && (am.getValue()>0 || rec) && !am.isSAMNull())
					for(int i=0;i<am.getLengthSAM();i++)
						if(am.getSAM(i)!=null){
							mod.getDamiera().getCasella(am.getSAM(i).getPosizione()).setEvidenziata(false);
							recDisevidSAM(mod, am.getSAM(i), true);
						}
	}
}
