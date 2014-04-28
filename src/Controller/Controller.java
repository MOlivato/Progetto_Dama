package Controller;

import AI.AI;
import Model.Model;
import Model.Mossa;
import Model.Posizione;
import View.View;
/**
 * Classe che controlla il gioco e le sue regole
 * @author molivato
 */
public class Controller {
	private Model mod;
	private Muovi muovi;
	private View vista;
	private final int numMaxMosse = 80;
	private AI cpu;
	
	public Controller(Model m){
		mod = m;
		muovi = new Muovi(mod);
	}
	
	/**
	 * Funzione che setta la view da controllare
	 * @param v View da controllare
	 */
	public void setView(View v){
		vista = v;
	}
	
	/**
	 * Funzione che setta il model da controllare
	 * @param m Model da controllare
	 */
	public void setModel(Model m){
		mod = m;
		muovi = new Muovi(mod);
	}
	
	/**
	 * Funzione che setta l'intelligenza artificiale
	 * @param ai AI usata come nemico
	 */
	public void setAI(AI ai){
		cpu = ai;
	}
	
	/**
	 * Funzione che ritorna true se la dama e' "epica"
	 */
	public boolean isEpic(){
		return mod.getUtente().getNome().equals("DamaEpica");
	}
	
	/**
	 * Funzione che esegue il setup del model
	 * secondo le regole della dama italiana
	 */
	public void setup(){
		new Setup(mod);
	}
	
	/**
	 * Funzione che fa aggiornare la view
	 */
	public void update(){
		vista.update();
	}
	
	/**
	 * Funzione che aggiunge posizioni alla mossa in atto dall'utente,
	 * se la mossa e' conclusa allora e' eseguita;
	 * se la mossa ha portato alla vittoria allora setta lo stato fine.
	 * @param pos Posizione aggiunta alla mossa
	 */
	public boolean move(Posizione pos){
		boolean res = false;
				
		if(pos!=null && mod.getStatoFine()==0){
			res = muovi.addPos(pos);
			//Controllo lo stato di fine partita
			mod.setStatoFine(controlStato(mod));
			update();
		}
		
		//Devo fare muovere l'AI
		if(mod.getGiocatoreTurno().equals(cpu.getGiocatore()) && res && mod.getStatoFine()==0){
			cpu.move();
		}
		
		return res;
	}
	
	/**
	 * Funzione che esegue una mossa
	 * @param m Mossa da eseguire
	 * @param visualizza parametro che indica se deve essere aggiornata la view all'esecuzione della mossa
	 */
	public boolean move(Mossa m, boolean visualizza){
		if(m!=null){
			System.out.println("La mossa che devo eseguire è: "+m);
			new Execute(mod, m);
			//Controllo lo stato di fine partita
			mod.setStatoFine(controlStato(mod));
			if(visualizza) update();
			return true;
		}
		return false;
	}
	
	//Funzione che associa alla parita uno stato di fine
	private int controlStato(Model mod){
					
		if(mod.getComputer().getNumPezzi() == 0 || (mod.getDamiera().existWhiteForest() && !mod.getDamiera().existBlackForest()))
			return 1; //Ha vinto l'Utente
		else if(mod.getUtente().getNumPezzi() == 0 || (!mod.getDamiera().existWhiteForest() && mod.getDamiera().existBlackForest()))
			return 2; //Ha vinto il Computer
		else if((!mod.getDamiera().existWhiteForest() && !mod.getDamiera().existBlackForest()) 
					|| mod.getNumMCNE() == numMaxMosse
					|| (mod.getComputer().getNumPezzi()==1 && mod.getUtente().getNumPezzi()==1))
			return 3; //La partita è patta
		
		return 0;
	}

}
