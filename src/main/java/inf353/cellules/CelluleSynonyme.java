package inf353;

public class CelluleSynonyme {

    String elt;
    CelluleSynonyme suiv;

    public CelluleSynonyme() {}

    public CelluleSynonyme(String elt) {
        this(elt, null);
    }

    public CelluleSynonyme(CelluleSynonyme suiv) {
        this(null, suiv);
    }

    public CelluleSynonyme(String elt, CelluleSynonyme suiv) {
        this.elt = elt;
        this.suiv = suiv;
    }

}