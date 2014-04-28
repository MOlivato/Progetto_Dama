package Model;

import java.awt.Color;

/**
 * Classe che rappresenta il Model del gioco.
 * Ovvero rappresenta e contiene le strutture dati necessarie alla corretta
 * modellizzazione del gioco e delle sue fuzioni
 * @author molivato
 *
 */
public class Model {
	//Modello basato sulle strutture dati del gioco della dama
	private Giocatore utente;
	private Giocatore computer;
	private Damiera damiera;
	private boolean turno; //turno == false allora turno dei neri altrimenti dei bianchi
	private int statoFine;
	private int numMCNE; //Numero mosse consecutive senza mangiare
	
	/**
	 * Funzione costruttore
	 */
	public Model(){
		damiera = new Damiera();
		turno = true; //Iniziano sempre i bianchi
		statoFine = 0;
	}
	
	/**
	 * Funzione costruttore di copia profonda
	 * @param mod Model da copiare in profondita'
	 */
	public Model(Model mod){
		utente = new Giocatore(mod.getUtente());
		computer = new Giocatore(mod.getComputer());
		damiera = new Damiera(mod.getDamiera());
		turno = mod.getTurno();
		statoFine = mod.getStatoFine();
	}
	
	/**
	 * Funzione che ritorna lo stato di fine della partita
	 */
	public int getStatoFine(){
		return statoFine;
	}
	
	/**
	 * Funzione che ritorna il giocatore riferito all'Utente
	 */
	public Giocatore getUtente(){
		return utente;
	}

	/**
	 * Funzione che ritorna il giocatore riferito al Computer
	 */
	public Giocatore getComputer(){
		return computer;
	}
	
	/**
	 * Funzione che ritorna la Damiera
	 */
	public Damiera getDamiera(){
		return damiera;
	}
	
	/**
	 * Funzione che ritorna il turno attuale
	 */
	public boolean getTurno(){
		return turno;
	}
	
	/**
	 * Funzione che ritorna il numero di mosse consecutive che
	 * non rappresentano una mangiata
	 */
	public int getNumMCNE() {
		return numMCNE;
	}
	
	/**
	 * Funzione che setta in numero di mosse consecutive senza mangiata
	 * @param numMCNE numero di mosse consecutive
	 */
	public void setNumMCNE(int numMCNE) {
		this.numMCNE = numMCNE;
	}
	
	/**
	 * Funzione che setta il Giocatore riferito all'Utente
	 * @param g Giocatore da settare come utente
	 */
	public void setUtente(Giocatore g){
		utente = g;
	}
	

	/**
	 * Funzione che setta il Giocatore riferito al Computer
	 * @param g Giocatore da settare come computer
	 */
	public void setComputer(Giocatore g){
		computer = g;
	}
	
	/**
	 * Funzione che setta lo stato di fine della partita
	 * @param sf stato di fine della partita
	 */
	public void setStatoFine(int sf){
		statoFine = sf;
	}
	
	/**
	 * Incrementa di 1 il numero di mosse consecutive
	 * senza mangiare
	 */
	public void increaseNumMCNE(){
		++numMCNE;
	}
	
	/**
	 * Funzione che fa passare la partita
	 * al prossimo turno
	 */
	public void nextTurno(){
		turno = !turno;
	}
	
	/**
	 * Funzione che ritorna il colore del giocatore
	 * a cui tocca fare la mossa in questo turno
	 */
	public Color getColoreTurno(){
		return turno?Color.WHITE:Color.BLACK;
	}
	
	/**
	 * Funzione che ritorna il giocatore del turno attuale
	 */
	public Giocatore getGiocatoreTurno(){
		return turno?utente:computer;
	}

	
	
}
