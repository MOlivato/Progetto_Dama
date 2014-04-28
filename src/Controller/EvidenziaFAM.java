package Controller;

import Model.Model;
import Model.Posizione;

/**
 * Classe che evidenzia nella Foresta di AlberoMosse
 * le caselle del giocatore del turno
 * aventi un pezzo con un AlberoMosse valido.
 * @author molivato
 *
 */
public class EvidenziaFAM{
	
	/**
	 * Funzione che evidenzia nella Foresta di AlberoMosse 
	 * le caselle dei pezzi movibili.
	 * @param mod Model di riferimento del gioco su cui devono essere evidenziate le caselle
	 */
	public EvidenziaFAM(Model mod){
		
		//pulisco la damiera dalle vecchie caselle evidenziate
		mod.getDamiera().clearEvid();
		
		//Evidenzio i pezzi che si possono muovere del giocatore che deve muovere		
		for(int i=0;i<mod.getDamiera().getDim();i++)
			for(int j=0;j<mod.getDamiera().getDim();j++)
				if(mod.getDamiera().getPezzo(new Posizione(i,j))!=null)
					if(mod.getDamiera().getPezzo(new Posizione(i,j)).getColore() == mod.getColoreTurno())
						if(mod.getDamiera().getPezzo(new Posizione(i,j)).getAlberoMosse()!=null)
							if(!mod.getDamiera().getPezzo(new Posizione(i,j)).getAlberoMosse().isSAMNull())
								new EvidenziaAM(mod, mod.getDamiera().getPezzo(new Posizione(i,j)).getAlberoMosse());
	}

}
