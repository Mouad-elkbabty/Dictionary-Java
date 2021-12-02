package inf353;

public class CelluleDictio{
    
   /**
     * Attributs de la cellule : le mot, son indice, et son suivant
     */
    public String elt;
    public int ind;
    public CelluleDictio suiv;

    /**
     * Constructeur vide (élément null, indice 0 et suivant null)
     */
    public CelluleDictio() {}

    /**
     * Constructeur à partir d'un élément (indice 0 et suivant null)
     */
    public CelluleDictio(String val) {
        this(val, 0, null);
    }

    /**
     * Constructeur à partir d'un suivant (élément null et indice 0)
     */
    public CelluleDictio(CelluleDictio suiv) {
        this(null, 0, suiv);
    }

    /**
     * Constructeur à partir d'un élément, d'une valeur et d'un suivant
     */
    public CelluleDictio(String val, int n, CelluleDictio suiv) {
        this.elt = val;
        this.ind = n;
        this.suiv = suiv;
    }

}
