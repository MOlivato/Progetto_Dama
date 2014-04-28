package Model;

import java.awt.Color;

/**
 * Classe che rappresenta la Damiera nel gioco della Dama Italiana.
 * E' una matrice di caselle identificate da una posizione.
 * Permette inoltre di ottenere informazioni utili sullo stato dei pezzi 
 * presenti sul campo.
 * @author molivato
 *
 */
public class Damiera{
	private Casella[][] damiera;
	
	/**
	 * Funzione costruttore
	 */
	//Creo la damiera
	public Damiera(){
		damiera = new Casella[8][8];
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				damiera[i][j] = new Casella(null, new Posizione(i,j), false);
	}
		
	/**
	 * Funzione costruttore di copia profonda
	 * @param altra Damiera usata come riferimento nella copia profonda
	 */
	public Damiera(Damiera altra){
		damiera = new Casella[altra.getDim()][altra.getDim()];
		for(int i=0;i<getDim();i++)
			for(int j=0;j<getDim();j++)
				damiera[i][j] = new Casella(altra.getCasella(new Posizione(i,j)));
	}
	
	/**
	 * Funzione che restituisce l'altezza e la larghezza della damiera
	 * NB: la damiera e' quadrata
	 */
	public int getDim(){
		return 8;
	}
	
	/**
	 * Funzione che restituisce il Pezzo in una Posizione
	 * @param pos Posizione del pezzo da restituire
	 */
	public Pezzo getPezzo(Posizione pos) {
		return damiera[pos.getRow()][pos.getCol()].getPezzo();
	}
	
	/**
	 * Funzione che restituisce la Casella in una Posizione
	 * @param pos Posizione della casella da restituire
	 */
	public Casella getCasella(Posizione pos){
		return damiera[pos.getRow()][pos.getCol()];
	}
	
	/**
	 * Funzione che setta un nuovo Pezzo in una Posizione 
	 * nella Damiera
	 * @param pos Posizione in cui inserire il pezzo
	 * @param nuovo Pezzo da inserire
	 */
	public void setPezzo(Posizione pos, Pezzo nuovo) {
		damiera[pos.getRow()][pos.getCol()].setPezzo(nuovo);
	}
	
	/**
	 * Funzione che setta lo stato di evidenziamento di una Casella
	 * @param pos Posizione della Casella di cui evidenziare lo stato
	 * @param ev parametro che indica se evidenziare o disevidenziare la Casella
	 */
	public void setEvidenziata(Posizione pos, boolean ev) {
		damiera[pos.getRow()][pos.getCol()].setEvidenziata(ev);
	}
	
	/**
	 * Funzione che ritorna True se la Casella in una Posizione
	 * e' evidenziata altrimenti False 
	 * @param pos Posizione della casella 
	 */
	public boolean isEvidPos(Posizione pos) {
		return damiera[pos.getRow()][pos.getCol()].isEvidenziata();
	}
	
	/**
	 * Funzione che ritorna True se la Posizione e' una Posizione valida
	 *  all'interno della Damiera
	 * @param pos Posizione da controllare
	 */
	public boolean isValidPos(Posizione pos){
		return pos.getRow()>=0 && pos.getRow()<getDim() && pos.getCol()>=0 && pos.getCol()<getDim();
	}
	
	/**
	 * Funzione che restituisce True se la Casella
	 * in una Posizione della Damiera e' libera
	 * @param pos Posizione della Casella da controllare
	 */
	public boolean isFreePos(Posizione pos){
		return damiera[pos.getRow()][pos.getCol()].isFree();
	}
	
	/**
	 * Funzione che restituisce True se una Posizione si
	 * riferisce ad una Casella nera 
	 * @param pos Posizione da controllare
	 */
	public boolean isBlackPos(Posizione pos){
		return pos.getRow()%2==pos.getCol()%2;
	} 
	
	/**
	 * Funzione che restituisce True se una pedina e' arrivata
	 * al bordo opposto al lato di partenza.
	 * @param pos Posizione da controllare
	 * @param col Colore della pedina
	 */
	public boolean isFinishRow(Posizione pos, Color col){
		return (!(col==Color.BLACK) && pos.getRow()==0) || (col==Color.BLACK && pos.getRow()==7);
	}
	
	/**
	 * Funzione analoga alla precedente
	 * @param cas Casella da controllare
	 * @param pezzo Pedina da controllare
	 */
	public boolean isFinishRow(Casella cas, Pezzo pezzo){
		return isFinishRow(cas.getPosizione(),pezzo.getColore());
	}
	
	/**
	 * Funzione che ritorna Ture se esiste una foresta valida
	 * di AlberoMosse per il giocatore di colore Nero
	 */
	public boolean existBlackForest(){
		for(int i=0;i<getDim();i++)
			for(int j=0;j<getDim();j++)
				if(!damiera[i][j].isFree() && damiera[i][j].getPezzo().getColore()==Color.BLACK)
					if(!damiera[i][j].getPezzo().getAlberoMosse().isSAMNull())
						return true;
		return false;
	}
	
	/**
	 * Funzione che ritorna Ture se esiste una foresta valida
	 * di AlberoMosse per il giocatore di colore Bianco
	 */
	public boolean existWhiteForest(){
		for(int i=0;i<getDim();i++)
			for(int j=0;j<getDim();j++)
				if(!damiera[i][j].isFree() && damiera[i][j].getPezzo().getColore()==Color.WHITE)
					if(!damiera[i][j].getPezzo().getAlberoMosse().isSAMNull())
						return true;
		return false;
	}
	
	/**
	 * Funzione che svuota la damiera da tutti i pezzi
	 */
	public void clear(){
		for(int i=0;i<getDim();i++)
			for(int j=0;j<getDim();j++)
				damiera[i][j]=new Casella(null, new Posizione(i,j), false);
	}
	
	/**
	 * Funzione che disevidenzia tutte le caselle della damiera
	 */
	public void clearEvid(){
		for(int i=0;i<getDim();i++)
			for(int j=0;j<getDim();j++)
				damiera[i][j].setEvidenziata(false);
	}
	
	/**
	 * Funzione che pulisce tutti i pezzi nella Damiera dai loro AlberoMosse
	 */
	public void clearForest(){
		for(int i=0;i<getDim();i++)
			for(int j=0;j<getDim();j++)
				if(damiera[i][j].getPezzo()!=null)
					damiera[i][j].getPezzo().setAlberoMosse(null);
	}
}
