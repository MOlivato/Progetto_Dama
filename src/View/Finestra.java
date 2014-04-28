package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Classe che rappresenta la finestra per l'interfaccia grafica.
 * @author molivato
 *
 */
public class Finestra extends JFrame{
	
	/**
	 * Funzione costruttore di una finestra standard per il gioco
	 */
	public Finestra(){
		setTitle("Progetto Dama 2014");
		setSize(750,750);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Funzione costruttore a parametri
	 * @param titolo titolo della finestra
	 * @param width larghezza della finestra
	 * @param height lunghezza della finestra
	 */
	public Finestra(String titolo, int width, int height){
		setTitle(titolo);
		setSize(width, height);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
		
	/**
	 * Funzione costruttore a parametri
	 * @param width larghezza della finestra
	 * @param height lunghezza della finestra
	 */
	public Finestra(int width, int height){
		setTitle("Progetto Dama 2014");
		setSize(width,height);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
