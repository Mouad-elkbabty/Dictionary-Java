package inf353;

public class CelluleDictio {
    
   /**
     * Attributs de la cellule : le mot, son indice, et son successeur.
     */
    public String elt;
    public int ind;
    public CelluleDictio suiv;

    /**
     * Constructeur vide (l'élément et le suivant ne sont pas spécifiés.
     */
    public CelluleDictio() {
        super();
    }

    /**
     * Constructeur à partir d'un élément (le suivant n'est pas spécifié).
     */
    public CelluleDictio(String val) {
        this(val, 0, null);
    }

    /**
     * Constructeur à partir d'un suivant (l'élément n'est pas spécifié).
     */
    public CelluleDictio(CelluleDictio suiv) {
        this(null, 0, suiv);
    }

    /**
     * Constructeur à partir d'un élément et d'un suivant.
     */
    public CelluleDictio(String val, int n, CelluleDictio suiv) {
        this.elt = val;
        this.suiv = suiv;
        this.ind = n;
    }

}
