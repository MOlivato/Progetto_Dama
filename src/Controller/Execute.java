package Controller;

import Model.AlberoMosse;
import Model.Model;
import Model.Mossa;
import Model.Posizione;

/**
 * Classe che esegue una mossa.
 * Ovvero modifica il model per realizzare la mossa scelta dall'utente.
 * Finita l'esecuzione della mossa il turno è passato al giocatore successivo
 * @author molivato
 *
 */
public class Execute {
	
	/**
	 * Funzione che data una mossa la esegue utilizzando come riferimento
	 * il model passatogli come parametro
	 * @param mod Model da modificare nell'esecuzione della mossa
	 * @param mossa Mossa da effettuare sul Model
	 */
	public Execute(Model mod, Mossa mossa){
		//System.out.println("!!!Devo eseguire la mossa!!!");
		//Devo effettuare la mangiata se necessario
		
		//System.out.println("La mossa da eseguire contiene "+mossa.getLength()+" posizioni...");
		
		//Se devo mangiare mangio tutti i pezzi indicati dalla mossa
		if(isEating(mossa.getPosMossa(0),mossa.getPosMossa(1))){
			for(int i=0; i<mossa.getLength()-1; i++){
				mod.getDamiera().setPezzo(this.getPosEated(mossa.getPosMossa(i), mossa.getPosMossa(i+1)), null);
				if(mod.getGiocatoreTurno().equals(mod.getUtente()))
					mod.getComputer().decreaseNumPezzi();
				else
					mod.getUtente().decreaseNumPezzi();
				//System.out.println("I dati del giocatore sono: "+mod.getGiocatoreTurno());
			}
			mod.setNumMCNE(0); //Ricomincio il conteggio delle mosse
		}else{
			mod.increaseNumMCNE(); //Incremento il numero delle mosse effettuate senza mangiare
		}
		
		
		//Sposto il pezzo nella posizione di arrivo
		mod.getDamiera().setPezzo(mossa.getLastPos(), mod.getDamiera().getPezzo(mossa.getPosMossa(0)));
		//Cancello il riferimento al pezzo nella posizione di partenza
		mod.getDamiera().setPezzo(mossa.getPosMossa(0), null);
		
		//Resetto l'albero del pezzo eliminando l'albero appena eseguito
		mod.getDamiera().getPezzo(mossa.getLastPos()).setAlberoMosse(new AlberoMosse(mossa.getPosMossa(mossa.getLength()-1)));
		
		//Se sono una pedina e sono arrivato in fondo devo diventare una dama
		if(mod.getDamiera().isFinishRow(mossa.getLastPos(), mod.getColoreTurno())){
			//System.out.println("La mossa ha creato una nuova Dama!!");
			mod.getDamiera().getPezzo(mossa.getLastPos()).changeToDama();
		}
		
		//System.out.println("MOSSA ESEGUITA!!");
		
		//Passo al prossimo turno
		new Next(mod);		
	}
	
	//Ritorno la posizione del pezzo che sto mangiando
	private Posizione getPosEated(Posizione start, Posizione end){
		//Recupero la riga e la colonna
		return new Posizione((start.getRow()+end.getRow())/2,(start.getCol()+end.getCol())/2);
	}
	
	//Funzione che ritorna true se la mossa è una mangiata
	private boolean isEating(Posizione start, Posizione end){
		return end.getCol()-start.getCol()>1||end.getCol()-start.getCol()<-1;
	}
	
}
