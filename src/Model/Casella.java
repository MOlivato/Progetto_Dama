package Model;

import java.awt.Color;

/**
 * Classe che rappresenta una casella della damiera.
 * @author molivato
 *
 */
public class Casella{
	//Classe che rappresenta una casella di una damiera
	private Pezzo pezzo;
	private Posizione pos;
	private Color colore;
	private boolean evidenziata;
	private boolean clicked;
	
	/**
	 * Funzione costruttore 
	 * @param pzz Pezzo da contenere
	 * @param p Posizione all'interno della damiera
	 * @param evid parametro che indica se sono evidenziata 
	 */
	public Casella(Pezzo pzz, Posizione p, boolean evid){
		pezzo = pzz;
		pos = p;
		evidenziata = evid;
		clicked = false;
		colore = initColore();
	}
	
	/**
	 * Funzione costruttore di copia profonda
	 * @param cas Casella da copiare in profondita'
	 */
	public Casella(Casella cas){
		pezzo = cas.getPezzo()!=null?new Pezzo(cas.getPezzo()):null;
		pos = new Posizione(cas.getPosizione());
		evidenziata = cas.isEvidenziata();
		clicked = cas.isClicked();
		colore = cas.getColore();
	}
	
	//Funzione che inizializza il colore della casella
	private Color initColore(){
		return pos.getRow()%2==pos.getCol()%2?Color.BLACK:Color.WHITE;
	}
		
	/**
	 * Funzione che ritorna il pezzo contenuto
	 */
	public Pezzo getPezzo() {
		return pezzo;
	}
	
	/**
	 * Funzione che ritorna la posizione  di riferimento 
	 * della casella all'interno della damiera
	 */
	public Posizione getPosizione() {
		return pos;
	}
	
	/**
	 * Funzione che ritorna il colore della casella
	 */
	public Color getColore(){
		return colore;
	}
	
	/**
	 * Funzione che setta il pezzo contenuto nella casella
	 * @param p Pezzo da contenere
	 */
	public void setPezzo(Pezzo p) {
		pezzo = p;
	}
	
	/**
	 * Funzione che evidenzia o disevidenzia la casella
	 * @param evidenzia vale True se devo evidenziarla altrimenti False
	 */
	public void setEvidenziata(boolean evidenzia) {
		evidenziata = evidenzia;
		
		if(!evidenziata && clicked) setClicked(false);
		else colore=evidenziata?Color.BLUE:initColore();
	}
	
	/**
	 * Funzione che setta la casella se e' cliccata o no
	 * @param clicked vale True se e' cliccate altrimenti False
	 */
	public void setClicked(boolean clicked){
		this.clicked = clicked;
		colore = clicked?Color.GREEN:evidenziata?Color.BLUE:initColore();
	}
	
	/**
	 * Funzione che ritorna True se la casella e' evidenziata
	 */
	public boolean isEvidenziata() {
		return evidenziata;
	}
	
	/**
	 * Funzione che ritorna True se la casella e' cliccata
	 */
	public boolean isClicked() {
		return clicked;
	}
	
	/**
	 * Funzione che ritorna True se la casella e' libera
	 * (non contiene pezzi)
	 */
	public boolean isFree(){
		return pezzo == null;
	}
	
	public String toString(){
		return "posizione: "+pos+" pezzo: "+pezzo+ " evid: "+evidenziata;
	}
}
