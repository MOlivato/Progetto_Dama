package Main;

import javax.swing.JOptionPane;

import AI.AI;
import Controller.Controller;
import Model.Model;
import View.View;

/**
 * Classe che contiene la funzione Main
 * responsabile dell'esecuzione del programma
 * @author molivato
 */
public class Main {
	
	/**
	 * Funzione che esegue il progetto della Dama Italiana
	 * @param args
	 */
	public static void main(String[] args) {
		//Creo il Model del gioco
		Model mod = new Model();
		//Creo il controllore del gioco collegandolo al nuovo model
		Controller ctrl = new Controller(mod);
		//Eseguo il setup del model
		ctrl.setup();
		//Faccio inserire il nome all'utente
		mod.getUtente().setNome(JOptionPane.showInputDialog(null,"Inserire il nome dell' Utente: ", "Sconosciuto"));
		
		//Creo la vista del nuovo gioco
		View vista = new View(mod, ctrl);
		
		//Creo l'inteligenza artificiale del nuovo gioco
		AI cpu = new AI(mod, ctrl, mod.getComputer());
		
		//Setto nel controllore la vista a cui e' associato
		ctrl.setView(vista);
		//Setto nel controllore l'AI a cui e' associato
		ctrl.setAI(cpu);
	}

}
