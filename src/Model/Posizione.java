package Model;

/**
 * Classe che rappresenta una posizione
 * riferita ad un modello matriciale a due dimensioni (row,col)
 * @author molivato
 *
 */
public class Posizione {
	private int row,col;
	
	/**
	 * Funzione costruttore della Posizione (0,0)
	 */
	public Posizione(){col=row=0;}
	
	/**
	 * Funzione costruttore a parametri
	 * @param r riga di riferimento 
	 * @param c colonna di riferimento
	 */
	public Posizione(int r, int c){
		row = r;
		col = c;
	}

	/**
	 * Funzione costruttore di copia
	 * @param p Posizione da copiare
	 */
	public Posizione(Posizione p){
		row = p.getRow();
		col = p.getCol();
	}
	
	/**
	 * Funzione che restituisce la riga di riferimento
	 */
	public int getRow(){ return row; }
	

	/**
	 * Funzione che restituisce la colonna di riferimento
	 */
	public int getCol(){ return col; }
	

	/**
	 * Funzione che controlla l'uguaglianza tra due posizioni
	 */
	public boolean equals(Posizione p){
		return row == p.row && col == p.col;
	}
	
	//Funzione che restituisce la posizione come stringa
	public String toString(){
		return "("+row+";"+col+")";
	}
}
