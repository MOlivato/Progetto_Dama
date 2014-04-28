package Controller;

import Model.AlberoMosse;
import Model.Model;
import Model.Posizione;
/**
 * Classe usata per popolare il model di una nuova foresta di albero mosse
 * Aggiorna la vecchia foresta di tutti e due i giocatori
 * @author molivato
 *
 */
public class CreaFAM {
	//Posizione reale del pezzo quindi radice dell'albero delle mangiate
	private Posizione radix;
	private boolean loop;
	
	/**
	 * Funzione che dato un model crea la foresta di alberi mosse per tutti e due i giocatori 
	 * @param mod Model utilizzato nella creazione della foresta
	 */
	//Questa classe crea l'albero delle mosse di una pedina
	public CreaFAM(Model mod){
		loop = false;
		//Pulisco la damiera dai vecchi alberi
		mod.getDamiera().clearForest();
		
		//Scorro tutte le pedine del colore del turno e genero il loro albero delle mosse
		for(int i=0;i<mod.getDamiera().getDim();i++){
			for(int j=0;j<mod.getDamiera().getDim();j++){
				if(!mod.getDamiera().isFreePos(new Posizione(i,j))){
					//Devo creare l'albero delle mosse per il pezzo
					AlberoMosse nam = Crea(mod, new Posizione(i,j), -1, -1);
					//Pianto nel pezzo il nuovo albero delle mosse
					mod.getDamiera().getPezzo(new Posizione(i,j)).setAlberoMosse(nam);
					
				}
			}
		}
	}
	
	//indexStart indica nel caso eating==true d dove sto arrivando per calcolare la mangiata
	// ovvero da da,sa,di,si per evitare di tornare indietro nel caso avessi una dama
	//indexStart=-1 se non sono in una mangiata ricorsiva
	private AlberoMosse Crea(Model mod, Posizione miapos, int indexPrec, int indexRad){
		AlberoMosse res = new AlberoMosse(miapos);
		
		//System.out.println("La posizione da analizzare è:  "+miapos);
		//System.out.println("Miapos: "+miapos+" IndexPrec: "+indexPrec);
		
		//Se la posizione è la radice equivale alla radice dell'albero
		if(indexPrec==-1) radix = miapos;		
		
		//Posizioni diagonali limitrofe a distanza +-1
		Posizione[] adiacenti = getDiag(mod, miapos,1);
		//Posizioni diagonali a distanza +-2 riferite alla mangiata
		Posizione[] successive = getDiag(mod, miapos,2);
		
		//Controllo per ogni posizione adiacente se ho le condizioni per attuare una mossa
		//In caso contrario considero la posizione adiacente non valida ovvero == null
		clearUnmovableVPos(mod, adiacenti, successive, indexPrec, indexRad);
				
		if(!isVPosNull(adiacenti)){
			for(int i=0;i<adiacenti.length;i++){
				if(adiacenti[i]!=null){
					if(mod.getDamiera().isFreePos(adiacenti[i])){
						 if(indexPrec==-1){
							 //Posso spostarmi in quella posizione
							 //System.out.println("Posso solo spostarmi in posizione "+adiacenti[i]);
							 res.setSAM(new AlberoMosse(adiacenti[i]), i);
						 }
					}else{
						//Posso mangiare in quella posizione
						//System.out.println("Posso mangiare in posizione "+adiacenti[i]+" arrivando in"+successive[i]);
						AlberoMosse temp = Crea(mod, successive[i], i, (indexRad==-1?i:indexRad));
						if(mod.getDamiera().getPezzo(adiacenti[i]).isDama()){
							//System.out.println("Sto mangiando una dama!");
							temp.increaseNumDameEated();
						}
						
						res.setSAM(temp, i);
					}	
				}
			}
			//Se l'albero aveva un ciclo ripristino loop per l'uso in un altro albero
			loop = false;
		}
		
		//Se l'albero rappresenta l'arrivo da una mangiata aumento i suo valore di 1
		//Aumentando di 1 il valore di tutto l'albero
		if(res.isSAMNull() && indexPrec!=-1)
			res.increaseValue();
		
		return res;
	}
	
	//Trovo le posizioni sulle diagonali addiacenti alla pedina ad una distanza dist
	private Posizione[] getDiag(Model mod, Posizione pos, int dist){
		Posizione[] diag = new Posizione[4];
		//System.out.println("La posizione radice è: "+radix);
		//System.out.println("Analizzo le diagonali della posizione "+pos+" a distanza"+dist);
		
		int deltarow = dist*(isFromNorth(mod,radix)?+1:-1);
		
		//Destra avanti
		diag[0] = new Posizione(pos.getRow()+deltarow, pos.getCol()+dist);
		//Sinistra avanti
		diag[1] = new Posizione(pos.getRow()+deltarow, pos.getCol()-dist);
		//Se è una dama faccio anche quelle all'indietro
		if(mod.getDamiera().getPezzo(radix).isDama()){
			//Destra indietro
			diag[2] = new Posizione(pos.getRow()-deltarow, pos.getCol()+dist);
			//Sinistra indietro
			diag[3] = new Posizione(pos.getRow()-deltarow, pos.getCol()-dist);
		}
		
		//Elimino le posizioni esterne alla damiera quindi non valide
		clearUnvalidPos(mod, diag);
		
		return diag;
	}
		
	//Elimino le posizioni esterne alla damiera
	private void clearUnvalidPos(Model mod, Posizione[] poses){
		for(int i=0;i<poses.length;i++)
				//Elimino le posizioni esterne alla damiera
				if(poses[i]!=null && !mod.getDamiera().isValidPos(poses[i])) poses[i]=null;
	}
	
	//Elimino le posizioni che non possono generare una mossa valida per quel pezzo
	private void clearUnmovableVPos(Model mod, Posizione[] adpos, Posizione[] sucpos, int indexPrec, int indexRad){
		
		//System.out.println("Controllo se è fattibile una mossa...");
		for(int i=0;i<adpos.length;i++)
			if(adpos[i]!=null && !mod.getDamiera().isFreePos(adpos[i])){
				
				//Elimino le posizioni di tipo mangiata se io sono pedina e lei è dama
				if(mod.getDamiera().getPezzo(radix).isPedina() && mod.getDamiera().getPezzo(adpos[i]).isDama()){
					adpos[i]=sucpos[i]=null;
					continue;
				}
				
				//Elimino le posizioni di tipo mangiata se siamo dello stesso colore
				if(mod.getDamiera().getPezzo(radix).getColore() == mod.getDamiera().getPezzo(adpos[i]).getColore()){
					adpos[i]=sucpos[i]=null;
					continue;
				}
				
				//Elimino le posizioni che hanno come successiva null
				if(sucpos[i]==null){
					adpos[i]=null;
					continue;
				}

				//Elimino le posizioni di tipo mangiata se la posizione di arrivo è occupata
				// e se non sto tornando in radice
				if(sucpos[i]!=null && !mod.getDamiera().isFreePos(sucpos[i]) && !radix.equals(sucpos[i])){
					adpos[i] = sucpos[i] = null;
					continue;
				}
								
				//Elimino le posizioni da cui sto arrivando nella simulazione di mangiata
				if(indexPrec+i==3){
					adpos[i]=sucpos[i]=null;
					continue;
				}
				
				//Elimino le posizioni di tipo mangiata che producono un ciclo infinito
				// in caso di mangiata ad anello di una dama che torna in radice
				if(loop) adpos[indexRad] = sucpos[indexRad] = null;
				
				//Mi ricordo che sto per andare in ciclo
				if(sucpos[i]!=null && radix.equals(sucpos[i])){	
					loop = true;
				}
				
			}
	}
	
	//Funzione che restituisce true se tutte le posizioni nel vettore sono null
	private boolean isVPosNull(Posizione[] v){
		for(Posizione p:v)	
			if(p!=null)	
				return false;
		return true;
	}
	
	//Ritorna true se il pezzo è del colore del giocatore posizionato a nord della damiera
	private boolean isFromNorth(Model mod, Posizione pos){
		//System.out.println("Sto analizzando la posizione "+pos);
		return mod.getComputer().getColore() == mod.getDamiera().getPezzo(pos).getColore();
	}
	
}
