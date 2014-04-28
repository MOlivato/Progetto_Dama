package AI;

import Model.Mossa;

/**
 * Classe che consiste nella riga della tabella di scelta
 * Oltre a contenere la mossa contiene tutte le informazioni
 * utili usate per la selezione delle mosse "ottime"
 * @author molivato
 *
 */
public class DescrittoreMossa {
	private Mossa mossa; //Mossa da descrivere
	private int vmaxEF; // Valore massimo nella foresta degli alberi nemica
	private int ndmaxEF; // Valore massimo numero dame mangiate foresta nemica
	private int numEF; // Valore che identifica il numero di foreste a valore massimo del nemico
	private int avvicinando; // True se la mossa porta ad un avvicinamento alla pedina nemica più vicina
	private boolean isdama;
	private boolean vittoria; // True se la mossa mi porta alla vittoria
	private boolean evolvi; // True se la mossa porta alla trasformazione di una pedina in una dama
	
	/**
	 * Costruttore che aggiunge un descrittore mossa vuoto
	 * contenente solo la mossa di riferimento
	 * @param m Mossa a cui si riferisce il descrittore
	 */
	public DescrittoreMossa(Mossa m){
		mossa = m;
		vittoria = false;
		evolvi = false;
		avvicinando = -1;
		isdama = false;
	}
	
	/**
	 * Funzione che ritorna la mossa memorizzata
	 * @return mossa
	 */
	public Mossa getMossa() {
		return mossa;
	}

	/**
	 * Funzione che setta la mossa a cui si riferisce il descrittore
	 * @param mossa
	 */
	public void setMossa(Mossa mossa) {
		this.mossa = mossa;
	}
	
	/**
	 * Funzione che ritorna il valore massimo generato dalla mossa nella foresta
	 * @return vmaxEF
	 */
	public int getVmaxEnemyForest() {
		return vmaxEF;
	}
	
	/**
	 * Funzione che setta il valore massimo nella foresta nemica
	 * generato da questa mossa
	 * @param vmaxEnemyForest
	 */
	public void setVmaxEnemyForest(int vmaxEnemyForest) {
		this.vmaxEF = vmaxEnemyForest;
	}

	/**
	 * Funzione che ritorna il numero massimo di dame mangiate nella foresta nemica
	 * generato da questa mossa
	 * @return ndmaxEF
	 */
	public int getNdmaxEnemyForest() {
		return ndmaxEF;
	}

	/**
	 * Funzione che setta il numero massimo di dame mangiate nella foresta nemica
	 * generato da questa mossa
	 * @param ndmaxEnemyForest
	 */
	public void setNdmaxEnemyForest(int ndmaxEnemyForest) {
		this.ndmaxEF = ndmaxEnemyForest;
	}
	
	/**
	 * Funzione che ritorna true se la mossa porta ad una vittoria
	 * @return vittoria
	 */
	public boolean isWinner() {
		return vittoria;
	}

	/**
	 * Funzione che setta se questa mossa porta alla vittoria
	 * @param vittoria
	 */
	public void setVittoria(boolean vittoria) {
		this.vittoria = vittoria;
	}

	/**
	 * Funzione che ritorna true se questa mossa porta all'evoluzione 
	 * di un pezzo da pedina a dama
	 * @return evolvi
	 */
	public boolean isChangedToDama() {
		return evolvi;
	}

	/**
	 * Funzione che setta se la mossa porta all'evoluzione della pedina in dama
	 * @param evolvi
	 */
	public void setChangedToDama(boolean evolvi) {
		this.evolvi = evolvi;
	}

	/**
	 * Funzione che ritorna il grado fi avvicinamento della mossa
	 * alle pedine o dame nemiche
	 * @return avvicinando
	 */
	public int getAvvicinando() {
		return avvicinando;
	}

	/**
	 * Funzione che setta il grado di avvicinamento alle dame nemiche 
	 * @param avvicinando
	 */
	public void setAvvicinando(int avvicinando) {
		this.avvicinando = avvicinando;
	}
	
	/**
	 * Funzione che ritorna true se la mossa e' riferita ad un pezzo di tipo dama
	 * @return isdama
	 */
	public boolean isDama() {
		return isdama;
	}

	/**
	 * Funzione che setta se la mossa e' riferita ad un pezzo di tipo dama
	 * @param isdama
	 */
	public void setIsdama(boolean isdama) {
		this.isdama = isdama;
	}

	/**
	 * Funzione che ritorna il numero di alberi presenti nella foresta nemica
	 * conseguenti a questa mossa 
	 * @return numEF
	 */
	public int getNumEF() {
		return numEF;
	}
	/**
	 * Funzione che setta il numero di alberi presente nella foresta nemica
	 * conseguenti a questa mossa
	 * @param NEF
	 */
	public void setNumEF(int NEF) {
		this.numEF = NEF;
	}
	
	/**
	 * Funzione che restituisce true se le due mosse generano la medesima
	 * situazione rispetto alla foresta nemica
	 * @param ds
	 * @return result
	 */
	public boolean equals(DescrittoreMossa ds){
		boolean res = vmaxEF == ds.getVmaxEnemyForest() && ndmaxEF == ds.getNdmaxEnemyForest();
		res = res && vittoria == ds.isWinner() && evolvi == ds.isChangedToDama() && avvicinando == ds.getAvvicinando();
		return res;
	}
	
	public String toString(){
		String res = mossa+"";
		res += " vmaxEF: "+vmaxEF+" ndmaxEF: "+ndmaxEF + " NumEF: "+numEF;
		res += "\n win: "+vittoria+" evolvi: "+evolvi+" avvic: "+avvicinando+" isdama:"+isdama; 
		return res;
	}

}
