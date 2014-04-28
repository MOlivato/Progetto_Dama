package Controller;

import java.awt.Color;

import Model.Giocatore;
import Model.Model;
import Model.Pezzo;
import Model.Posizione;
/**
 * Classe che esegue il Setup del Model secondo le regole della Dama Italiana
 * @author molivato
 *
 */
public class Setup {
	
	/**
	 * Classe che posiziona le pedine sulla Damiera
	 * e aggiorna lo stato del Model
	 * @param mod Model di riferimento del gioco di cui eseguire il setup
	 */
	public Setup(Model mod){
		System.out.println("Sto facendo il setup del model...");
		//1)Faccio il setup del campo 
		Init(mod);
		
		//2)Aggiorno lo stato del Model[AlberiMosse, Caselle evidenziate]
		new Update(mod);
		System.out.println("Finito il setup del model...");
		
	}
	
	//Inizializzo il campo
	private void Init(Model mod){
		//Pulisco la damiera da vecchi pezzi
		mod.getDamiera().clear();
		//Setto i giocatori
		mod.setUtente(new Giocatore("Utente",12,Color.WHITE));
		mod.setComputer(new Giocatore("Computer",12,Color.BLACK));
		
		int dim = mod.getDamiera().getDim();	
		//Inserisco le pedine del colore opportuno nella posizione opportuna
		for(int i=0;i<3;i++)
			for(int j=0;j<dim;j++)
				if(mod.getDamiera().isBlackPos(new Posizione(i,j)) && (i+j<10)){
					mod.getDamiera().setPezzo(new Posizione(i,j),new Pezzo(mod.getComputer().getColore()));
					mod.getDamiera().setPezzo(new Posizione(dim-1-i,dim-1-j),new Pezzo(mod.getUtente().getColore()));
				}
		
		
		/*		
		//mod.getDamiera().setPezzo(new Posizione(1,3),new Pezzo(Color.BLACK));
		mod.getDamiera().setPezzo(new Posizione(3,1),new Pezzo(Color.BLACK));
		mod.getDamiera().getPezzo(new Posizione(3,1)).changeToDama();
		mod.getDamiera().setPezzo(new Posizione(0,0),new Pezzo(Color.BLACK));
		//mod.getDamiera().getPezzo(new Posizione(7,5)).changeToDama();
		mod.getDamiera().setPezzo(new Posizione(2,6),new Pezzo(Color.BLACK));
		mod.getDamiera().getPezzo(new Posizione(2,6)).changeToDama();
		
		mod.getDamiera().setPezzo(new Posizione(6,2),new Pezzo(Color.BLACK));
		//mod.getDamiera().getPezzo(new Posizione(6,2)).changeToDama();
		mod.getDamiera().setPezzo(new Posizione(4,4),new Pezzo(Color.BLACK));
		//mod.getDamiera().getPezzo(new Posizione(4,4)).changeToDama();
		mod.getDamiera().setPezzo(new Posizione(2,2),new Pezzo(Color.BLACK));
		mod.getDamiera().getPezzo(new Posizione(2,2)).changeToDama();
		
		//mod.getDamiera().setPezzo(new Posizione(5,1),new Pezzo(Color.WHITE));
		
		mod.getDamiera().setPezzo(new Posizione(4,4),new Pezzo(Color.WHITE));
		mod.getDamiera().getPezzo(new Posizione(4,4)).changeToDama();
		 
		//mod.getDamiera().setPezzo(new Posizione(7,5),new Pezzo(Color.WHITE));
		*/
	}
	
}
