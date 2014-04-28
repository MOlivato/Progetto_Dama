package Controller;

import java.awt.Color;

import Model.AlberoMosse;
import Model.Model;
import Model.Posizione;

/**
 * Classe che ha il compito di pulire la Foresta di AlberoMosse dei pezzi
 * del giocatore riferito al turno attuale. Per la pulizia si seguo le regole
 * della dama italiana.
 * @author molivato
 *
 */
public class PulisciFAM{
	
	/**
	 * Funzione che pulisce la Foresta di AlberoMosse
	 * secondo le regole della dama italiana, ai pezzi
	 * del giocatore del turno attuale.
	 * @param mod Model di riferimento del gioco dove pulire la foresta
	 */
	public PulisciFAM(Model mod){
		
		//Recupero il valore massimo per un albero mosse nella foresta
		int max = getMaxValueForest(mod, mod.getColoreTurno());
		//Recupero il valore massimo di dame mangiate
		int ndmax = getMaxNDEForest(mod, mod.getColoreTurno());
		
		System.out.println("Il massimo valore della foresta e': "+max);
		System.out.println("Il massimo numero di dame per la foresta e': "+ndmax);
				
		//Elimino gli alberi della foresta a valore minimo
		cleanValueForest(mod, max, ndmax);
		
		//Elimino tra gli alberi con lo steso valore e stesso N di dame
		//quelli di una pedina se esiste una dama con un albero valido
		if(max>1) cleanNotDamaForest(mod);
		
		//Pulisco a fondo i singoli alberi rimasti
		deepCleanTrees(mod);	
	}
	
	//Funzione che restituisce l'albero con valore massimo nella foresta di
	// alberi dei pezzi di un determinato colore col
	private int getMaxValueForest(Model mod, Color col){
		int max = 0;
		Posizione temp = null;
		
		for(int i=0;i<mod.getDamiera().getDim();i++)
			for(int j=0;j<mod.getDamiera().getDim();j++){
				temp = new Posizione(i,j);
				if(!mod.getDamiera().isFreePos(temp) && !mod.getDamiera().getPezzo(temp).isNullAM())
					if(mod.getDamiera().getPezzo(temp).getColore() == col)
						if(mod.getDamiera().getPezzo(temp).getAlberoMosse().getValue()>max)
							max = mod.getDamiera().getPezzo(temp).getAlberoMosse().getValue();
			}
					
		return max;
	}
	
	//Funzione che restituisce l'albero con valore massimo nella foresta di
	// alberi dei pezzi di un determinato colore col
	private int getMaxNDEForest(Model mod, Color col){
		int ndmax = 0;
		Posizione temp = null;
		
		for(int i=0;i<mod.getDamiera().getDim();i++)
			for(int j=0;j<mod.getDamiera().getDim();j++){
				temp = new Posizione(i,j);
				if(!mod.getDamiera().isFreePos(temp) && !mod.getDamiera().getPezzo(temp).isNullAM())
					if(mod.getDamiera().getPezzo(temp).getColore() == col)
						if(mod.getDamiera().getPezzo(temp).getAlberoMosse().getNumDameEated()>ndmax)
							ndmax = mod.getDamiera().getPezzo(temp).getAlberoMosse().getNumDameEated();
			}
					
		return ndmax;
	}

	//Funzione che pulisce la foresta eliminando gli alberi
	// con valore e numero di dame inferiore al massimo registrato nella foresta
	private void cleanValueForest(Model mod, int max, int ndmax){
		Posizione temp = null;
		
		for(int i=0;i<mod.getDamiera().getDim();i++)
			for(int j=0;j<mod.getDamiera().getDim();j++){
				temp = new Posizione(i,j);
				if(!mod.getDamiera().isFreePos(temp) && !mod.getDamiera().getPezzo(temp).isNullAM())
					if(mod.getDamiera().getPezzo(temp).getColore() == mod.getColoreTurno())
						if(mod.getDamiera().getPezzo(temp).getAlberoMosse().getValue() != max
							||  mod.getDamiera().getPezzo(temp).getAlberoMosse().getNumDameEated() != ndmax)
							resetTree(mod, temp);
			}
		
	}

	//Funzione che pulisce (tra alberi equivalenti) alberi di pedine se esistono
	//alberi di dame validi
	private void cleanNotDamaForest(Model mod){
		boolean damaTree = false;
		Posizione temp = null;
				
		//Trovo se c'è una dama con alberomosse non null
		for(int i=0;i<mod.getDamiera().getDim();i++)
			for(int j=0;j<mod.getDamiera().getDim();j++){
				temp = new Posizione(i,j);
				if(!mod.getDamiera().isFreePos(temp))
					if(mod.getDamiera().getPezzo(temp).getColore() == mod.getColoreTurno())
						if(mod.getDamiera().getPezzo(temp).isDama() && mod.getDamiera().getPezzo(temp).getAlberoMosse().getValue()>1){
							damaTree = true;
							break;
						}
			}
		
		//Se c'è una dama con albero mosse non null elimino gli alberi
		//delle pedine se esistono
		if(damaTree)
			for(int i=0;i<mod.getDamiera().getDim();i++)
				for(int j=0;j<mod.getDamiera().getDim();j++){
					temp = new Posizione(i,j);
					if(!mod.getDamiera().isFreePos(temp) && mod.getDamiera().getPezzo(temp).getAlberoMosse().getValue()>0)
						if(mod.getDamiera().getPezzo(temp).getColore() == mod.getColoreTurno())
							if(mod.getDamiera().getPezzo(temp).isPedina())
								resetTree(mod, temp);	
				}
	}
	
	//Funzione che resetta una albero mosse di un pezzo
	private void resetTree(Model mod, Posizione pos){
		mod.getDamiera().getPezzo(pos).setAlberoMosse(new AlberoMosse(pos));
	}
	
	//Funzione che pulisce a fondo gli ultimi alberi rimasti
	private void deepCleanTrees(Model mod){
		for(int i=0;i<mod.getDamiera().getDim();i++)
			for(int j=0;j<mod.getDamiera().getDim();j++)
				if(!mod.getDamiera().isFreePos(new Posizione(i,j)) && !mod.getDamiera().getPezzo(new Posizione(i,j)).isNullAM()){
					mod.getDamiera().getPezzo(new Posizione(i,j)).getAlberoMosse().clean();
				}
	}
}
