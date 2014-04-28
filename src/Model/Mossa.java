package Model;

/**
 * Classe che rappresenta una possibile mossa di un pezzo
 * all'interno della damiera. E' strutturata come un vettore di posizioni
 * che identificano il percorso preciso effettuato dalla mossa
 * @author molivato
 *
 */
public class Mossa {
	private Posizione[] mossa;
	private int index;
	
	/**
	 * Funzione costruttore
	 */
	public Mossa(){
		index = 0;
		mossa = new Posizione[1];
	}
	
	/**
	 * Funzione costruttore di copia profonda
	 * @param m Mossa da copiare in profondita'
	 */
	public Mossa(Mossa m){
		index = m.getLength();
		mossa = new Posizione[m.getLength()];
		for(int i=0;i<index;i++)
			mossa[i] = new Posizione(m.getPosMossa(i));
	}
	
	/**
	 * Funzione che inserisce una nuova posizione nella mossa
	 * @param pos Posizione da inserire
	 */
	public void addNewPos(Posizione pos){
		
		//Se il vettore della mossa si sta esaurendo lo raddoppio
		if(mossa.length==index)
			//Il vettore è esaurito lo ricreo raddoppiato
			mossa = upgradeVett(mossa);
		
		
		mossa[index] = new Posizione(pos);
		++index;
	}
	
	//Aumento il vettore delle descrizioni raddoppiandone la dimensione
	private Posizione[] upgradeVett(Posizione[] mossa){
		Posizione[] res = new Posizione[mossa.length*2];
			
		//Copio gli elementi nel nuovo array
		for(int i=0;i<mossa.length;i++)
			res[i] = mossa[i];
		
		return res;
	}
	
	/**
	 * Funzione che pulisce la mossa da posizioni non valide
	 */
	public void cleanVett(){
		//Trovo il numero reale di elmenti nel vettore
		int num=0;
		while(num<mossa.length && mossa[num]!=null)	++num;
		
		//Creo il vettore ben dimensionato
		Posizione[] res = new Posizione[num];
		
		//Copio gli elementi nel nuovo array
		for(int i=0;i<num;i++)
			res[i] = mossa[i];
		
		//aggiorno il vettore della mossa
		mossa=res;
	}
	
	/**
	 * Funzione che ritorna la prima Posizione inserita nella mossa
	 */
	public Posizione getStartPos(){
		return mossa[0]!=null?mossa[0]:null;
	}
	
	/**
	 * Funzione che ritorna l'ultima Posizione inserita nella mossa
	 */
	public Posizione getLastPos(){
		return mossa[index-1]!=null?mossa[index-1]:null;
	}
	
	/**
	 * Funzione che ritorna la Posizione nella mossa all'indice i
	 * @param i indice della Posizione
	 */
	public Posizione getPosMossa(int i){
		return mossa[i];
	}
	
	/**
	 * Funzione che ritorna il numero di posizioni
	 * passate dalla Mossa
	 */
	public int getLength(){
		return index;
	}
	
	/**
	 * Funzione che ritorna True se la Mossa e' vuota
	 */
	public boolean isEmpty(){
		return mossa[0]==null;
	}
	
	//Funzione che trasforma la mossa in una stringa
	public String toString(){
		String res = "";
		for(Posizione p: mossa)
			res += " "+p+" -> ";
		return res;
	}

}
