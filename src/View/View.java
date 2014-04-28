package View;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;

import Controller.Controller;
import Model.Model;

/**
 * Classe che costituisce la View per il gioco della Dama Italiana.
 * Permette di visualizzare graficamente il gioco e i giocatore.
 * Inoltre permette all'utente di interagire con il gioco altraverso il mouse.
 * @author molivato
 *
 */
public class View {
	private Finestra f;
	private UI_Damiera damiera;
	private UI_Giocatore utente;
	private UI_Giocatore computer;
	private Model mod;
	private Controller ctrl;
	
	/**
	 * Funzione costruttore
	 * @param m Model di riferimento da usare nel disegnare il gioco
	 * @param c Controller di riferimento da usare nell'iterazione tra l'utente e il gioco
	 */
	public View(Model m, Controller c){
		//Creo la finestra del gioco
		f = new Finestra("Progetto Dama 2014",1024,768);
		
		mod = m;
		ctrl = c;
		//Setto la interfaccia grafica della damiera
		damiera = new UI_Damiera(mod.getDamiera(), ctrl);
		
		//Setto le interfaccie grafiche dei giocatori
		utente = new UI_Giocatore(mod.getUtente(), ctrl.isEpic());
		computer = new UI_Giocatore(mod.getComputer(), ctrl.isEpic());
		
		//Aggiungo le interfacce grafiche alla finestra per essere visualizzate
		f.add(damiera,BorderLayout.CENTER);
		f.add(computer, BorderLayout.WEST);
		f.add(utente, BorderLayout.EAST);
		f.setVisible(true);
	}
	
	/**
	 * Funzione che aggiorna la View a partire dallo stato del Model
	 */
	public void update(){
		System.out.println("Sto aggiornando view della damiera...");
		damiera.update_UI();
		utente.update_UI();
		computer.update_UI();
		
		//Aggiorno effettivamente la damiera
		f.repaint();
		if(mod.getStatoFine()!=0){
			if(mod.getStatoFine()==1){
				JOptionPane.showMessageDialog(null, "PARTITA FINITA! HA VINTO L'UTENTE!!");
			}else if(mod.getStatoFine()==2){
				JOptionPane.showMessageDialog(null, "PARTITA FINITA! HA VINTO IL COMPUTER!!");
			}else if(mod.getStatoFine()==3){
				JOptionPane.showMessageDialog(null, "PARTITA FINITA! PARTITA PATTA!!");
			}
		}
				
		System.out.println("Finito di aggiornare view della damiera...");
	}
	
}
