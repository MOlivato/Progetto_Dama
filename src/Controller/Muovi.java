package Controller;

import Model.AlberoMosse;
import Model.Model;
import Model.Mossa;
import Model.Posizione;

/**
 * Classe che permette all'utente di effettuare una mossa.
 * Ha il compito di costruire progressivamente le mossa dell'utente
 * evidenziando i percorsi possibili all'utente in modo progressivo.
 * Una volta finita la mossa questa e' eseguita.
 * @author molivato
 *
 */
public class Muovi {
	private Model mod;
	private AlberoMosse am;
	private Mossa mossa;

	/**
	 * Funzione che inizializza il moviemento dell'utente
	 * @param m
	 */
	public Muovi(Model m){
		mod = m;
		mossa = new Mossa();
	}
	
	/**
	 * Funzione che resetta la mossa permettendo la creazione di una nuova mossa
	 */
	public void reset(){
		mossa = new Mossa();		
	}
	
	/**
	 * Funzione che aggiunge alla mossa che sta costruendo l'utente le posizioni.
	 * Qunado si aggiunge la posizione finale di un ramo dell'albero mosse del pezzo
	 * allora la mossa e' considerata terminata e sara' eseguita.
	 * @param pos Posizione scelta dall'utente da aggiungere alla mossa da effettuare
	 */
	public boolean addPos(Posizione pos){
		
		//Se la posizione corrisponde ad una casella evidenziata allora posso muovermi
		if(mod.getDamiera().getCasella(pos).isEvidenziata()){
			
			//Recupero l'albero del pezzo
			if(mossa.isEmpty()){
				am = mod.getDamiera().getPezzo(pos).getAlberoMosse();
				//Se la posizione appartiene ad una casella evidenziata allora è un pezzo movibile
				mossa.addNewPos(pos);
				mod.getDamiera().getCasella(pos).setClicked(true);
			
			}else if(mod.getDamiera().isFreePos(pos) && !mossa.getLastPos().equals(pos)){
				for(int i=0;i<am.getLengthSAM();i++)
					if(am.getSAM(i)!=null && !am.getSAM(i).getPosizione().equals(pos))
						mod.getDamiera().getCasella(am.getSAM(i).getPosizione()).setEvidenziata(false);
				
				am = am.getSAM(pos);
				//Se la posizione appartiene ad una casella evidenziata allora è un pezzo movibile
				mossa.addNewPos(pos);
				mod.getDamiera().getCasella(pos).setClicked(true);
				
			}else if(!mossa.getPosMossa(0).equals(pos) && !mod.getDamiera().isFreePos(pos)){
			
				System.out.println("Hai premuto su un'altro pezzo movibile!!!");
				//Disevidenzio l'albero mosse del pezzo precedente
				new EvidenziaAM().recDisevidSAM(mod, mod.getDamiera().getPezzo(mossa.getPosMossa(0)).getAlberoMosse(), false);
				reset();
				return addPos(pos);
				
			}else{
				return false;
			}
						
			System.out.println(""+am);
			
			//Ho finito di comporre la mossa e devo eseguirla
			if(am.isSAMNull()){
				System.out.println("Eseguo la mossa...");
				new Execute(mod, mossa);
				reset();
				return true;
			}else{
				//Evidenzio il sottoalbero successivo
				for(int i=0;i<am.getLengthSAM();i++){
					if(am.getSAM(i) != null){
						System.out.println(""+am);
						if(am.getSAM(i).isSAMNull())
							new EvidenziaAM(mod, am.getSAM(i));
						else
							new EvidenziaAM().EvidenziaSAM(mod, am.getSAM(i));
					}
				}
			}
			
		}
		return false;
	}
	
}
