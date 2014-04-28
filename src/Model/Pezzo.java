package Model;

import java.awt.Color;

/**
 * Classe che rappresenta un Pezzo nel gioco della Dama Italiana.
 * Puo' quindi identificare sia una dama che una pedina.
 * @author molivato
 *
 */
public class Pezzo{
	private Color colore; //Colore della pedina
	private AlberoMosse am; //Albero che indica le mosse fattibili dalla pedina
	private boolean tipo; //Tipo del pezzo (true = dama, false = pedina)
	
	/**
	 * Funzione costruttore di una pedina
	 * @param color colore della pedina
	 */
	public Pezzo(Color color){
		colore = color;
		tipo = false;
		am = null;
	}
	
	/**
	 * Funzione costruttore di copia profonda
	 * @param p Pezzod a copiare in profondita'
	 */
	public Pezzo(Pezzo p){
		colore = p.getColore();
		tipo = p.isDama();
		am = new AlberoMosse(p.getAlberoMosse());
	}

	/**
	 * Funzione che ritorna il colore del Pezzo
	 */
	public Color getColore(){
		return colore;
	}
	

	/**
	 * Funzione che ritorna l'AlberoMosse del Pezzo
	 */
	public AlberoMosse getAlberoMosse() {
		return am;
	}
	
	
	/**
	 * Funzione che setta l'AlberoMosse del Pezzo
	 * @param am AlberoMosse da settare
	 */
	public void setAlberoMosse(AlberoMosse am) {
		this.am = am;
		//System.out.println("Il mio albero mosse è: "+am);
	}

	/**
	 * Funzione che ritorna True se il pezzo e' una Pedina
	 */
	public boolean isPedina() {
		return !tipo;
	}
	

	/**
	 * Funzione che ritorna True se il pezzo e' una Dama
	 */
	public boolean isDama(){
		return tipo;
	}
	

	/**
	 * Funzione che ritorna True se il pezzo e' nero
	 */
	public boolean isBlack(){
		return colore == Color.BLACK;
	}
	

	/**
	 * Funzione che ritorna True se il pezzo ha l'AlberoMosse
	 * a NULL
	 */
	public boolean isNullAM(){
		return am == null;
	}
	
	/**
	 * Funzione che trasforma un Pezzo di tipo Pedina
	 * in un Pezzo di tipo Dama
	 */
	public void changeToDama(){
		tipo = true;
	}

	/**
	 * Funzione che ritorna True se due pezzi sono equivalenti
	 * @param p Pezzo da usare per il confronto
	 */
	public boolean equals(Pezzo p){
		return colore==p.getColore() && p.isDama() == p.isDama();
	}
	
	/**
	 * Funzione che verifica se due pezzi sono dello stesso colore
	 * @param p Pezzo da usare nel confronto
	 */
	public boolean equalsColor(Pezzo p){
		return this.isBlack() == p.isBlack();
	}
	
	public String toString(){
		return (isDama()?"Dama":"Pedina")+" "+getColore();
	}
}
