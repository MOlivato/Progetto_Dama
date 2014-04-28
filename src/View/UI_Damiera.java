package View;

import Controller.Controller;
import Model.Damiera;
import Model.Posizione;

import java.awt.GridLayout;

import javax.swing.JPanel;
/**
 * Classe che rappresenta l'interfaccia grafica della Damiera.
 * @author molivato
 *
 */
public class UI_Damiera extends JPanel {
	private UI_Casella[][] damiera;
	
	/**
	 * Funzione costruttore dell'interfaccia grafica
	 * @param dm Damiera di riferimento da rappresentare graficamente
	 * @param ctrl Controller usato per l' iterazione con la Damiera
	 */
	public UI_Damiera(Damiera dm, Controller ctrl){
		setLayout(new GridLayout(8,8));
		damiera = new UI_Casella[8][8];
		
		//Aggiungo le caselle
		for(int i=0;i<dm.getDim();i++){
			for(int j=0;j<dm.getDim();j++){
				//Aggiungo la casella alla damiera
				damiera[i][j] = new UI_Casella(dm.getCasella(new Posizione(i,j)), ctrl);
				add(damiera[i][j]);
			}
		}
	}
	
	/**
	 * Funzione che aggiorna l'interfaccia grafica della Damiera
	 */
	public void update_UI(){
		//Aggiungo le caselle
		for(int i=0;i<damiera.length;i++){
			for(int j=0;j<damiera.length;j++){
				//Aggiungo la casella alla damiera
				damiera[i][j].update_UI();
			}
		}
	}
}
