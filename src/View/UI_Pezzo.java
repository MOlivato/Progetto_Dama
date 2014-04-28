package View;

import javax.swing.ImageIcon;

import Model.Pezzo;

/**
 * Classe che rappresenta l'intefaccia grafica di un pezzo
 * contenuto nella damiera sia per la pedina che  per la dama
 * @author molivato
 *
 */
public class UI_Pezzo{
	private ImageIcon img;
	private Pezzo pezzo;
	
	/**
	 * Funzione costruttore dell'interfaccia grafica
	 * @param pezzo Pezzo da rappresentare
	 * @param isepic parametro che indica se il pezzo deve essere 'epico'
	 */
	public UI_Pezzo(Pezzo pezzo, boolean isepic){
		this.pezzo = pezzo;
		this.setPezzo(pezzo, isepic);
	}
	
	/**
	 * Funzione che setta il pezzo da rappresentare
	 * @param pezzo Pezzo da rappresentare
	 * @param isepic parametro che indica se il pezzo deve essere 'epico'
	 */
	public void setPezzo(Pezzo pezzo, boolean isepic){
		this.pezzo = pezzo;
		if(pezzo!=null){
			String nomeImg =(pezzo.isDama()?"dama":"pedina")+(pezzo.isBlack()?"Nera":"Bianca")+(isepic?".gif":".png");
			img = new ImageIcon("src/resources/"+nomeImg);
		}else{
			img=null;
		}
	}
	
	/**
	 * Funzione che ritorna True se non sto rappresentando nessun pezzo
	 */
	public boolean isNull(){
		return img == null;
	}
	
	/**
	 * Funzione che restituisce l'immagine corrispondente al pezzo rappresentato
	 */
	public ImageIcon getImg(){
		return img;
	}
	
	/**
	 * Funzione che ritorna il pezzo da rappresentato
	 */
	public Pezzo getPezzo(){
		return pezzo;
	}
	
	//Trasformo le informazioni in una stringa
	public String toString(){
		return ""+(!isNull()?img.toString().split("/")[2]:null);
	}
}
