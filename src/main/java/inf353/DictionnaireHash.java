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
            T[n] = new CelluleDictio(m, nb, T[n]);
            this.nb += 1;
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
        int n = -1;
        int i = Math.abs(m.hashCode() % N);
        CelluleDictio cc = T[i];
        while (cc != null && cc.elt != m) {
            cc = cc.suiv;
        }
        if (cc != null) {
            n = cc.ind;
        }
        return n;
    }

    /**
     * Retourne le mot contenu à l'indice i dans le DictionnaireHash ou null s'il n'existe pas
     * 
     * @param i L'indice à tester
     * @return Le mot de l'indice testé
     */
    @Override
    public String motIndice(int i) {
        String s = null;
        if (this.nb > i) {
            int j = 0;
            while (j < N && s == null) {
                CelluleDictio cc = this.T[j];
                while (cc != null && cc.ind != i) {
                    cc = cc.suiv;
                }
                if (cc != null) {
                    s = cc.elt;
                }
                j++;
            }
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
        boolean trouve = false;
        int j = 0;
        while (j < N && !trouve) {
            CelluleDictio cc = this.T[j];
            while (cc != null && !cc.elt.startsWith(p)) {
                cc = cc.suiv;
            }
            if (cc != null) {
                trouve = true;
            }
            j++;
        }
        return trouve;
    }

    /**
     * Retourne le préfixe le plus long du mot contenu dans le DictionnaireHash
     * 
     * @param mot Le mot à tester
     * @return
     */
    @Override
    public String plusLongPrefixeDe(String mot) {
        int n = this.indiceMot(mot);
        while (mot.length() > 0 && n == -1) {
            mot = mot.substring(0, mot.length()-1);
            n = this.indiceMot(mot);
        }
        return mot;
    }

}
