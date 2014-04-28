package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Giocatore;
import Model.Pezzo;

/**
 * Classe che rappresenta l'interfaccia grafica del Giocatore 
 * durante la partita.
 * @author molivato
 *
 */
public class UI_Giocatore extends JPanel{
	private Giocatore giocatore;
	private JButton img;
	private UI_Pezzo uip;
	private JLabel numpezzi;
	private JLabel nome;
	
	/**
	 * Funzione costruttore dell'interfaccia grafica
	 * @param g Giocatore da rappresentare graficamente
	 * @param isEpic parametro che mi indica se il gioco e' in modalita' epica
	 */
	public UI_Giocatore(Giocatore g, final boolean isEpic){
		//3 righe e una colonna
		setLayout(new GridLayout(3,1));
		giocatore = g;
		uip = new UI_Pezzo(new Pezzo(giocatore.getColore()), isEpic);
		
		img = new JButton();
		img.setIcon(uip.getImg());
		img.setFocusable(false);
		//Aggiungo l'alscoltatore al bottone
		img.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//Cambio le icone del bottone
				if(uip.getPezzo().isPedina()){
					uip.getPezzo().changeToDama();
					uip.setPezzo(uip.getPezzo(), isEpic);
				}else{
					uip.setPezzo(new Pezzo(giocatore.getColore()), isEpic);
				}

				img.setIcon(uip.getImg());
			}	
		});
		img.setBackground(Color.GRAY);
		
		nome = new JLabel(""+g.getNome());
		numpezzi = new JLabel("#Pezzi: "+g.getNumPezzi());
		
		if(giocatore.getColore()==Color.BLACK){
			this.add(img);
			this.add(nome);
			this.add(numpezzi);
		}else{
			this.add(numpezzi);
			this.add(nome);
			this.add(img);
		}
	} 
	
	/**
	 * Funzione che aggiorna l'interfaccia grafica del Giocatore
	 */
	public void update_UI(){
		numpezzi.setText("#Pezzi: "+giocatore.getNumPezzi());
	}
	

}
