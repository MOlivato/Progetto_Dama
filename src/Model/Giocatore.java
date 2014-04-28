package Model;

import java.awt.Color;

/**
 * Classe che rappresenta un Giocatore della Dama Italiana
 * @author molivato
 *
 */
public class Giocatore {
	private String nome;
	private int numPezzi;
	private Color colore;
	
	/**
	 * Funzione costruttore
	 */
	public Giocatore(){
		nome = "Sconosciuto";
		numPezzi = 0;
		colore = Color.BLACK;
	}
	
	/**
	 * Funzione costruttore a parametri
	 * @param name nome del Giocatore
	 * @param np numero pezzi del Giocatore
	 * @param col colore dei pezzi del Giocatore
	 */
	public Giocatore(String name, int np, Color col){
		nome = name;
		numPezzi = np;
		colore = col;
	}
	
	/**
	 * Funzione costruttore di copia profonda
	 * @param g Giocatore da copiare in profondita'
	 */
	public Giocatore(Giocatore g){
		nome = new String(g.getNome());
		numPezzi = g.getNumPezzi();
		colore = g.getColore();
	}
	
	/**
	 * Funzione che decrementa il numero di pezzi del giocatore
	 */
	public void decreaseNumPezzi(){
		--numPezzi;
	}
	
	/**
	 * Funzione che setta il nome del gicatore
	 * @param name nuovo nome del Giocatore
	 */
	public void setNome(String name){
		nome = name;
	}
	
	/**
	 * Funzione che restituisce il nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Funzione che restituisce il numero di pezzi
	 */
	public int getNumPezzi() {
		return numPezzi;
	}

	/**
	 * Funzione che restituisce il colore
	 */
	public Color getColore() {
		return colore;
	}
	
	/**
	 * Funzione che verifica l'uguaglianza tra due giocatori
	 * @param g Giocatore da comparare
	 */
	public boolean equals(Giocatore g){
		return colore.equals(g.getColore()) && numPezzi == g.getNumPezzi() && nome.equals(g.getNome());
	}
	
	public String toString(){
		return "Nome: "+nome+" NumeroPezzi: "+numPezzi+" Colore: "+colore;
	}
}
