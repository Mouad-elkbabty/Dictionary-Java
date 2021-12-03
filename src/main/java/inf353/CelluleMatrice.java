package inf353;

public class CelluleMatrice {
      
   /**
     * Attributs de la cellule : le mot, son indice, et son suivant
     */
    public int elt;
    public int ind;
    public CelluleMatrice suiv;

    /**
     * Constructeur vide (élément 0, indice 0 et suivant null)
     */
    public CelluleMatrice() {}

    /**
     * Constructeur à partir d'un élément (indice 0 et suivant null)
     */
    public CelluleMatrice(int val) {
        this(val, 0, null);
    }

    /**
     * Constructeur à partir d'un suivant (élément 0 et indice 0)
     */
    public CelluleMatrice(CelluleMatrice suiv) {
        this(0, 0, suiv);
    }

    /**
     * Constructeur à partir d'un élément, d'une valeur et d'un suivant
     */
    public CelluleMatrice(int val, int i, CelluleMatrice suiv) {
        this.elt = val;
        this.ind = i;
        this.suiv = suiv;
    }
    
}
