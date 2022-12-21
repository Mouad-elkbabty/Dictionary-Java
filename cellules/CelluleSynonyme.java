package inf353;

public class CelluleSynonyme {

    String expression;
    String synonyme;
    CelluleSynonyme suiv;

    public CelluleSynonyme() {}

    public CelluleSynonyme(String elt, String syno) {
        this(elt, syno, null);
    }

    public CelluleSynonyme(String elt, String syno, CelluleSynonyme succ) {
        this.expression = elt;
        this.synonyme = syno;
        this.suiv = succ;
    }

}