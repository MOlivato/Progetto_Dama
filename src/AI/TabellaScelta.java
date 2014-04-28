package AI;

import Model.Mossa;
/**
 * Classe della tabella di scelta utilizzata dall'AI
 * per valutare le possibili mosse e selezionare le mosse ottime
 * @author molivato
 */
public class TabellaScelta {
	private DescrittoreMossa[] desmosse;
	private int index;
	
	public TabellaScelta(){
		desmosse = new DescrittoreMossa[1];
		index = 0;
	}
	
	public TabellaScelta(int dim){
		desmosse = new DescrittoreMossa[dim];
		index = 0;
	}
	
	/**
	 * Funzione che aggiunge un descrittore mossa in fondo alla tabella
	 * @param dm descrittore mossa da aggiungere
	 */
	public void addDescrittoreMossa(DescrittoreMossa dm){
		
		if(desmosse.length==index)
			//Il vettore è esaurito lo ricreo raddoppiato
			desmosse = upgradeVett(desmosse);
			
		//Aggiungo il nuovo descrittore e aumento l'indice
		desmosse[index] = dm;
		++index;
	}
	
	//Aumento il vettore delle descrizioni raddoppiandone la dimensione
	private DescrittoreMossa[] upgradeVett(DescrittoreMossa[] dm){
		DescrittoreMossa[] res = new DescrittoreMossa[dm.length*2];
		
		//Copio gli elementi nel nuovo array
		for(int i=0;i<dm.length;i++)
			res[i] = dm[i];
		
		return res;
	}
	
	/**
	 * Funzione che elimina dalla tabella di scelta i descrittori mossa a NULL
	 */
	public void cleanTab(){
		//Trovo il numero reale di elmenti nel vettore
		int num=0;
		
		//Trovo il nmero di descrittori mossa corretto
		for(int i=0;i<desmosse.length;i++)
			if(desmosse[i]!=null)
				++num;
				
		//Creo il vettore ben dimensionato
		DescrittoreMossa[] res = new DescrittoreMossa[num];
		
		int c=0;
		//Copio gli elementi nel nuovo array
		for(int i=0;i<desmosse.length;i++)
			if(desmosse[i]!=null){
				res[c] = desmosse[i];
				++c;
			}
		
		//aggiorno il vettore della mossa
		desmosse=res;
	}
	
	/**
	 * Ritorna la lunghezza della tabella di scelta
	 * (ovvero il numero di descrittori mossa presenti)
	 */
	public int getLength(){
		return index;
	}
	
	/**
	 * Funzione che ritorna il descrittore mossa ad un dato
	 * indice nella tabella
	 * @param pos posizione all'interno della tabella
	 */
	public DescrittoreMossa getDescrittoreMossa(int pos){
		return desmosse[pos];
	}
	
	/**
	 * Funzione che ritorna l'ultimo descrittore mossa della tabella
	 */
	public DescrittoreMossa getLastDM(){
		return desmosse[index-1]!=null?desmosse[index-1]:null;
	}
	
	/**
	 * Funzione che ritorna il valore della mossa che genera l'alberomosse
	 * con il minor numero di pezzi mangiati nell'avversario
	 */
	public int getMossaMinVMax(){
		int vmin = 12;
		
		if(desmosse!=null)
		//Trovo il minimo tra i vmax
			for(DescrittoreMossa dm : desmosse)
				if(dm!=null  && dm.getVmaxEnemyForest() < vmin)
					vmin = dm.getVmaxEnemyForest();
		
		//Restituisco il vmin se è valido
		return vmin!=12?vmin:-1;
	}
	
	/**
	 * Funzione che ritorna il valore della mossa che genera l'alberomosse
	 * con il minor numero di dame mangiate nell'avversario
	 */
	public int getMossaMinNDMax(){
		int ndmin = 12;
		
		if(desmosse!=null)
		//Trovo il minimo tra i vmax
			for(DescrittoreMossa dm : desmosse)
				if(dm!=null && dm.getNdmaxEnemyForest()<ndmin)
					ndmin = dm.getNdmaxEnemyForest();
		
		//Restituisco il vmin se è valido
		return ndmin!=12?ndmin:-1;
	}
	
	/**
	 * Funzione che ritorna il numero minimo di alberi generati nel nemico
	 * tra tutte le mosse della tabella
	 */
	public int getMossaMinNEF(){
		int nefmin = 12;
		
		if(desmosse!=null)
		//Trovo il minimo tra i vmax
			for(DescrittoreMossa dm : desmosse)
				if(dm!=null  && dm.getNumEF() < nefmin)
					nefmin = dm.getNumEF();
		
		//Restituisco il vmin se è valido
		return nefmin!=12?nefmin:-1;
	}
	
	/**
	 * Funzione che ritorna il valore della mossa che genera l'alberomosse
	 * con il minor numero di dame mangiate nell'avversario
	 */
	public int getMossaMinAvvicinando(){
		int minAvv = 16;
		
		if(desmosse!=null)
		//Trovo il minimo tra i vmax
			for(DescrittoreMossa dm : desmosse)
				if(dm!=null && dm.getAvvicinando()<minAvv && dm.getAvvicinando()!= -1)
					minAvv = dm.getAvvicinando();
		
		//Restituisco il vmin se è valido
		return minAvv!=16?minAvv:-1;
	}
	
	/**
	 * Funzione che restituisce una mossa scelta casualmente
	 * all'interno della tabella di scelta
	 */
	public Mossa getMossaCasuale(){
		return desmosse[(int)(Math.random()*desmosse.length)].getMossa();
	}
	
	/**
	 * Funzione che pulisce la tabella da mosse che non mi fanno vincere
	 * se esiste almeno una mossa che mi fa vincere
	 */
	public void cleanNotWin(){
		boolean win = false;
		
		//Controllo se c'è una mossa che mi fa vincere
		for(int i=0;i<desmosse.length;i++)
			if(desmosse[i]!=null)
				if(desmosse[i].isWinner()){
					win = true;
					break;
				}
		
		System.out.println("Variabile che controlla se ci sono vincite: "+win);
		
		//Se c'è una mossa che mi fa vincere cancello le altre
		if(win)
			for(int i=0;i<desmosse.length;i++)
				if(desmosse[i]!=null)
					if(!desmosse[i].isWinner())
						desmosse[i]=null;		
	}
	
	/**
	 * Funzione che pulisce la tabella da mosse che non mi trasformano in dama
	 * se esiste almeno una mossa che mi trasforma in dama
	 */
	public void cleanNotChangedToDama(){
		boolean ctd = false;
			
		//Controllo se c'è una mossa che mi fa vincere
		for(int i=0;i<desmosse.length;i++)
			if(desmosse[i]!=null)
				if(desmosse[i].isChangedToDama()){
					ctd = true;
					break;
				}
		
		
		System.out.println("Variabile che controlla se ci sono mossa che trasformano in dama: "+ctd);
		
		
		//Se c'è una mossa che mi fa trasformare in dama
		if(ctd)
			for(int i=0;i<desmosse.length;i++)
				if(desmosse[i]!=null)
					if(!desmosse[i].isChangedToDama())
						desmosse[i]=null;		
	}
	
	/**
	 * Funzione che pulisce la tabella da mosse che non fanno avvicinare una mia dama all'avversario
	 * se ho dame e se mi posso avvicinare al nemico
	 */
	public void cleanNotDamaAvvicinando(){
		//Se qualche dama si può avvicinare
		//Elimino le mosse che spostano dame lontano da pedine o dame nemiche
		if(haveSomeDamaAvvicinando()){
			//Recupero il valore minimo di avvicinamento
			int min = this.getMossaMinAvvicinando();
			
			for(int i=0;i<desmosse.length;i++)
				if(desmosse[i]!=null && desmosse[i].isDama() && desmosse[i].getAvvicinando() != min)
					desmosse[i]=null;
		}
	}
	
	/**
	 * Funzione che pulisce la tabella da mosse che non generano il minimo valore possibile
	 * nella foresta di alberi mosse nemica
	 * @param vmin valore minimo generato nella foresta nemica
	 */
	public void cleanValueTabella(int vmin){
			
		//Elimino i descrittori mossa e quindi le mosse che non sono le migliori
		// facendo riferimento ai valori degli alberi generati nella foresta nemica
		for(int i=0;i<desmosse.length;i++)
			if(desmosse[i]!=null)
				if(desmosse[i].getVmaxEnemyForest()> vmin )
					desmosse[i] = null;
	}
	
	/**
	 * Funzione che pulisce la tabella da mosse che non generano il minimo valore possibile
	 * di dame manigate nella foresta di alberi mosse nemica
	 * @param ndmin valore minimo di dame mangiate generato nella foresta nemica
	 */
	public void cleanNDValueTabella(int ndmin){
		
		//Elimino i descrittori mossa e quindi le mosse che non sono le migliori
		// facendo riferimento ai valori dei sottoalberi generati nella foresta nemica
		for(int i=0;i<desmosse.length;i++)
			if(desmosse[i]!=null)
				if(desmosse[i].getNdmaxEnemyForest()> ndmin)
					desmosse[i] = null;
	}
	
	/**
	 * Funzione che pulisce latabella da mosse che non generano il valore minimo di alberi
	 * nella foresta nemica
	 * @param nefmin valore minimo di alberi nella foresta nemica
	 */
	public void cleanNumEF(int nefmin){
		
		for(int i=0;i<desmosse.length;i++)
			if(desmosse[i]!=null)
				if(desmosse[i].getNumEF()> nefmin)
					desmosse[i] = null;
	}
	
	/**
	 * Funzione che pulisce la tabella togliendo le mosse
	 * che fanno muovere i pezzi più erretrati
	 */
	public void cleanMinRowPedinaTab(){
		int max=0;
		
		//Recupero la riga dei pezzi più avanzati
		for(int i=0;i<desmosse.length;i++)
			if(desmosse[i]!=null && desmosse[i].getMossa().getStartPos().getRow()>max)
				max = desmosse[i].getMossa().getStartPos().getRow();
		
		System.out.println("Il massimo valore della riga da poter muovere è: "+max);
		
		//Elimino le mosse che spostano pezzi nelle retrovie
		for(int i=0;i<desmosse.length;i++)
			if(desmosse[i]!=null && desmosse[i].getMossa().getStartPos().getRow() != max && !desmosse[i].isDama())
					desmosse[i]=null;
	}
	
	/**
	 * Funzione che pulisce la tabella togliendo le mosse
	 * che fanno muovere i pezzi più avanzati
	 */
	public void cleanMaxRowPedinaTab(){
		int min=12;
		
		//Recupero la riga dei pezzi più avanzati
		for(int i=0;i<desmosse.length;i++)
			if(desmosse[i]!=null && desmosse[i].getMossa().getStartPos().getRow()<min)
					min = desmosse[i].getMossa().getStartPos().getRow();
		
		System.out.println("Il minimo valore della riga da poter muovere è: "+min);
		
		//Elimino le mosse che spostano pezzi nelle retrovie
		for(int i=0;i<desmosse.length;i++)
			if(desmosse[i]!=null && desmosse[i].getMossa().getStartPos().getRow()!=min && !desmosse[i].isDama())
					desmosse[i]=null;
	}
	
	//Funzione che restituisce true se ho una dama in campo
	private boolean haveSomeDamaAvvicinando(){
		for(int i=0;i<desmosse.length;i++)
			if(desmosse[i]!=null && desmosse[i].isDama() && desmosse[i].getAvvicinando()!=-1)
				return true;
		return false;
	}
	
	/**
	 * Funzione che ritorna true se posseggo qualche dama
	 */
	public boolean haveSomeDama(){
		for(int i=0;i<desmosse.length;i++)
			if(desmosse[i]!=null && desmosse[i].isDama())
				return true;
		return false;
	}
	
	public String toString(){
		String res = "";
		for(DescrittoreMossa dm: desmosse)
			res += "\n Descrittore mossa:"+dm;
		return res;
	}
	
}
