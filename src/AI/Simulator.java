package AI;

import Controller.Controller;
import Model.Model;
import Model.Posizione;

/**
 * Classe che simula una mossa e permette di ricavere la condizione del
 * model se questa fosse eseguita
 * @author molivato
 *
 */
public class Simulator {
	Model mod;
	
	/**
	 * @param m Model di riferimento del gioco
	 */
	public Simulator(Model m){
		mod = m;
	}
	
	/**
	 * Funzione che simula la mossa e compila il descrittore mossa
	 * @param dm contiene la mossa da eseguire
	 */
	public void simulate(DescrittoreMossa dm){
		
		//Creo un model temporaneo 
		Model Mtemp = new Model(mod);
		//Creo un controller temporaneo
		Controller Ctemp = new Controller(Mtemp);
		
				
		//Controllo se la mossa fa trasformare il pezzo in una dama
		dm.setChangedToDama(isChangingToDama(dm));
		
		
		//eseguo la mossa sul campo temporaneo con un controller temporaneo
		Ctemp.move(dm.getMossa(), false);
		
		if(dm.isDama()){
			System.out.println("Sto simulando una dama!!");
			dm.setAvvicinando(getGradoVicinanza(dm.getMossa().getLastPos(), getNearEnemyPezzo(Mtemp, dm.getMossa().getStartPos(), enemyHasDama(Mtemp))));
		}
		
		
		//Se nel model risulta che ho vinto allora setto vittoria a true nel descrittore
		dm.setVittoria(Mtemp.getStatoFine() == 2?true:false);
		
		//Aggiorno Valore massimo della foresta del nemico
		// Il numero di dame mangiate dalla foresta del nemico
		//  E il numero di alberi validi nella foresta del nemico
		updateValuesDM(Mtemp, dm);
		
		System.out.println("Il descrittore mossa creato è: "+dm);
	}	
	
	/**
	 * Funzione che ritorna true se la mossa provoca il cambiamento
	 * del pezzo a cui è associata da pedina a dama
	 * @param dm contiene la mossa da controllare
	 */
	private boolean isChangingToDama(DescrittoreMossa dm){
		return mod.getDamiera().getPezzo(dm.getMossa().getStartPos()).isPedina() && mod.getDamiera().isFinishRow(dm.getMossa().getStartPos(), mod.getDamiera().getPezzo(dm.getMossa().getStartPos()).getColore());
	}
	
	/**
	 * Funzione che ritorna true se il nemico possiede dame non immobilizzate
	 * @param m Model di riferimento del gioco
	 */
	private boolean enemyHasDama(Model m){
		for(int i=0;i<m.getDamiera().getDim();i++)
			for(int j=0;j<m.getDamiera().getDim();j++)
				if(m.getDamiera().getCasella(new Posizione(i,j)).isEvidenziata() && m.getDamiera().getPezzo(new Posizione(i,j)).isDama())
					return true;
		return false;
	}
	
	/**
	 * Funzione che aggiorna i parametri del descrittore relativi a <code>vmax<code> e <code>ndmax<code>
	 * @param m Model di riferimento del gioco
	 * @param dm descrittore contenente la mossa da controllare
	 */
	private void updateValuesDM(Model m, DescrittoreMossa dm){
		//Scorro le caselle se c'e' una casella evidenziata
		// prendo il valore del suo albero e il numero di dame mangiate
		for(int i=0;i<m.getDamiera().getDim();i++)
			for(int j=0;j<m.getDamiera().getDim();j++)
				if(m.getDamiera().getCasella(new Posizione(i,j)).isEvidenziata()){
					dm.setVmaxEnemyForest(m.getDamiera().getPezzo(new Posizione(i,j)).getAlberoMosse().getValue());
					dm.setNdmaxEnemyForest(m.getDamiera().getPezzo(new Posizione(i,j)).getAlberoMosse().getNumDameEated());
		}
		//Recupero il numero di foreste valide nel nemico
		dm.setNumEF(this.getNumEnemyForest(m));
	}
	
	/**
	 * Funzione che ritorna il numero di pezzi nemici con una foresta valida
	 * @param m Model generato dalla simulazione della mossa
	 */
	private int getNumEnemyForest(Model m){
		int c=0;
		
		for(int i=0;i<m.getDamiera().getDim();i++)
			for(int j=0;j<m.getDamiera().getDim();j++)
				if(m.getDamiera().getCasella(new Posizione(i,j)).isEvidenziata())
					c++;
		
		return c;
	}
	
	/**
	 * Funzione che restituisce la posizione del più vicino pezzo nemico movibile
	 * Se il nemico ha delle dame restituisce la posizione della dama più vicina
	 * @param m Model generato dalla simulazione della mossa
	 * @param miapos parametro riferito alla posizione di partenza del pezzo nelaa mossa
	 * @param isdama parametro che vale true se devo cercare le dame 
	 */
	private Posizione getNearEnemyPezzo(Model m, Posizione miapos, boolean isdama){
		int mrow=0 , mcol=0;
		int gradoVicinanza = 12;
		//System.out.println("####HO CHIAMATO LA FUNZIONE NEARENEMY!!###");
		
		//Scansiono la damiera in cerca del pezzo nemico più vicino non immobile
		for(int i=0;i<m.getDamiera().getDim();i++){
			for(int j=0;j<m.getDamiera().getDim();j++){
				if(m.getDamiera().getCasella(new Posizione(i,j)).isEvidenziata() && m.getDamiera().getPezzo(new Posizione(i,j)).isDama() == isdama){
					System.out.println("Pos: "+i+","+j+" gradoVicinanza: "+getGradoVicinanza(miapos,new Posizione(i,j)));
					if(this.getGradoVicinanza(miapos, new Posizione(i,j)) < gradoVicinanza){
						mrow=i;
						mcol=j;
						gradoVicinanza = this.getGradoVicinanza(miapos, new Posizione(i,j));
					}
				}
			}
		}
			
		System.out.println("Il miopezzo è:"+miapos+ " <==> Il pezzo nemico più vicino è: "+mrow+","+mcol);
		//Ritorno la casella del pezzo nemico più vicino
		return new Posizione(mrow,mcol);
	}
		
	/**
	 * Funzione che restituisce il grado di vicinanza tra due pedine
	 * @param miapos posizione del pezzo da muovere
	 * @param suapos posizione del pezzo nemico
	 */
	private int getGradoVicinanza(Posizione miapos, Posizione suapos){
		return Math.abs(miapos.getRow()-suapos.getRow())+Math.abs(miapos.getCol()-suapos.getCol());
	}
	
}
