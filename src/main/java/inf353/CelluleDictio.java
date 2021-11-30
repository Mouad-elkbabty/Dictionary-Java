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
    public CelluleEntier() {
        super();
    }

    /**
     * Constructeur à partir d'un élément (le suivant n'est pas spécifié).
     */
    public CelluleEntier(String val) {
        this.elt = val;
        this.ind = 0;
    }

    /**
     * Constructeur à partir d'un suivant (l'élément n'est pas spécifié).
     */
    public CelluleEntier(CelluleEntier suiv) {
        this.suiv = suiv;
        this.elt = null;
    }

    /**
     * Constructeur à partir d'un élément et d'un suivant.
     */
    public CelluleEntier(String val,int n, CelluleEntier suiv) {
        this.elt = val;
        this.suiv = suiv;
        this.ind = n;
    }

}
