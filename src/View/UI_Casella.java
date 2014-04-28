package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Controller.Controller;
import Model.Casella;

/**
 * Classe che rappresenta l'interfaccia grafica di una Casella della Damiera.
 * @author molivato
 *
 */
public class UI_Casella extends JButton{
	private UI_Pezzo pezzo;
	private Casella casella;
	private Controller ctrl;
	
	/**
	 * Funzione costruttore dell'interfaccia grafica
	 * @param cas Casella di riferimento da rappresentare graficamente
	 * @param c Controller usato nell'iterazione con la Casella
	 */
	public UI_Casella(Casella cas, Controller c){
		//Setto l'immagine standard del bottone
		casella = cas;
		ctrl = c;
		//gli passo il pezzo della casella
		pezzo = new UI_Pezzo(casella.getPezzo(), ctrl.isEpic());
				
		this.setListener();
		this.update_UI();
	}
	
	/**
	 * Funzione che aggiorna l'interfaccia grafica della Casella
	 */
	public void update_UI(){
		//System.out.println("Sto aggiornando la singola casella...");
		//Riassegno il pezzo preso dalla casella
		pezzo.setPezzo(casella.getPezzo(), ctrl.isEpic());
		//System.out.println(pezzo.isNull()?"":"C'è un pezzo in questa casella!!");
		//Disegno il pezzo che contiene
		setIcon(pezzo.getImg());
		
		//Aggiorno il colore della casella NERO, BIANCO, VERDE(evidnziata)
		this.setBackground(casella.getColore());
		this.setFocusable(false);
		
		//System.out.println(casella.isClicked()?"La casella è stata cliccata!!":"La casella è ancora da cliccare");
		
		//Se la casella non contiene un pezzo e se la casella non è evidenziata 
		//Allora la casella è disabilitata(non posso cliccare sopra)
		
		//Abilito il click sulla casella solo se è evidenziata
		this.setEnabled(casella.isEvidenziata() || !casella.isFree()?true:false);
		
		//System.out.println("Finito di aggiornare la singola casella...");
	}
	
	//Aggiungo il Listener alla classe
	private void setListener(){
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Cambio le icone del bottone
				if(casella.isEvidenziata()){
					System.out.println("La posizione in cui vuoi muovere è: "+casella);
					ctrl.move(casella.getPosizione());
				}else{
					System.out.println("La casella non è evidenziata. Non puovi muovere da qui!");
				}
			}	
		});
	}

}
