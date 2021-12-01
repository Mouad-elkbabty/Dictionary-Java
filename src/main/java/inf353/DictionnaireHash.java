package inf353;

public class DictionnaireHash implements Dictionnaire {

    /// attributs
    static int N = 100000;
    CelluleDictio[] T;
    int nb;

    /**
     * Crée un DictionnaireHash vide
     */
    public DictionnaireHash() {
        this.T = new CelluleDictio[N];
        this.nb = 0;
    }

    /**
     * Vide le dictionnaire
     */
    @Override
    public void vider() {
        this.T = new CelluleDictio[N];
        this.nb = 0;
    }

    /**
     * Ajoute un mot au dictionnaire si il n'y est pas déjà présent
     * 
     * @param m Le mot à ajouter
     */
    @Override
    public void ajouterMot(String m) {
        if (!contient(m)) {
            int n = Math.abs(m.hashCode() % N);
            CelluleDictio cc = T[n];
            CelluleDictio cp = null;
            int i = 0;
            while (cc != null) {
                cp = cc;
                cc = cc.suiv;
                i++;
            }
            if (cp == null) {
                T[n] = new CelluleDictio(m, (n * 10000) + i, null);
            } else {
                cp.suiv = new CelluleDictio(m, (n * 10000) + i, null);
            }
        }
    }

    /**
     * Retourne l'indice du mot dans le DictionnaireHash ou -1 s'il n'est pas trouvé
     * 
     * @param m Le mot à tester
     * @return L'indice du mot testé
     */
    @Override
    public int indiceMot(String m) {
        int n = Math.abs(m.hashCode() % N);
        CelluleDictio cc = T[n];
        while (cc != null && cc.elt != m) {
            cc = cc.suiv;
        }
        if (cc != null) {
            n = cc.ind;
        } else {
            n = -1;
        }
        return n;
    }

    /**
     * Retourne le mot contenu à l'indice i dans le DictionnaireHash ou null s'il
     * n'existe pas
     * 
     * @param i L'indice à tester
     * @return Le mot de l'indice testé
     */
    @Override
    public String motIndice(int i) {
        int j = 0;
        CelluleDictio cc;
        String s = null;

        j = i - ((i / 10000) * 10000);
        cc = T[i / 10000];
        while (cc != null && j > 0) {
            cc = cc.suiv;
            j = j - 1;
        }
        if (cc != null) {
            s = cc.elt;
        }
        return s;
    }

    /**
     * Renvoie vrai si le mot est contenu dans le DictionnaireHash
     * 
     * @param m Le mot à tester
     * @return
     */
    @Override
    public boolean contient(String m) {
        return indiceMot(m) != -1;
    }

    /**
     * Retourne le nombre de mots dans le DictionnaireHash
     * 
     * @return
     */
    @Override
    public int nbMots() {
        return this.nb;
    }

    /**
     * Retourne vrai si le préfixe est contenu dans l'un des mots du
     * DictionnaireHash
     * 
     * @param p Le préfixe à tester
     * @return
     */
    @Override
    public boolean contientPrefixe(String p) {
        return false;
    }

    /**
     * Retourne le préfixe le plus long du mot contenu dans le DictionnaireHash
     * 
     * @param mot Le mot à tester
     * @return
     */
    @Override
    public String plusLongPrefixeDe(String mot) {
        return null;
    }

}
