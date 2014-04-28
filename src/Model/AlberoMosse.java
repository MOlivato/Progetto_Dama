package Model;

/**
 * Classe che rappresenta l'albero delle possibili posizioni
 * raggiungibili nelle mosse di un pezzo. Per questo e' chiamata AlberoMosse
 * @author molivato
 *
 */
public class AlberoMosse{
	private Posizione pos;
	private AlberoMosse[] am;
	private int valore;
	private int numDameEated;
	
	/**
	 * Funzione che costruisce un AlberoMosse di un pezzo immobile
	 * @param p Posizione del pezzo immobile
	 */
	public AlberoMosse(Posizione p){
		pos = p;
		am = null;
		valore = 0;
		numDameEated = 0;
	}
		
	/**
	 * Funzione che setta i rami di un AlberoMosse
	 * indicando la posizione di innesto del ramo
	 * @param oam AlberoMosse da innestare come ramo
	 * @param i Posizione del ramo che deve essere innestato
	 */
	public void setSAM(AlberoMosse oam, int i){
		if(am==null) am = new AlberoMosse[4];
		//Inserisco l'albero
		am[i] = oam;
		//Aggiorno il valore del mio albero comparandolo con l'albero appena inserito
		if(am[i]!=null && am[i].getValue()>valore-1) valore = am[i].getValue()+1;
		//Aggiorno il numero di dame che posso mangiare
		if(am[i]!=null && am[i].getNumDameEated()>numDameEated) numDameEated = am[i].getNumDameEated();
	}
	
	/**
	 * Funzione che incrementa di 1 il valore dell'albero
	 */
	public void increaseValue(){
		++valore;
	}
	
	/**
	 * Funzione che incrementa di 1 il valore del numero di dame mangiate dall'albero
	 */
	public void increaseNumDameEated(){
		++numDameEated;
	}
	
	/**
	 * Funzione costruttore di copia profonda dell'AlberoMosse
	 * @param other AlberoMosse da copiare usando la copia profonda
	 */
	public AlberoMosse(AlberoMosse other){
		pos = new Posizione(other.getPosizione());
		valore = other.getValue();
		numDameEated = other.getNumDameEated();
		if(!other.isSAMNull()){
			am = new AlberoMosse[4];
			for(int i=0;i<am.length;i++)
				if(am[i]!=null)
					am[i] = new AlberoMosse(other.getSAM(i));
		}
	}
	
	/**
	 * Funzione che ritorna il ramo dell'AlberoMosse ad indice i
	 * @param i indice del sottoAlberoMosse da restituire
	 */
	public AlberoMosse getSAM(int i){
		return am[i];
	}
	
	/**
	 * Funzione che ritorna il sottoAlberoMosse che ha in radice
	 * una posizione pos
	 * @param pos Posizione da ricercare nei sottoAlberoMosse
	 */
	public AlberoMosse getSAM(Posizione pos){
		for(int i=0;i<am.length;i++)
			if(am[i]!=null && am[i].getPosizione().equals(pos))
				return am[i];
		
		return null;			
	}
	
	/**
	 * Funzione che ritorna il peso dell'albero
	 * (riferito al numero di mangiate possibili)
	 */
	public int getValue(){
		return valore;
	}
	
	/**
	 * Funzione che ritorna il numero di dame possibilmente mangiabili
	 */
	public int getNumDameEated(){
		return numDameEated;
	}
	
	/**
	 * Funzione che ritorna la Posizione in cui e' radicato l'AlberoMosse
	 */
	public Posizione getPosizione(){
		return pos;
	}
	
	/**
	 * Funzione che ritorna il valore massimo di dame mangiate
	 * tra i sottoAlberoMosse ovvero nei rami dell'albero
	 */
	private int getMaxDameEated(){
		int max=0;
		
		for(AlberoMosse i:am)
			if(i!=null && i.getNumDameEated()>max)
				max = i.getNumDameEated();
		return max;
	}
	
	/**
	 * Ritorna la lunghezza dei possibili idici dei sottoAlberoMosse
	 * ovvero il numero dei possibili rami
	 */
	public int getLengthSAM(){
		return isSAMNull()?0:4;
	}
	
	/**
	 * Funzione che ritorna True se l'AlberoMosse non contiene rami
	 * ovvero non contiene sottoAlberoMosse
	 */
	public boolean isSAMNull(){
		return am==null;
	}
	
	/**
	 * Funzione che elimina ricorsivamente tutti i rami dell'AlberoMosse
	 * con valore non massimo o numero dame mangiate non massimo
	 */
	public void clean(){
		if(!isSAMNull())
			for(int i=0;i<am.length;i++)
				if(am[i]!=null)
					//Elimino l'albero se il suo valore non  massimo o non mangio abbastanza dame
					if(am[i].getValue()!=valore-1 || am[i].getNumDameEated()<getMaxDameEated())
						am[i]=null;
					else
						am[i].clean();
	}
	
	//Rendo l'albero mosse una stringa
	public String toString(){
		String res = "";
		
		res += "valore: "+valore+" "+pos+" -- "+(am!=null?am[0]+" -- "+am[1]+" -- "+am[2]+" -- "+am[3]:"");
		
		return res;
	}
	
}
