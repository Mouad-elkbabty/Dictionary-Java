package inf353;

public class CelluleDictio {
    
   /**
     * Attributs de la cellule : elt: le mot,
            ind: son indice dans le dictionnaire,
            occ: nombre d'occurrence total dans le corpus
            df: tf, nombre de document ou le mot est présent
            suiv: cellule suivante.
     */
    public String elt;
    public int ind;
    public int occ;
    public int df;
    public CelluleDictio suiv;

    /**
     * Constructeur vide (élément null, indice 0, occ null, nbDoc null et suivant null)
     */
    public CelluleDictio(){}

    /**
     * Constructeur à partir d'un élément (indice 0 et suivant null)
     */
    public CelluleDictio(String val) {
        this(val, 0,0,0, null);

    }
    /**
     * Constructeur à partir d'un élément, d'une valeur et d'un suivant
     */
    public CelluleDictio(String val, int ind, int occ, int df, CelluleDictio suiv) {
        this.elt = val;
        this.ind = ind;
        this.occ = occ;
        this.df = df;
        this.suiv = suiv;
    }

}
