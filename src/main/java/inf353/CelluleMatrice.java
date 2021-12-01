package inf353;

public class CelluleMatrice {
      
   /**
     * Attributs de la cellule : le mot, son indice, et son successeur.
     */
    public int elt;
    public int ind;
    public CelluleMatrice suiv;

    /**
     * Constructeur vide (l'élément et le suivant ne sont pas spécifiés.
     */
    public CelluleMatrice() {
        super();
    }

    /**
     * Constructeur à partir d'un élément (le suivant n'est pas spécifié).
     */
    public CelluleMatrice(int val) {
        this(val, 0, null);
    }

    /**
     * Constructeur à partir d'un suivant (l'élément n'est pas spécifié).
     */
    public CelluleMatrice(CelluleMatrice suiv) {
        this(0, 0, suiv);
    }

    /**
     * Constructeur à partir d'un élément et d'un suivant.
     */
    public CelluleMatrice(int val, int n, CelluleMatrice suiv) {
        this.elt = val;
        this.suiv = suiv;
        this.ind = n;
    }
    
}
