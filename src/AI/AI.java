package AI;

import Controller.Controller;
import Model.Giocatore;
import Model.Model;

/**
 * Classe che implementa l'intelligenza artificiale.
 * In pratica è la classe rappresentativa del package AI
 * 
 * @author molivato
 */
public class AI{
	private Model mod;
	private Controller ctrl;
	private Giocatore giocatore;
	private ScegliMosse IntArt;
	
	/**
	 * Costruttore della classe AI
	 * @param m Model che rappresenta la struttura dati della partita attuale
	 * @param c Controller che sta effettuando il controllore della partita attuale
	 * @param g Giocatore a cui e' associata l'AI
	 */
	public AI(Model m, Controller c, Giocatore g){
		mod = m;
		giocatore = g;
		ctrl = c;
		IntArt = new ScegliMosse(mod);
	}
	
	/**
	 * Funzione che invia al controller la mosse che l'AI vuol eseguire
	 * dopo averla opportunamente scelta
	 */
	public void move(){
		System.out.println("Sto facendo muovere l'AI!!");
		
		//Faccio sciegliere le mosse a seconda della tipologia di mosse
		TabellaScelta ts = IntArt.scegli();
		
		ctrl.move(ts.getMossaCasuale(), true);
		
		System.out.println("Finito di muovere l'AI!!");
	}
	
	/**
	 * @return <code>giocatore<code> il giocatore che sto rappresentando
	 */
	public Giocatore getGiocatore(){
		return giocatore;
	}
	

}
